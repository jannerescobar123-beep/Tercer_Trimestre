package gui;

import javax.swing.*;
import java.awt.*;
import controlador.Coordinador;

public class VentanaCompra extends JDialog {

    private Coordinador c;

    private JTextField txtCliente, txtEdad, txtTelefono;
    private JComboBox<String> comboTipo;
    private JTextField txtProducto, txtPrecio, txtCantidad;

    private JTextArea area;

    public VentanaCompra(JFrame parent, boolean modal){
        super(parent,"Compra",modal);
        setSize(400,400);
        setLayout(new GridLayout(10,2));

        txtCliente = new JTextField();
        txtEdad = new JTextField();
        txtTelefono = new JTextField();
        comboTipo = new JComboBox<>(new String[]{"","A","B","C"});

        txtProducto = new JTextField();
        txtPrecio = new JTextField();
        txtCantidad = new JTextField();

        area = new JTextArea();

        add(new JLabel("Cliente")); add(txtCliente);
        add(new JLabel("Edad")); add(txtEdad);
        add(new JLabel("Teléfono")); add(txtTelefono);
        add(new JLabel("Tipo")); add(comboTipo);

        add(new JLabel("Producto")); add(txtProducto);
        add(new JLabel("Precio")); add(txtPrecio);
        add(new JLabel("Cantidad")); add(txtCantidad);

        JButton btn = new JButton("Comprar");
        btn.addActionListener(e -> c.realizarCompra());

        add(btn);
        add(new JScrollPane(area));
    }

    public void setCoordinador(Coordinador c){ this.c=c; }

    public String getCliente(){ return txtCliente.getText(); }
    public String getEdad(){ return txtEdad.getText(); }
    public String getTelefono(){ return txtTelefono.getText(); }
    public String getTipo(){ return comboTipo.getSelectedItem().toString(); }

    public String getProducto(){ return txtProducto.getText(); }
    public String getPrecio(){ return txtPrecio.getText(); }
    public String getCantidad(){ return txtCantidad.getText(); }

    public void mostrarResultado(String t){ area.setText(t); }
    public void limpiar(){ area.setText(""); }

    public void mensaje(String m){
        JOptionPane.showMessageDialog(this,m);
    }
}
