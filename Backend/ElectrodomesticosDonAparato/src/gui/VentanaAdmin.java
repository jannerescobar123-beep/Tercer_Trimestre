package gui;

import javax.swing.*;
import java.awt.*;
import controlador.Coordinador;

public class VentanaAdmin extends JDialog {

    private Coordinador c;
    private JTextArea areaUsuarios;

    public VentanaAdmin(JFrame parent, boolean modal){
        super(parent,"Admin",modal);
        setSize(350,300);
        setLayout(new BorderLayout());

        areaUsuarios = new JTextArea();
        JScrollPane scroll = new JScrollPane(areaUsuarios);

        JButton btnListar = new JButton("Ver usuarios");

        btnListar.addActionListener(e -> {
            System.out.println("Listando usuarios...");
            c.listarUsuarios();
        });

        add(btnListar, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    public void setCoordinador(Coordinador c){ this.c=c; }

    public void mostrarUsuarios(String txt){
        areaUsuarios.setText(txt);
    }
}
