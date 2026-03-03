package gui;

import logica.ModeloDatos;
import entidad.Estudiante;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaInformacion extends JDialog {
	private static final long serialVersionUID = 1L;

	public VentanaInformacion(Frame owner) {
		super(owner, "Lista de Estudiantes", true);
		setBounds(150, 150, 700, 300);

		ArrayList<Estudiante> lista = ModeloDatos.getLista();

		if (lista.isEmpty()) {
			JOptionPane.showMessageDialog(owner, "No hay estudiantes registrados.", "Lista vacía",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
			return;
		}

		String[] columnas = { "Nombre", "Documento", "Materia", "Nota 1", "Nota 2", "Nota 3", "Promedio", "Estado" };
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};

		for (Estudiante e : lista) {
			modelo.addRow(new Object[] {
					e.getNombre(), e.getDocumento(), e.getMateria(),
					String.format("%.1f", e.getNota1()),
					String.format("%.1f", e.getNota2()),
					String.format("%.1f", e.getNota3()),
					String.format("%.2f", e.getPromedio()),
					e.getEstado()
			});
		}

		JTable tabla = new JTable(modelo);
		tabla.setRowHeight(24);
		tabla.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));

		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(scroll, BorderLayout.CENTER);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(e -> dispose());
		JPanel p = new JPanel();
		p.add(btnCerrar);
		add(p, BorderLayout.SOUTH);
	}
}
