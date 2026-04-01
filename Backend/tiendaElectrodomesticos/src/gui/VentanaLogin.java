package gui;

import controlador.Coordinador;
import modelo.dto.UsuarioDTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Primera ventana que aparece al abrir la app
// se dirige dependiento el rol
public class VentanaLogin extends JFrame {

    private final Coordinador coordinador;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JLabel lblError;

    public VentanaLogin(Coordinador coordinador) {
        this.coordinador = coordinador;
        initUI();
    }

    private void initUI() {
        setTitle("Iniciar Sesión");
        setSize(380, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));

        // Encabezado azul con título
        JPanel header = new JPanel();
        header.setBackground(new Color(0x3B4FC8));
        header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(new EmptyBorder(12, 0, 12, 0));

        JLabel titulo = new JLabel("Tienda Electrodomésticos", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 14));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(titulo);
        panel.add(header);
        panel.add(Box.createVerticalStrut(24));

        // Campo usuario
        panel.add(label("Usuario"));
        panel.add(Box.createVerticalStrut(4));
        txtUsuario = new JTextField();
        txtUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        panel.add(txtUsuario);
        panel.add(Box.createVerticalStrut(12));

        // Campo contraseña
        panel.add(label("Contraseña"));
        panel.add(Box.createVerticalStrut(4));
        txtPassword = new JPasswordField();
        txtPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        txtPassword.addActionListener(e -> accionLogin()); // Enter hace login
        panel.add(txtPassword);
        panel.add(Box.createVerticalStrut(18));

        // Botón entrar
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(new Color(0x3B4FC8));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnEntrar.setFocusPainted(false);
        btnEntrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEntrar.addActionListener(e -> accionLogin());
        panel.add(btnEntrar);
        panel.add(Box.createVerticalStrut(8));

        // Enlace para registrarse
        JButton btnReg = new JButton("¿No tienes cuenta? Regístrate");
        btnReg.setContentAreaFilled(false);
        btnReg.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        btnReg.setForeground(new Color(0x3B4FC8));
        btnReg.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btnReg.setFocusPainted(false);
        btnReg.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReg.addActionListener(e -> new VentanaRegistro(coordinador, this).setVisible(true));
        panel.add(btnReg);
        panel.add(Box.createVerticalStrut(8));

        // Etiqueta de error (vacía hasta que falle el login)
        lblError = new JLabel("", SwingConstants.CENTER);
        lblError.setForeground(new Color(0xCC2200));
        lblError.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblError.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblError);


        panel.add(Box.createVerticalStrut(14));
        JLabel hint = new JLabel("<html><center><font color='#999' size='2'>" +
                "admin/admin123 &nbsp;|&nbsp; juan/user123</font></center></html>",
                SwingConstants.CENTER);
        hint.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(hint);

        add(panel);
    }

    private void accionLogin() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtPassword.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            lblError.setText("Completa usuario y contraseña.");
            return;
        }

        // Coordinador verifica credenciales en BD
        UsuarioDTO sesion = coordinador.login(user, pass);

        if (sesion == null) {
            lblError.setText("Usuario o contraseña incorrectos.");
            txtPassword.setText("");
            return;
        }

        dispose(); // Cierra el login

        // Redirige según el rol
        if (sesion.esAdmin()) {
            new VentanaAdmin(coordinador).setVisible(true);
        } else {
            new VentanaUsuario(coordinador).setVisible(true);
        }
    }

    private JLabel label(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("SansSerif", Font.BOLD, 12));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }
}