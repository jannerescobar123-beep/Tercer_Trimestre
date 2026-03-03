package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import entidad.Estudiante;
import logica.Procesos;
import java.awt.Font;

public class VentanaPrincipal extends JFrame implements ActionListener {

    JPanel miPanel;
    JLabel lblNombre, lblNota1, lblNota2, lblNota3, lblResultado, lblMensaje;
    JTextField txtNombre, txtNota1, txtNota2, txtNota3, txtResultado, txtMensaje;
    JButton btnCalcular, btnLimpiar;

    Procesos miProceso = new Procesos();
    private JTextField txtMateria;
    private JTextField txtDocument;
    private JButton btnLista;
    private JButton btnConsultar;

    public VentanaPrincipal() {
        iniciarComponentes();
        setTitle("Calcular el promedio del estudiante");
        setSize(872, 399);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void limpiar() {
        txtNombre.setText("");
        txtMateria.setText("");
        txtNota1.setText("");
        txtNota2.setText("");
        txtNota3.setText("");
        txtResultado.setText("");
        txtMensaje.setText("");
        txtDocument.setText("");
    }

    private void iniciarComponentes() {

        miPanel = new JPanel();
        getContentPane().add(miPanel);
        miPanel.setLayout(null);

        JLabel lblTitulo = new JLabel("Calcular el promedio del estudiante");
        lblTitulo.setBounds(230, 38, 469, 30);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        miPanel.add(lblTitulo);

        JLabel lblDocument = new JLabel("Documento:");
        lblDocument.setBounds(560, 107, 80, 30);
        miPanel.add(lblDocument);

        txtDocument = new JTextField();
        txtDocument.setBounds(648, 108, 200, 30);
        miPanel.add(txtDocument);

        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 107, 80, 30);
        miPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(80, 108, 200, 30);
        miPanel.add(txtNombre);

        JLabel lblMateria = new JLabel("Materia:");
        lblMateria.setBounds(290, 107, 80, 30);
        miPanel.add(lblMateria);

        txtMateria = new JTextField();
        txtMateria.setBounds(350, 108, 200, 30);
        miPanel.add(txtMateria);

        lblNota1 = new JLabel("Nota 1:");
        lblNota1.setBounds(97, 160, 80, 30);
        miPanel.add(lblNota1);

        txtNota1 = new JTextField();
        txtNota1.setBounds(148, 161, 80, 30);
        miPanel.add(txtNota1);

        lblNota2 = new JLabel("Nota 2:");
        lblNota2.setBounds(252, 160, 80, 30);
        miPanel.add(lblNota2);

        txtNota2 = new JTextField();
        txtNota2.setBounds(301, 161, 80, 30);
        miPanel.add(txtNota2);

        lblNota3 = new JLabel("Nota 3:");
        lblNota3.setBounds(416, 160, 80, 30);
        miPanel.add(lblNota3);

        txtNota3 = new JTextField();
        txtNota3.setBounds(470, 161, 80, 30);
        miPanel.add(txtNota3);

        lblResultado = new JLabel("Promedio:");
        lblResultado.setBounds(20, 240, 92, 30);
        miPanel.add(lblResultado);

        txtResultado = new JTextField();
        txtResultado.setBounds(80, 241, 200, 30);
        txtResultado.setEditable(false);
        miPanel.add(txtResultado);

        lblMensaje = new JLabel("Estado:");
        lblMensaje.setBounds(290, 240, 80, 30);
        miPanel.add(lblMensaje);

        txtMensaje = new JTextField();
        txtMensaje.setBounds(340, 241, 393, 30);
        txtMensaje.setEditable(false);
        miPanel.add(txtMensaje);

        btnCalcular = new JButton("Calcular y Guardar");
        btnCalcular.setBounds(80, 291, 160, 30);
        btnCalcular.addActionListener(this);
        miPanel.add(btnCalcular);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(274, 291, 120, 30);
        btnLimpiar.addActionListener(this);
        miPanel.add(btnLimpiar);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(430, 291, 120, 30);
        btnConsultar.addActionListener(this);
        miPanel.add(btnConsultar);

        btnLista = new JButton("Lista");
        btnLista.setBounds(578, 291, 120, 30);
        btnLista.addActionListener(this);
        miPanel.add(btnLista);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnCalcular) {

            Estudiante miEstudiante = new Estudiante();
            miEstudiante.setDocumento(txtDocument.getText());
            miEstudiante.setNombre(txtNombre.getText());
            miEstudiante.setMateria(txtMateria.getText());
            miEstudiante.setNota1(Double.parseDouble(txtNota1.getText()));
            miEstudiante.setNota2(Double.parseDouble(txtNota2.getText()));
            miEstudiante.setNota3(Double.parseDouble(txtNota3.getText()));

            double promedio = miProceso.calcularPromedio(
                miEstudiante.getNota1(),
                miEstudiante.getNota2(),
                miEstudiante.getNota3()
            );

            miEstudiante.setPromedio(promedio);
            miProceso.calcularResultado(miEstudiante);

            String respuesta = miProceso.guardarEstudiante(miEstudiante);

            if (respuesta.equals("existente")) {

                int opcion = JOptionPane.showConfirmDialog(null,
                    "Este estudiante ya está registrado en esta materia.\n¿Quiere actualizar sus notas?",
                    "Estudiante existente",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

                if (opcion == JOptionPane.YES_OPTION) {
                    miProceso.actualizarEstudiante(miEstudiante);
                    txtResultado.setText(String.format("%.2f", promedio));
                    txtMensaje.setText(miEstudiante.getResultado());
                }

            } else {
                txtResultado.setText(String.format("%.2f", promedio));
                txtMensaje.setText(miEstudiante.getResultado());
            }
        }

        else if (e.getSource() == btnConsultar) {

            Estudiante estudiante = miProceso.consultarEstudiante(
                txtDocument.getText(),
                txtMateria.getText()
            );

            if (estudiante != null) {
                txtNombre.setText(estudiante.getNombre());
                txtMateria.setText(estudiante.getMateria());
                txtNota1.setText(String.valueOf(estudiante.getNota1()));
                txtNota2.setText(String.valueOf(estudiante.getNota2()));
                txtNota3.setText(String.valueOf(estudiante.getNota3()));
                txtResultado.setText(String.format("%.2f", estudiante.getPromedio()));
                txtMensaje.setText(estudiante.getResultado());
            } else {
                JOptionPane.showMessageDialog(null,
                    "Estudiante no encontrado",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            }
        }

        else if (e.getSource() == btnLista) {
            VentanaLista ventana = new VentanaLista(miProceso.obtenerLista());
            ventana.setVisible(true);
        }

        else if (e.getSource() == btnLimpiar) {
            limpiar();
        }
    }
}