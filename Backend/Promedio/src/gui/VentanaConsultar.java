package gui;

import javax.swing.*;
import entidad.Estudiante;
import java.awt.Font;

public class VentanaConsultar extends JFrame {

    JTextArea area;

    public VentanaConsultar(Estudiante estudiante) {

        setTitle("Consultar Estudiante");
        setSize(400, 300);
        setLocationRelativeTo(null);

        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));

        add(area);

        if (estudiante != null) {
            area.setText(
                "Nombre: "    + estudiante.getNombre()    + "\n" +
                "Documento: " + estudiante.getDocumento() + "\n" +
                "Materia: "   + estudiante.getMateria()   + "\n" +
                "Nota 1: "    + estudiante.getNota1()     + "\n" +
                "Nota 2: "    + estudiante.getNota2()     + "\n" +
                "Nota 3: "    + estudiante.getNota3()     + "\n" +
                "Promedio: "  + estudiante.getPromedio()  + "\n" +
                "Resultado: " + estudiante.getResultado()
            );
        } else {
            area.setText("Estudiante no encontrado");
        }
    }
}