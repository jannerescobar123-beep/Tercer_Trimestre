package gui;

import entidad.Estudiante;
import logica.Procesos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		super(owner, "Registrar Promedio", true);
		setBounds(150, 150, 420, 380);
		setLayout(new BorderLayout());

		JPanel panel = new JPanel(new GridLayout(8, 2, 8, 8));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

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

		add(panel, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		JButton btnCalcular = new JButton("Calcular");
		JButton btnGuardar = new JButton("Guardar");
		JButton btnLimpiar = new JButton("Limpiar");
		panelBotones.add(btnCalcular);
		panelBotones.add(btnGuardar);
		panelBotones.add(btnLimpiar);
		add(panelBotones, BorderLayout.SOUTH);

		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double n1 = Double.parseDouble(txtNota1.getText().trim());
					double n2 = Double.parseDouble(txtNota2.getText().trim());
					double n3 = Double.parseDouble(txtNota3.getText().trim());
					double prom = (n1 + n2 + n3) / 3;
					txtPromedio.setText(String.format("%.2f", prom));
					txtEstado.setText(prom >= 3.0 ? "Aprobado" : "Reprobado");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(VentanaPromedio.this,
							"Las notas deben ser números válidos.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = txtNombre.getText().trim();
					String documento = txtDocumento.getText().trim();
					String materia = txtMateria.getText().trim();

					if (nombre.isEmpty() || documento.isEmpty() || materia.isEmpty()) {
						JOptionPane.showMessageDialog(VentanaPromedio.this,
								"Por favor completa todos los campos.",
								"Campos vacíos", JOptionPane.WARNING_MESSAGE);
						return;
					}

					double n1 = Double.parseDouble(txtNota1.getText().trim());
					double n2 = Double.parseDouble(txtNota2.getText().trim());
					double n3 = Double.parseDouble(txtNota3.getText().trim());

					Estudiante est = Procesos.crearEstudiante(nombre, documento, materia, n1, n2, n3);

					JOptionPane.showMessageDialog(VentanaPromedio.this,
							"Estudiante guardado.\nPromedio: " + String.format("%.2f", est.getPromedio()) +
									" — " + est.getEstado(),
							"Éxito", JOptionPane.INFORMATION_MESSAGE);

					limpiar();

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(VentanaPromedio.this,
							"Las notas deben ser números válidos.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
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
