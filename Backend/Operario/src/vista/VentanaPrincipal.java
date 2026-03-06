package vista;

import entidad.Operario;
import logica.Procesos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtNombre     = new JTextField();
    private JTextField txtSueldo     = new JTextField();
    private JTextField txtAntiguedad = new JTextField();
    private JTextField txtResultado  = new JTextField();
    private JTextField txtDetalle    = new JTextField();

    public VentanaPrincipal() {
        setTitle("Cálculo de Aumento Salarial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 420, 360);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        panel.add(new JLabel("Nombre operario:"));  panel.add(txtNombre);
        panel.add(new JLabel("Sueldo actual:"));    panel.add(txtSueldo);
        panel.add(new JLabel("Años antigüedad:"));  panel.add(txtAntiguedad);
        panel.add(new JLabel("Sueldo a pagar:"));   panel.add(txtResultado);
        panel.add(new JLabel("Detalle:"));          panel.add(txtDetalle);

        txtResultado.setEditable(false);
        txtDetalle.setEditable(false);

        JButton btnCalcular = new JButton("Calcular y Guardar");
        JButton btnVerLista = new JButton("Ver Lista");
        JButton btnLimpiar  = new JButton("Limpiar");

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnCalcular);
        panelBotones.add(btnVerLista);
        panelBotones.add(btnLimpiar);

        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre     = txtNombre.getText().trim();
                    double sueldo     = Double.parseDouble(txtSueldo.getText().trim());
                    int    antiguedad = Integer.parseInt(txtAntiguedad.getText().trim());

                    if (nombre.isEmpty()) {
                        JOptionPane.showMessageDialog(VentanaPrincipal.this,
                            "Por favor ingresa el nombre del operario.",
                            "Campo vacío", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    Operario operario  = new Operario(nombre, sueldo, antiguedad);
                    Operario resultado = Procesos.calcularYGuardar(operario);

                    txtResultado.setText(String.format("$ %.2f", resultado.getSueldoFinal()));
                    txtDetalle.setText(resultado.getDetalle());

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this,
                        "Sueldo y antigüedad deben ser números válidos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnVerLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VantanaLista(VentanaPrincipal.this).setVisible(true);
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtNombre.setText("");
                txtSueldo.setText("");
                txtAntiguedad.setText("");
                txtResultado.setText("");
                txtDetalle.setText("");
            }
        });
    }
}