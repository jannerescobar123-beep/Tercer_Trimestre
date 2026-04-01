package gui;

import javax.swing.*;
import java.awt.*;
import controlador.Coordinador;

public class VentanaRegistro extends JDialog {

    private Coordinador c;
    private JTextField user, pass, rol;

    public VentanaRegistro(JFrame p, boolean m){
        super(p,"Registro",m);
        setSize(250,200);
        setLayout(new GridLayout(4,2));

        user = new JTextField();
        pass = new JTextField();
        rol = new JTextField();

        add(new JLabel("Usuario")); add(user);
        add(new JLabel("Clave")); add(pass);
        add(new JLabel("Rol")); add(rol);

        JButton btn = new JButton("Registrar");
        btn.addActionListener(e -> c.registrar());
        add(btn);
    }

    public void setCoordinador(Coordinador c){ this.c=c; }

    public String getUser(){ return user.getText(); }
    public String getPass(){ return pass.getText(); }
    public String getRol(){ return rol.getText(); }

    public void mensaje(String m){
        JOptionPane.showMessageDialog(this,m);
    }
}
