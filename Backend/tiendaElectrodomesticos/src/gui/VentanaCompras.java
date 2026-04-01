package gui;

import controlador.Coordinador;
import modelo.dto.ClienteDTO;
import modelo.dto.ProductoDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * VentanaCompras — se abre desde VentanaAdmin.
 * ventanaAdmin puede ser null si se usa desde otro contexto.
 */
public class VentanaCompras extends JFrame {

    private final Coordinador  coordinador;
    private final VentanaAdmin ventanaAdmin;
    private final JFrame       ventanaPadre;

    private JComboBox<ClienteDTO>  cboClientes;
    private JComboBox<ProductoDTO> cboProductos;
    private JSpinner               spinCantidad;
    private JLabel                 lblPrecio;
    private JLabel                 lblTotal;

    public VentanaCompras(Coordinador coordinador,
                          JFrame ventanaPadre,
                          VentanaAdmin ventanaAdmin) {
        this.coordinador  = coordinador;
        this.ventanaPadre = ventanaPadre;
        this.ventanaAdmin = ventanaAdmin;
        initUI();
        cargarCombos();
    }

    private void initUI() {
        setTitle("Realizar Compra");
        setSize(440, 360);
        setLocationRelativeTo(ventanaAdmin != null ? ventanaAdmin : ventanaPadre);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 24, 20, 24));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1.0;

        JLabel titulo = new JLabel("Nueva Compra");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        titulo.setForeground(new Color(0x3B4FC8));
        g.gridx = 0; g.gridy = 0; g.gridwidth = 2;
        panel.add(titulo, g);
        g.gridwidth = 1;

        g.gridx = 0; g.gridy = 1; panel.add(new JLabel("Cliente:"), g);
        cboClientes = new JComboBox<>();
        g.gridx = 1; panel.add(cboClientes, g);

        g.gridx = 0; g.gridy = 2; panel.add(new JLabel("Producto:"), g);
        cboProductos = new JComboBox<>();
        cboProductos.addActionListener(e -> actualizarPrecio());
        g.gridx = 1; panel.add(cboProductos, g);

        g.gridx = 0; g.gridy = 3; panel.add(new JLabel("Precio unitario:"), g);
        lblPrecio = new JLabel("$0");
        lblPrecio.setFont(new Font("SansSerif", Font.BOLD, 13));
        g.gridx = 1; panel.add(lblPrecio, g);

        g.gridx = 0; g.gridy = 4; panel.add(new JLabel("Cantidad:"), g);
        spinCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
        spinCantidad.addChangeListener(e -> actualizarTotal());
        g.gridx = 1; panel.add(spinCantidad, g);

        g.gridx = 0; g.gridy = 5; panel.add(new JLabel("Total:"), g);
        lblTotal = new JLabel("$0");
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTotal.setForeground(new Color(0x1A7A4A));
        g.gridx = 1; panel.add(lblTotal, g);

        g.gridx = 0; g.gridy = 6; g.gridwidth = 2;
        panel.add(new JSeparator(), g);

        JButton btnComprar = new JButton("Confirmar Compra");
        btnComprar.setBackground(new Color(0x1A7A4A));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnComprar.setFocusPainted(false);
        g.gridy = 7;
        panel.add(btnComprar, g);
        btnComprar.addActionListener(e -> accionComprar());

        add(panel);
    }

    private void cargarCombos() {
        cboClientes.removeAllItems();
        for (ClienteDTO c : coordinador.obtenerClientes()) cboClientes.addItem(c);
        cboProductos.removeAllItems();
        for (ProductoDTO p : coordinador.obtenerProductosDisponibles()) cboProductos.addItem(p);
        actualizarPrecio();
    }

    private void actualizarPrecio() {
        ProductoDTO p = (ProductoDTO) cboProductos.getSelectedItem();
        if (p != null) { lblPrecio.setText("$" + String.format("%,.0f", p.getPrecio())); actualizarTotal(); }
    }

    private void actualizarTotal() {
        ProductoDTO p = (ProductoDTO) cboProductos.getSelectedItem();
        if (p != null)
            lblTotal.setText("$" + String.format("%,.0f", p.getPrecio() * (int) spinCantidad.getValue()));
    }

    private void accionComprar() {
        ClienteDTO  cliente  = (ClienteDTO)  cboClientes.getSelectedItem();
        ProductoDTO producto = (ProductoDTO) cboProductos.getSelectedItem();
        if (cliente == null || producto == null) {
            JOptionPane.showMessageDialog(this, "Selecciona cliente y producto."); return;
        }
        String res = coordinador.realizarCompra(producto, cliente.getId(), (int) spinCantidad.getValue());
        boolean ok = res.startsWith("OK") || res.startsWith("Compra");
        // Detectar éxito por símbolo checkmark
        ok = res.contains("Compra realizada") || res.startsWith("OK");
        // Forma robusta: éxito si NO empieza por palabras de error
        ok = !res.startsWith("Stock") && !res.startsWith("Error") &&
             !res.startsWith("La cantidad") && !res.startsWith("Compra guardada pero");

        JOptionPane.showMessageDialog(this, res,
            ok ? "Éxito" : "Error",
            ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);

        if (ok) {
            if (ventanaAdmin != null) ventanaAdmin.cargarCompras();
            cargarCombos();
            spinCantidad.setValue(1);
        }
    }
}