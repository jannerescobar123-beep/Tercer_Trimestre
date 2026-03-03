package gui;

import logica.Procesos;
import entidad.Estudiante;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaPromedio extends JDialog {
	private static final long serialVersionUID = 1L;

	private JTextField txtNombre = new JTextField();
	private JTextField txtDocumento = new JTextField();
	private JTextField txtMateria = new JTextField();
	private JTextField txtNota1 = new JTextField();
	private JTextField txtNota2 = new JTextField();
	private JTextField txtNota3 = new JTextField();
	private JTextField txtPromedio = new JTextField();
	private JTextField txtEstado = new JTextField();

	public VentanaPromedio(Frame owner) {
		super(owner, "Calcular Promedio", true);
		setBounds(150, 150, 420, 360);

		JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
		panel.setBorder(new EmptyBorder(20, 20, 10, 20));

		panel.add(new JLabel("Nombre:"));
		panel.add(txtNombre);
		panel.add(new JLabel("Documento:"));
		panel.add(txtDocumento);
		panel.add(new JLabel("Materia:"));
		panel.add(txtMateria);
		panel.add(new JLabel("Nota 1:"));
		panel.add(txtNota1);
		panel.add(new JLabel("Nota 2:"));
		panel.add(txtNota2);
		panel.add(new JLabel("Nota 3:"));
		panel.add(txtNota3);
		panel.add(new JLabel("Promedio:"));
		panel.add(txtPromedio);
		panel.add(new JLabel("Estado:"));
		panel.add(txtEstado);

		txtPromedio.setEditable(false);
		txtEstado.setEditable(false);

		JButton btnCalcular = new JButton("Calcular");
		JButton btnGuardar = new JButton("Guardar");
		JButton btnLimpiar = new JButton("Limpiar");

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		panelBotones.add(btnCalcular);
		panelBotones.add(btnGuardar);
		panelBotones.add(btnLimpiar);

		add(panel, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);

		btnCalcular.addActionListener(e -> {
			try {
				double prom = (Double.parseDouble(txtNota1.getText().trim()) +
						Double.parseDouble(txtNota2.getText().trim()) +
						Double.parseDouble(txtNota3.getText().trim())) / 3;
				txtPromedio.setText(String.format("%.2f", prom));
				txtEstado.setText(prom >= 3.0 ? "Aprobado" : "Reprobado");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Las notas deben ser números válidos.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		btnGuardar.addActionListener(e -> {
			try {
				if (txtNombre.getText().isBlank() || txtDocumento.getText().isBlank()
						|| txtMateria.getText().isBlank()) {
					JOptionPane.showMessageDialog(this, "Completa todos los campos.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				Estudiante est = Procesos.crearEstudiante(
						txtNombre.getText().trim(), txtDocumento.getText().trim(), txtMateria.getText().trim(),
						Double.parseDouble(txtNota1.getText().trim()),
						Double.parseDouble(txtNota2.getText().trim()),
						Double.parseDouble(txtNota3.getText().trim()));
				JOptionPane.showMessageDialog(this,
						"Guardado: " + est.getNombre() + "\nPromedio: " + String.format("%.2f", est.getPromedio())
								+ " — " + est.getEstado(),
						"Éxito", JOptionPane.INFORMATION_MESSAGE);
				limpiar();
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Las notas deben ser números válidos.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		btnLimpiar.addActionListener(e -> limpiar());
	}

	private void limpiar() {
		txtNombre.setText("");
		txtDocumento.setText("");
		txtMateria.setText("");
		txtNota1.setText("");
		txtNota2.setText("");
		txtNota3.setText("");
		txtPromedio.setText("");
		txtEstado.setText("");
	}
}
