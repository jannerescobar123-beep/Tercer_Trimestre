package gui;

import javax.swing.*;
import java.awt.Font;
import java.util.HashMap;
import entidad.Estudiante;

public class VentanaLista extends JFrame {

    JTextArea areaTexto;
    JScrollPane scroll;

    public VentanaLista(HashMap<String, Estudiante> listaEstudiantes) {

        setTitle("Listado de estudiantes");
        setSize(500, 400);
        setLocationRelativeTo(null);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 14));

        scroll = new JScrollPane(areaTexto);
        add(scroll);

        mostrarLista(listaEstudiantes);
    }

    private void mostrarLista(HashMap<String, Estudiante> lista) {

        String aprobados = "Estudiantes Aprobados \n\n";
        String reprobados = "\nEstudiantes Reprobados \n\n";

        for (Estudiante e : lista.values()) {
            if (e.getPromedio() >= 3.5) {
                aprobados += "Documento: " + e.getDocumento() + "\n";
                aprobados += "Nombre: "    + e.getNombre()    + "\n";
                aprobados += "Materia: "   + e.getMateria()   + "\n";
                aprobados += "Promedio: "  + e.getPromedio()  + "\n";
                aprobados += "Estado: "    + e.getResultado() + "\n";
                aprobados += "------------------------\n";
            } else {
                reprobados += "Documento: " + e.getDocumento() + "\n";
                reprobados += "Nombre: "    + e.getNombre()    + "\n";
                reprobados += "Materia: "   + e.getMateria()   + "\n";
                reprobados += "Promedio: "  + e.getPromedio()  + "\n";
                reprobados += "Estado: "    + e.getResultado() + "\n";
                reprobados += "------------------------\n";
            }
        }

        areaTexto.setText(aprobados + reprobados);
    }
}