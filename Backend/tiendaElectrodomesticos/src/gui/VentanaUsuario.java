package gui;

import controlador.Coordinador;
import modelo.dto.ClienteDTO;
import modelo.dto.ComprasDTO;
import modelo.dto.ProductoDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

// Panel para el usuario normal (rol = 'usuario')
// Permite: ver catálogo, realizar compra con descuento y ver sus compras
public class VentanaUsuario extends JFrame {

    private final Coordinador coordinador;
    private ClienteDTO clienteActual;

    // Campos del formulario de compra (según el PDF)
    private JTextField txtNombre, txtApellido, txtEdad, txtTelefono;
    private JTextField txtProducto, txtValorUnitario, txtCantidad;
    private JComboBox<String> cboTipo;

    // Etiquetas de resultado (según el PDF)
    private JLabel lblResNombre, lblResTipo, lblResTotalCompra;
    private JLabel lblResDescuento, lblResPrecioReal;

    // Tabla de mis compras
    private DefaultTableModel modeloMisCompras;

    public VentanaUsuario(Coordinador coordinador) {
        this.coordinador  = coordinador;
        this.clienteActual = coordinador.obtenerClientePorUsuario(
                coordinador.getSesion().getId());
        initUI();
        cargarDatos();
    }

    private void initUI() {
        setTitle("Tienda DON APARATO — " + coordinador.getSesion().getUsername());
        setSize(860, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Barra superior
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(new Color(0x3B4FC8));
        barra.setBorder(new EmptyBorder(10, 16, 10, 16));
        JLabel lblTitulo = new JLabel("Tienda DON APARATO");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTitulo.setForeground(Color.WHITE);
        JButton btnLogout = new JButton("Cerrar sesión");
        btnLogout.setBackground(new Color(0x8B2200));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(e -> cerrarSesion());
        JPanel der = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        der.setOpaque(false);
        String nombreMostrar = clienteActual != null
                ? clienteActual.getNombre() + " " + clienteActual.getApellido()
                : coordinador.getSesion().getUsername();
        JLabel lblUser = new JLabel("👤 " + nombreMostrar + "   ");
        lblUser.setForeground(new Color(0xCCDDFF));
        der.add(lblUser); der.add(btnLogout);
        barra.add(lblTitulo, BorderLayout.WEST);
        barra.add(der, BorderLayout.EAST);

        // Pestañas
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabs.addTab("Realizar Compra", tabCompra());
        tabs.addTab("Mis Compras",     tabMisCompras());

        // Aviso si no tiene perfil de cliente
        if (clienteActual == null) {
            JPanel aviso = new JPanel(new FlowLayout());
            aviso.setBackground(new Color(0xFFF3CD));
            JLabel lbl = new JLabel("⚠  Sin perfil de cliente. Contacta al administrador.");
            lbl.setForeground(new Color(0x664D00));
            aviso.add(lbl);
            add(barra,  BorderLayout.NORTH);
            add(tabs,   BorderLayout.CENTER);
            add(aviso,  BorderLayout.SOUTH);
        } else {
            add(barra,  BorderLayout.NORTH);
            add(tabs,   BorderLayout.CENTER);
        }
    }

    // ── PESTAÑA REALIZAR COMPRA (formulario del PDF) ──────
    private JPanel tabCompra() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(12, 14, 12, 14));
        panel.setBackground(Color.WHITE);

        // ── Sección datos del usuario ─────────────────────
        JPanel panelDatos = new JPanel(new GridLayout(3, 4, 8, 8));
        panelDatos.setBackground(Color.WHITE);
        panelDatos.setBorder(new TitledBorder("Datos del Usuario"));

        txtNombre       = new JTextField();
        txtApellido     = new JTextField();
        txtEdad         = new JTextField();
        txtTelefono     = new JTextField();

        // Tipo ENUM: A, B, C o vacío (sin descuento)
        cboTipo = new JComboBox<>(new String[]{"(Sin tipo)", "A", "B", "C"});

        // Pre-cargar datos del cliente si ya existe
        if (clienteActual != null) {
            txtNombre.setText(clienteActual.getNombre());
            txtApellido.setText(clienteActual.getApellido());
            txtEdad.setText(String.valueOf(clienteActual.getEdad()));
            txtTelefono.setText(clienteActual.getTelefono());
            String tipo = clienteActual.getTipo();
            cboTipo.setSelectedItem(tipo != null ? tipo : "(Sin tipo)");
        }

        panelDatos.add(new JLabel("Nombre:")); panelDatos.add(txtNombre);
        panelDatos.add(new JLabel("Apellido:")); panelDatos.add(txtApellido);
        panelDatos.add(new JLabel("Edad:")); panelDatos.add(txtEdad);
        panelDatos.add(new JLabel("Teléfono:")); panelDatos.add(txtTelefono);
        panelDatos.add(new JLabel("Tipo (A/B/C):")); panelDatos.add(cboTipo);
        panelDatos.add(new JLabel("")); panelDatos.add(new JLabel(""));

        // ── Sección datos del producto ────────────────────
        JPanel panelProd = new JPanel(new GridLayout(2, 4, 8, 8));
        panelProd.setBackground(Color.WHITE);
        panelProd.setBorder(new TitledBorder("Datos del Producto"));

        txtProducto      = new JTextField();
        txtValorUnitario = new JTextField();
        txtCantidad      = new JTextField();

        // Selector de producto del inventario
        JComboBox<ProductoDTO> cboProd = new JComboBox<>();
        for (ProductoDTO p : coordinador.obtenerProductosDisponibles()) cboProd.addItem(p);
        cboProd.addActionListener(e -> {
            ProductoDTO sel = (ProductoDTO) cboProd.getSelectedItem();
            if (sel != null) {
                txtProducto.setText(sel.getNombre());
                txtValorUnitario.setText(String.valueOf(sel.getPrecio()));
            }
        });
        // Cargar primer producto
        if (cboProd.getItemCount() > 0) {
            ProductoDTO p = (ProductoDTO) cboProd.getSelectedItem();
            txtProducto.setText(p.getNombre());
            txtValorUnitario.setText(String.valueOf(p.getPrecio()));
        }

        panelProd.add(new JLabel("Seleccionar producto:")); panelProd.add(cboProd);
        panelProd.add(new JLabel("Valor unitario:")); panelProd.add(txtValorUnitario);
        panelProd.add(new JLabel("Nombre producto:")); panelProd.add(txtProducto);
        panelProd.add(new JLabel("Cantidad:")); panelProd.add(txtCantidad);

        // ── Botones (según el PDF) ────────────────────────
        JPanel panelBtns = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 8));
        panelBtns.setBackground(Color.WHITE);

        JButton btnComprar  = new JButton("Realizar Compra");
        JButton btnMostrar  = new JButton("Mostrar Datos del Usuario");
        JButton btnLimpiar  = new JButton("Limpiar");

        btnComprar.setBackground(new Color(0x1A7A4A));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnComprar.setFocusPainted(false);

        btnMostrar.setBackground(new Color(0x3B4FC8));
        btnMostrar.setForeground(Color.WHITE);
        btnMostrar.setFocusPainted(false);

        btnLimpiar.setBackground(new Color(0x888888));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFocusPainted(false);

        panelBtns.add(btnComprar); panelBtns.add(btnMostrar); panelBtns.add(btnLimpiar);

        // ── Etiquetas de resultado (según el PDF) ─────────
        JPanel panelRes = new JPanel(new GridLayout(5, 2, 8, 6));
        panelRes.setBackground(new Color(0xF5F5FF));
        panelRes.setBorder(new TitledBorder("Resultado de la Compra"));

        lblResNombre     = new JLabel("—");
        lblResTipo       = new JLabel("—");
        lblResTotalCompra= new JLabel("—");
        lblResDescuento  = new JLabel("—");
        lblResPrecioReal = new JLabel("—");

        lblResPrecioReal.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblResPrecioReal.setForeground(new Color(0x1A7A4A));

        panelRes.add(new JLabel("Usuario:"));       panelRes.add(lblResNombre);
        panelRes.add(new JLabel("Tipo:"));          panelRes.add(lblResTipo);
        panelRes.add(new JLabel("Precio total:"));  panelRes.add(lblResTotalCompra);
        panelRes.add(new JLabel("Descuento:"));     panelRes.add(lblResDescuento);
        panelRes.add(new JLabel("Precio real:"));   panelRes.add(lblResPrecioReal);

        //Acciones de los botones

        // Realizar Compra: calcula descuento y muestra en etiquetas
        btnComprar.addActionListener(e -> {
            if (clienteActual == null) {
                JOptionPane.showMessageDialog(this, "Necesitas perfil de cliente para comprar.");
                return;
            }
            // Validar campos
            if (txtCantidad.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingresa la cantidad.");
                return;
            }
            int cantidad;
            try { cantidad = Integer.parseInt(txtCantidad.getText().trim()); }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número.");
                return;
            }

            // Construir ClienteDTO con el tipo seleccionado en el combo
            String tipoSel = (String) cboTipo.getSelectedItem();
            ClienteDTO cliente = new ClienteDTO(
                clienteActual.getId(),
                txtNombre.getText().trim(),
                txtApellido.getText().trim(),
                clienteActual.getEdad(),
                txtTelefono.getText().trim(),
                tipoSel.equals("(Sin tipo)") ? null : tipoSel,
                clienteActual.getEmail()
            );

            // Obtener producto seleccionado del combo
            ProductoDTO prod = (ProductoDTO) cboProd.getSelectedItem();
            if (prod == null) { JOptionPane.showMessageDialog(this, "Selecciona un producto."); return; }

            // Procesa la venta con descuento en LogicaDeVenta
            ComprasDTO resultado = coordinador.realizarCompraConDescuento(cliente, prod, cantidad);

            if (resultado == null) {
                JOptionPane.showMessageDialog(this,
                    "Error: stock insuficiente o cantidad inválida.", "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Mostrar resultados en las etiquetas (como pide el PDF)
            lblResNombre.setText(cliente.getNombre() + " " + cliente.getApellido());
            lblResTipo.setText(tipoSel.equals("(Sin tipo)") ? "Sin tipo" : "Tipo " + tipoSel);

            lblResTotalCompra.setText("$" + String.format("%,.0f", resultado.getTotalSinDesc()));

            // Descuento: mostrar texto o "No se le realiza descuento"
            if (resultado.getDescuento() == 0) {
                lblResDescuento.setText("No se le realiza descuento");
                lblResDescuento.setForeground(new Color(0x666666));
            } else {
                double pct = cliente.getPorcentajeDescuento() * 100;
                lblResDescuento.setText("- $" + String.format("%,.0f", resultado.getDescuento())
                    + " (" + (int)pct + "%)");
                lblResDescuento.setForeground(new Color(0xCC2200));
            }

            lblResPrecioReal.setText("$" + String.format("%,.0f", resultado.getTotalFinal()));

            cargarMisCompras();
        });

        // Mostrar Datos del Usuario: muestra los datos básicos o avisa si están vacíos
        btnMostrar.addActionListener(e -> {
            if (txtNombre.getText().trim().isEmpty() && txtApellido.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Los campos se encuentran vacíos.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            String tipo = (String) cboTipo.getSelectedItem();
            JOptionPane.showMessageDialog(this,
                "Nombre: "    + txtNombre.getText() + " " + txtApellido.getText() + "\n" +
                "Edad: "      + txtEdad.getText() + "\n" +
                "Teléfono: "  + txtTelefono.getText() + "\n" +
                "Tipo: "      + (tipo.equals("(Sin tipo)") ? "Sin tipo asignado" : tipo),
                "Datos del Usuario", JOptionPane.INFORMATION_MESSAGE);
        });

        // Limpiar: limpia formulario y etiquetas
        btnLimpiar.addActionListener(e -> {
            txtNombre.setText(""); txtApellido.setText("");
            txtEdad.setText(""); txtTelefono.setText("");
            txtProducto.setText(""); txtValorUnitario.setText("");
            txtCantidad.setText("");
            cboTipo.setSelectedIndex(0);
            lblResNombre.setText("—"); lblResTipo.setText("—");
            lblResTotalCompra.setText("—"); lblResDescuento.setText("—");
            lblResPrecioReal.setText("—");
        });

        //Armar el panel completo 
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBackground(Color.WHITE);
        centro.add(panelDatos);
        centro.add(Box.createVerticalStrut(8));
        centro.add(panelProd);
        centro.add(Box.createVerticalStrut(8));
        centro.add(panelBtns);
        centro.add(Box.createVerticalStrut(8));
        centro.add(panelRes);

        panel.add(new JScrollPane(centro), BorderLayout.CENTER);
        return panel;
    }

    //PESTAÑA MIS COMPRAS
    private JPanel tabMisCompras() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        modeloMisCompras = new DefaultTableModel(
            new String[]{"#","Producto","Cant.","Total sin desc.","Descuento","Total final"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(modeloMisCompras);
        tabla.setRowHeight(26);
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JButton btnRefresh = new JButton("Actualizar");
        btnRefresh.addActionListener(e -> cargarMisCompras());

        panel.add(btnRefresh,                   BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla),        BorderLayout.CENTER);
        return panel;
    }

    // CARGA DE DATOS
    public void cargarDatos()  { cargarMisCompras(); }

    private void cargarMisCompras() {
        modeloMisCompras.setRowCount(0);
        if (clienteActual == null) return;
        List<ComprasDTO> compras = coordinador.obtenerComprasPorCliente(clienteActual.getId());
        for (ComprasDTO c : compras)
            modeloMisCompras.addRow(new Object[]{
                c.getId(), c.getNombreProducto(), c.getCantidad(),
                "$" + String.format("%,.0f", c.getTotalSinDesc()),
                "$" + String.format("%,.0f", c.getDescuento()),
                "$" + String.format("%,.0f", c.getTotalFinal())});
    }

    private void cerrarSesion() {
        coordinador.logout();
        dispose();
        new VentanaLogin(coordinador).setVisible(true);
    }
}