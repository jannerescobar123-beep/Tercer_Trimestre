package gui;

import javax.swing.*;
import controlador.Coordinador;

public class VentanaPrincipal extends JFrame {

    private Coordinador c;

    public VentanaPrincipal(){
        setTitle("Sistema");
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> c.mostrarLogin());

        add(btnLogin);
    }

    public void setCoordinador(Coordinador c){ this.c=c; }
}