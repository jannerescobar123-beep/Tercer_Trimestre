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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	public VentanaPromedio(Frame owner) {
		super(owner, "Registrar Promedio", true);
		setBounds(150, 150, 780, 468);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
		panel.setLayout(null);

		JLabel label = new JLabel("Nombre:");
		label.setBounds(20, 15, 78, 38);
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(label);
		txtNombre.setBounds(96, 15, 269, 38);
		panel.add(txtNombre);
		JLabel label_1 = new JLabel("Documento:");
		label_1.setBounds(375, 15, 94, 38);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(label_1);
		JLabel label_2 = new JLabel("Materia:");
		label_2.setBounds(20, 76, 78, 38);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(label_2);
		JLabel label_3 = new JLabel("Nota 1:");
		label_3.setBounds(20, 155, 78, 38);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(label_3);
		JLabel label_4 = new JLabel("Nota 2:");
		label_4.setBounds(20, 203, 78, 37);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(label_4);
		JLabel label_5 = new JLabel("Nota 3:");
		label_5.setBounds(18, 250, 78, 38);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(label_5);
		JLabel label_6 = new JLabel("Promedio:");
		label_6.setBounds(18, 298, 78, 38);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(label_6);
		JLabel label_7 = new JLabel("Estado:");
		label_7.setBounds(407, 155, 359, 38);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(label_7);

		getContentPane().add(panel, BorderLayout.CENTER);
		
		textField = new JTextField();
		textField.setBounds(473, 15, 283, 38);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setBounds(96, 79, 269, 38);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(96, 155, 269, 38);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(96, 203, 269, 38);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(96, 250, 269, 38);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(96, 298, 269, 38);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setBounds(407, 202, 349, 38);
		panel.add(textField_6);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		JButton btnCalcular = new JButton("Calcular");
		JButton btnGuardar = new JButton("Guardar");
		JButton btnLimpiar = new JButton("Limpiar");
		panelBotones.add(btnCalcular);
		panelBotones.add(btnGuardar);
		panelBotones.add(btnLimpiar);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);

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
