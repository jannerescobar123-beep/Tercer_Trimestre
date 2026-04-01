package gui;

import controlador.Coordinador;
import modelo.dto.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaAdmin extends JFrame {

    private final Coordinador coordinador;
    private DefaultTableModel modeloProductos, modeloClientes, modeloCompras, modeloUsuarios;
    private JTable tablaProductos, tablaClientes, tablaCompras, tablaUsuarios;

    public VentanaAdmin(Coordinador coordinador) {
        this.coordinador = coordinador;
        initUI();
        cargarTodo();
    }

    private void initUI() {
        setTitle("Panel Admin — " + coordinador.getSesion().getUsername());
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Barra superior
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(new Color(0x1E2A7A));
        barra.setBorder(new EmptyBorder(10, 16, 10, 16));
        JLabel lbl = new JLabel("Panel de Administración");
        lbl.setFont(new Font("SansSerif", Font.BOLD, 15));
        lbl.setForeground(Color.WHITE);
        JButton btnOut = new JButton("Cerrar sesión");
        btnOut.setBackground(new Color(0xCC2200));
        btnOut.setForeground(Color.WHITE);
        btnOut.setFocusPainted(false);
        btnOut.addActionListener(e -> cerrarSesion());
        JPanel der = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        der.setOpaque(false);
        JLabel lblU = new JLabel(coordinador.getSesion().getUsername() + " (admin)   ");
        lblU.setForeground(new Color(0xBBCCFF));
        der.add(lblU); der.add(btnOut);
        barra.add(lbl, BorderLayout.WEST);
        barra.add(der, BorderLayout.EAST);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabs.addTab("Productos", tabProductos());
        tabs.addTab("Clientes",  tabClientes());
        tabs.addTab("Compras",   tabCompras());
        tabs.addTab("Usuarios",  tabUsuarios());

        add(barra, BorderLayout.NORTH);
        add(tabs,  BorderLayout.CENTER);
    }

    // PESTAÑA PRODUCTOS
    private JPanel tabProductos() {
        modeloProductos = modelo(new String[]{"ID","Nombre","Precio","Stock"});
        tablaProductos  = tabla(modeloProductos);
        JButton bA = btn("+ Agregar", new Color(0x1A7A4A));
        JButton bE = btn("Editar",    new Color(0x3B4FC8));
        JButton bX = btn("Eliminar",  new Color(0xCC2200));
        JButton bR = btn("Refrescar", new Color(0x666666));
        bA.addActionListener(e -> dialogoAgregarProducto());
        bE.addActionListener(e -> dialogoEditarProducto());
        bX.addActionListener(e -> eliminarProducto());
        bR.addActionListener(e -> cargarProductos());
        return panelTab(tablaProductos, bA, bE, bX, bR);
    }

    // PESTAÑA CLIENTES
    private JPanel tabClientes() {
        // Ahora muestra también apellido, edad y tipo
        modeloClientes = modelo(new String[]{"ID","Nombre","Apellido","Edad","Teléfono","Tipo","Email"});
        tablaClientes  = tabla(modeloClientes);
        JButton bA = btn("+ Agregar", new Color(0x1A7A4A));
        JButton bE = btn("Editar",    new Color(0x3B4FC8));
        JButton bX = btn("Eliminar",  new Color(0xCC2200));
        JButton bR = btn("Refrescar", new Color(0x666666));
        bA.addActionListener(e -> dialogoAgregarCliente());
        bE.addActionListener(e -> dialogoEditarCliente());
        bX.addActionListener(e -> eliminarCliente());
        bR.addActionListener(e -> cargarClientes());
        return panelTab(tablaClientes, bA, bE, bX, bR);
    }

    //PESTAÑA COMPRAS 
    private JPanel tabCompras() {
        // Muestra descuento y total final
        modeloCompras = modelo(new String[]{"#","Cliente","Producto","Cant.","Sin desc.","Descuento","Total final"});
        tablaCompras  = tabla(modeloCompras);
        JButton bN = btn("Nueva Compra", new Color(0x3B4FC8));
        JButton bR = btn("Refrescar",    new Color(0x666666));
        bN.addActionListener(e -> new VentanaCompras(coordinador, null, this).setVisible(true));
        bR.addActionListener(e -> cargarCompras());
        return panelTab(tablaCompras, bN, bR);
    }

    // PESTAÑA USUARIOS
    private JPanel tabUsuarios() {
        modeloUsuarios = modelo(new String[]{"ID","Username","Rol","Activo"});
        tablaUsuarios  = tabla(modeloUsuarios);
        JButton bA = btn("+ Agregar",  new Color(0x1A7A4A));
        JButton bX = btn("Eliminar",   new Color(0xCC2200));
        JButton bR = btn("Refrescar",  new Color(0x666666));
        bA.addActionListener(e -> dialogoAgregarUsuario());
        bX.addActionListener(e -> eliminarUsuario());
        bR.addActionListener(e -> cargarUsuarios());
        return panelTab(tablaUsuarios, bA, bX, bR);
    }

    //DIÁLOGOS CRUD 

    private void dialogoAgregarProducto() {
        JTextField fN = new JTextField(), fP = new JTextField(), fS = new JTextField();
        if (JOptionPane.showConfirmDialog(this,
            new Object[]{"Nombre:", fN, "Precio:", fP, "Stock:", fS},
            "Agregar Producto", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                boolean ok = coordinador.agregarProducto(fN.getText().trim(),
                    Double.parseDouble(fP.getText().trim()), Integer.parseInt(fS.getText().trim()));
                msg(ok ? "Producto agregado." : "Error.", ok); cargarProductos();
            } catch (NumberFormatException e) { JOptionPane.showMessageDialog(this, "Precio y stock deben ser números."); }
        }
    }

    private void dialogoEditarProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Selecciona un producto."); return; }
        int id = (int) modeloProductos.getValueAt(fila, 0);
        JTextField fN = new JTextField((String) modeloProductos.getValueAt(fila, 1));
        JTextField fP = new JTextField(((String) modeloProductos.getValueAt(fila, 2)).replace(",",""));
        JTextField fS = new JTextField(String.valueOf(modeloProductos.getValueAt(fila, 3)));
        if (JOptionPane.showConfirmDialog(this,
            new Object[]{"Nombre:", fN, "Precio:", fP, "Stock:", fS},
            "Editar Producto", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                boolean ok = coordinador.actualizarProducto(new ProductoDTO(id,
                    fN.getText().trim(), Double.parseDouble(fP.getText().trim()),
                    Integer.parseInt(fS.getText().trim())));
                msg(ok ? "Actualizado." : "Error.", ok); cargarProductos();
            } catch (NumberFormatException e) { JOptionPane.showMessageDialog(this, "Números inválidos."); }
        }
    }

    private void eliminarProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Selecciona un producto."); return; }
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar?", "Confirmar",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            boolean ok = coordinador.eliminarProducto((int) modeloProductos.getValueAt(fila, 0));
            msg(ok ? "Eliminado." : "No se puede eliminar (tiene compras).", ok); cargarProductos();
        }
    }

    private void dialogoAgregarCliente() {
        JTextField fNom = new JTextField(), fApe = new JTextField();
        JTextField fEda = new JTextField(), fTel = new JTextField(), fEma = new JTextField();
        // Tipo ENUM como combo
        JComboBox<String> fTipo = new JComboBox<>(new String[]{"(Sin tipo)", "A", "B", "C"});
        if (JOptionPane.showConfirmDialog(this,
            new Object[]{"Nombre:", fNom, "Apellido:", fApe, "Edad:", fEda,
                         "Teléfono:", fTel, "Email:", fEma, "Tipo:", fTipo},
            "Agregar Cliente", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                String tipo = fTipo.getSelectedItem().toString();
                ClienteDTO dto = new ClienteDTO(
                    fNom.getText().trim(), fApe.getText().trim(),
                    Integer.parseInt(fEda.getText().trim()),
                    fTel.getText().trim(),
                    tipo.equals("(Sin tipo)") ? null : tipo,
                    fEma.getText().trim());
                boolean ok = coordinador.registrarCliente(dto);
                msg(ok ? "Cliente agregado." : "Error (email duplicado?).", ok); cargarClientes();
            } catch (NumberFormatException e) { JOptionPane.showMessageDialog(this, "Edad debe ser número."); }
        }
    }

    private void dialogoEditarCliente() {
        int fila = tablaClientes.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Selecciona un cliente."); return; }
        int id = (int) modeloClientes.getValueAt(fila, 0);
        JTextField fNom = new JTextField((String) modeloClientes.getValueAt(fila, 1));
        JTextField fApe = new JTextField((String) modeloClientes.getValueAt(fila, 2));
        JTextField fEda = new JTextField(String.valueOf(modeloClientes.getValueAt(fila, 3)));
        JTextField fTel = new JTextField((String) modeloClientes.getValueAt(fila, 4));
        JTextField fEma = new JTextField((String) modeloClientes.getValueAt(fila, 6));
        JComboBox<String> fTipo = new JComboBox<>(new String[]{"(Sin tipo)", "A", "B", "C"});
        String tipoAct = (String) modeloClientes.getValueAt(fila, 5);
        fTipo.setSelectedItem(tipoAct != null ? tipoAct : "(Sin tipo)");
        if (JOptionPane.showConfirmDialog(this,
            new Object[]{"Nombre:", fNom, "Apellido:", fApe, "Edad:", fEda,
                         "Teléfono:", fTel, "Email:", fEma, "Tipo:", fTipo},
            "Editar Cliente", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                String tipo = fTipo.getSelectedItem().toString();
                ClienteDTO dto = new ClienteDTO(id,
                    fNom.getText().trim(), fApe.getText().trim(),
                    Integer.parseInt(fEda.getText().trim()),
                    fTel.getText().trim(),
                    tipo.equals("(Sin tipo)") ? null : tipo,
                    fEma.getText().trim());
                boolean ok = coordinador.actualizarCliente(dto);
                msg(ok ? "Actualizado." : "Error.", ok); cargarClientes();
            } catch (NumberFormatException e) { JOptionPane.showMessageDialog(this, "Edad debe ser número."); }
        }
    }

    private void eliminarCliente() {
        int fila = tablaClientes.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Selecciona un cliente."); return; }
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar?", "Confirmar",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            boolean ok = coordinador.eliminarCliente((int) modeloClientes.getValueAt(fila, 0));
            msg(ok ? "Eliminado." : "No se puede eliminar (tiene compras).", ok); cargarClientes();
        }
    }

    private void dialogoAgregarUsuario() {
        JTextField fU = new JTextField();
        JPasswordField fP = new JPasswordField();
        JComboBox<String> fR = new JComboBox<>(new String[]{"usuario", "admin"});
        if (JOptionPane.showConfirmDialog(this,
            new Object[]{"Username:", fU, "Password:", fP, "Rol:", fR},
            "Agregar Usuario", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            String pass = new String(fP.getPassword());
            if (pass.length() < 4) { JOptionPane.showMessageDialog(this, "Password mínimo 4 caracteres."); return; }
            boolean ok = coordinador.registrarUsuario(fU.getText().trim(), pass, (String) fR.getSelectedItem());
            msg(ok ? "Usuario creado." : "Username ya existe.", ok); cargarUsuarios();
        }
    }

    private void eliminarUsuario() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Selecciona un usuario."); return; }
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar?", "Confirmar",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            boolean ok = coordinador.eliminarUsuario((int) modeloUsuarios.getValueAt(fila, 0));
            msg(ok ? "Eliminado." : "No se puede eliminar.", ok); cargarUsuarios();
        }
    }

    //CARGA DE DATOS 
    public void cargarTodo() { cargarProductos(); cargarClientes(); cargarCompras(); cargarUsuarios(); }

    private void cargarProductos() {
        modeloProductos.setRowCount(0);
        for (ProductoDTO p : coordinador.obtenerTodosProductos())
            modeloProductos.addRow(new Object[]{p.getId(), p.getNombre(),
                String.format("%,.0f", p.getPrecio()), p.getStock()});
    }

    private void cargarClientes() {
        modeloClientes.setRowCount(0);
        for (ClienteDTO c : coordinador.obtenerClientes())
            modeloClientes.addRow(new Object[]{c.getId(), c.getNombre(), c.getApellido(),
                c.getEdad(), c.getTelefono(),
                c.getTipo() != null ? c.getTipo() : "Sin tipo", c.getEmail()});
    }

    public void cargarCompras() {
        modeloCompras.setRowCount(0);
        for (ComprasDTO c : coordinador.obtenerHistorialCompras())
            modeloCompras.addRow(new Object[]{c.getId(), c.getNombreCliente(),
                c.getNombreProducto(), c.getCantidad(),
                "$" + String.format("%,.0f", c.getTotalSinDesc()),
                "$" + String.format("%,.0f", c.getDescuento()),
                "$" + String.format("%,.0f", c.getTotalFinal())});
    }

    private void cargarUsuarios() {
        modeloUsuarios.setRowCount(0);
        for (UsuarioDTO u : coordinador.obtenerUsuarios())
            modeloUsuarios.addRow(new Object[]{u.getId(), u.getUsername(),
                u.getRol(), u.isActivo() ? "Sí" : "No"});
    }

    private void cerrarSesion() {
        coordinador.logout(); dispose();
        new VentanaLogin(coordinador).setVisible(true);
    }

    //UTILIDADES 
    private JPanel panelTab(JTable t, JButton... botones) {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setBorder(new EmptyBorder(10,10,10,10));
        p.setBackground(Color.WHITE);
        JPanel bp = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        bp.setBackground(Color.WHITE);
        for (JButton b : botones) bp.add(b);
        p.add(bp, BorderLayout.NORTH);
        p.add(new JScrollPane(t), BorderLayout.CENTER);
        return p;
    }

    private DefaultTableModel modelo(String[] cols) {
        return new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
    }

    private JTable tabla(DefaultTableModel m) {
        JTable t = new JTable(m);
        t.setRowHeight(26);
        t.setFont(new Font("SansSerif", Font.PLAIN, 13));
        t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return t;
    }

    private JButton btn(String txt, Color color) {
        JButton b = new JButton(txt);
        b.setBackground(color); b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif", Font.PLAIN, 12));
        b.setFocusPainted(false);
        return b;
    }

    private void msg(String txt, boolean ok) {
        JOptionPane.showMessageDialog(this, txt,
            ok ? "OK" : "Error",
            ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }
}