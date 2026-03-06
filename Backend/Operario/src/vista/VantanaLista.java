package vista;

import entidad.Operario;
import logica.ModeloDeDatos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VantanaLista extends JDialog {

    private static final long serialVersionUID = 1L;

    public VantanaLista(Frame owner) {
        super(owner, "Lista de Operarios", true);
        setBounds(120, 120, 750, 320);
        setLayout(new BorderLayout());

        ArrayList<Operario> lista = ModeloDeDatos.getLista();

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(owner,
                "No hay operarios registrados.",
                "Lista vacía", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            return;
        }

        String[] columnas = { "Nombre", "Sueldo Original", "Antigüedad", "Sueldo Final", "Detalle" };

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        for (Operario o : lista) {
            modelo.addRow(new Object[]{
                o.getNombre(),
                String.format("$ %.2f", o.getSueldo()),
                o.getAntiguedad() + " años",
                String.format("$ %.2f", o.getSueldoFinal()),
                o.getDetalle()
            });
        }

        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(26);
        tabla.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));

        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, col);
                String detalle = (String) t.getValueAt(row, 4);
                if (detalle.contains("20%")) {
                    c.setForeground(new Color(0, 130, 0));
                } else if (detalle.contains("5%")) {
                    c.setForeground(new Color(0, 80, 200));
                } else {
                    c.setForeground(Color.DARK_GRAY);
                }
                return c;
            }
        });

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
