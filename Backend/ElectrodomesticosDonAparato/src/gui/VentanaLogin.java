package gui;

import javax.swing.*;
import java.awt.*;
import controlador.Coordinador;

public class VentanaLogin extends JDialog {

    private Coordinador c;
    private JTextField user;
    private JTextField pass;

    public VentanaLogin(JFrame p, boolean m){
        super(p,"Login",m);
        setSize(250,150);
        setLayout(new GridLayout(3,2));

        user = new JTextField();
        pass = new JTextField();

        add(new JLabel("Usuario"));
        add(user);
        add(new JLabel("Clave"));
        add(pass);

        JButton btn = new JButton("Entrar");
        btn.addActionListener(e -> c.login());
        add(btn);
    }

    public void setCoordinador(Coordinador c){ this.c=c; }

    public String getUser(){ return user.getText(); }
    public String getPass(){ return pass.getText(); }

    public void mensaje(String m){
        JOptionPane.showMessageDialog(this,m);
    }
}
