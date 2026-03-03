package gui;

import entidad.Estudiante;
import logica.ModeloDatos;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaInformacion extends JDialog {

	private static final long serialVersionUID = 1L;

	public VentanaInformacion(Frame owner) {
		super(owner, "Lista de Estudiantes", true);
		setBounds(100, 100, 750, 300);
		setLayout(new BorderLayout());

		ArrayList<Estudiante> lista = ModeloDatos.getLista();

		if (lista.isEmpty()) {
			JOptionPane.showMessageDialog(owner,
					"No hay estudiantes registrados.",
					"Lista vacía", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			return;
		}

		String[] columnas = { "Nombre", "Documento", "Materia", "Nota 1", "Nota 2", "Nota 3", "Promedio", "Estado" };

		DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		for (Estudiante e : lista) {
			modelo.addRow(new Object[] {
					e.getNombre(),
					e.getDocumento(),
					e.getMateria(),
					String.format("%.1f", e.getNota1()),
					String.format("%.1f", e.getNota2()),
					String.format("%.1f", e.getNota3()),
					String.format("%.2f", e.getPromedio()),
					e.getEstado()
			});
		}

		JTable tabla = new JTable(modelo);
		tabla.setRowHeight(26);
		tabla.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));

		tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable t, Object value,
					boolean isSelected, boolean hasFocus, int row, int col) {
				Component c = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, col);
				String promedio = (String) t.getValueAt(row, 6);
				double prom = Double.parseDouble(promedio.replace(",", "."));
				if (prom >= 3.0) {
					c.setForeground(new Color(0, 80, 200));
				} else {
					c.setForeground(Color.RED);
				}
				return c;
			}
		});

		add(new JScrollPane(tabla), BorderLayout.CENTER);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(e -> dispose());
		JPanel p = new JPanel();
		p.add(btnCerrar);
		add(p, BorderLayout.SOUTH);
	}
}
