import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VentanaPrincipal extends JFrame implements ActionListener {

   
	JPanel miPanel;
    JLabel lblNombre, lblNota1, lblNota2, lblNota3, lblResultado, lblMensaje;
    JTextField txtNombre, txtNota1, txtNota2, txtNota3, txtResultado, txtMensaje;
    JButton btnCalcular, btnLimpiar;

    public VentanaPrincipal() {
        iniciarComponentes();
        setTitle("Calcular el promedio del estudiante");
        setSize(400, 360);
        setLocationRelativeTo(null);
    }

    private void iniciarComponentes() {

        miPanel = new JPanel();
        miPanel.setLayout(null);
        add(miPanel);

        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 80, 30);
        miPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 20, 200, 30);
        miPanel.add(txtNombre);

        lblNota1 = new JLabel("Nota 1:");
        lblNota1.setBounds(20, 70, 80, 30);
        miPanel.add(lblNota1);

        txtNota1 = new JTextField();
        txtNota1.setBounds(100, 70, 80, 30);
        miPanel.add(txtNota1);

        lblNota2 = new JLabel("Nota 2:");
        lblNota2.setBounds(200, 70, 80, 30);
        miPanel.add(lblNota2);

        txtNota2 = new JTextField();
        txtNota2.setBounds(260, 70, 80, 30);
        miPanel.add(txtNota2);

        lblNota3 = new JLabel("Nota 3:");
        lblNota3.setBounds(20, 120, 80, 30);
        miPanel.add(lblNota3);

        txtNota3 = new JTextField();
        txtNota3.setBounds(100, 120, 80, 30);
        miPanel.add(txtNota3);

        lblResultado = new JLabel("Promedio:");
        lblResultado.setBounds(20, 165, 80, 30);
        miPanel.add(lblResultado);

        txtResultado = new JTextField();
        txtResultado.setBounds(100, 165, 200, 30);
        txtResultado.setEditable(false);
        miPanel.add(txtResultado);

        lblMensaje = new JLabel("Estado:");
        lblMensaje.setBounds(20, 210, 80, 30);
        miPanel.add(lblMensaje);

        txtMensaje = new JTextField();
        txtMensaje.setBounds(100, 210, 250, 30);
        txtMensaje.setEditable(false);
        miPanel.add(txtMensaje);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(100, 260, 100, 30);
        btnLimpiar.addActionListener(this);
        miPanel.add(btnLimpiar);

        btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(220, 260, 120, 30);
        btnCalcular.addActionListener(this);
        miPanel.add(btnCalcular);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnCalcular) {

            String nombre = txtNombre.getText();
            double nota1 = Double.parseDouble(txtNota1.getText());
            double nota2 = Double.parseDouble(txtNota2.getText());
            double nota3 = Double.parseDouble(txtNota3.getText());

            Estudiante miEstudiante = new Estudiante();
            miEstudiante.setNombre(nombre);
            miEstudiante.setNota1(nota1);
            miEstudiante.setNota2(nota2);
            miEstudiante.setNota3(nota3);

            Procesos miProceso = new Procesos();
            double promedio = miProceso.calcularPromedio(nota1, nota2, nota3);
            miEstudiante.setPromedio(promedio);

            txtResultado.setText(String.valueOf(promedio));

            miProceso.calcularResultado(miEstudiante);
            txtMensaje.setText(miEstudiante.getResultado());
        }

        if (e.getSource() == btnLimpiar) {
            txtNombre.setText("");
            txtNota1.setText("");
            txtNota2.setText("");
            txtNota3.setText("");
            txtResultado.setText("");
            txtMensaje.setText("");
        }
    }
}