package gui;

import controlador.Coordinador;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Permite crear una cuenta nueva con rol 'usuario'
// Al terminar regresa al login
public class VentanaRegistro extends JFrame {

    private final Coordinador coordinador;
    private final VentanaLogin ventanaLogin;

    private JTextField txtUsuario, txtNombre, txtApellido;
    private JTextField txtEdad, txtEmail, txtTelefono;
    private JPasswordField txtPassword, txtConfirmar;
    private JComboBox<String> cboTipo;

    public VentanaRegistro(Coordinador coordinador, VentanaLogin ventanaLogin) {
        this.coordinador = coordinador;
        this.ventanaLogin = ventanaLogin;
        initUI();
    }

    private void initUI() {
        setTitle("Crear cuenta");
        setSize(420, 500);
        setLocationRelativeTo(ventanaLogin);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 4, 5, 4);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1.0;

        JLabel titulo = new JLabel("Registro de nuevo usuario");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 14));
        titulo.setForeground(new Color(0x3B4FC8));
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        panel.add(titulo, g);
        g.gridwidth = 1;

        // Campos del formulario
        txtUsuario = campo(panel, g, "Username *", 1, new JTextField());
        txtPassword = campoPass(panel, g, "Contraseña *", 2, new JPasswordField());
        txtConfirmar = campoPass(panel, g, "Confirmar *", 3, new JPasswordField());
        txtNombre = campo(panel, g, "Nombre *", 4, new JTextField());
        txtApellido = campo(panel, g, "Apellido *", 5, new JTextField());
        txtEdad = campo(panel, g, "Edad *", 6, new JTextField());
        txtEmail = campo(panel, g, "Email *", 7, new JTextField());
        txtTelefono = campo(panel, g, "Teléfono", 8, new JTextField());

        // Tipo de afiliacion
        g.gridx = 0;
        g.gridy = 9;
        panel.add(new JLabel("Tipo afiliación:"), g);
        cboTipo = new JComboBox<>(new String[] { "(Sin tipo)", "A", "B", "C" });
        g.gridx = 1;
        panel.add(cboTipo, g);

        JButton btnCrear = new JButton("Crear cuenta");
        btnCrear.setBackground(new Color(0x3B4FC8));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnCrear.setFocusPainted(false);
        g.gridx = 0;
        g.gridy = 10;
        g.gridwidth = 2;
        panel.add(btnCrear, g);
        btnCrear.addActionListener(e -> accionRegistrar());

        add(panel);
    }

    private void accionRegistrar() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtPassword.getPassword());
        String confirm = new String(txtConfirmar.getPassword());
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String edadStr = txtEdad.getText().trim();
        String email = txtEmail.getText().trim();
        String tel = txtTelefono.getText().trim();
        String tipo = cboTipo.getSelectedItem().toString();

        // Validaciones
        if (user.isEmpty() || pass.isEmpty() || nombre.isEmpty()
                || apellido.isEmpty() || edadStr.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa los campos obligatorios (*).");
            return;
        }
        if (!pass.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
            return;
        }
        if (pass.length() < 4) {
            JOptionPane.showMessageDialog(this, "Contraseña mínimo 4 caracteres.");
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número.");
            return;
        }

        // Registra el usuario con rol 'usuario'
        boolean ok = coordinador.registrarUsuario(user, pass, "usuario");
        if (!ok) {
            JOptionPane.showMessageDialog(this, "El username ya existe.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtiene id del usuario recién creado para vincular el cliente
        var sesion = coordinador.login(user, pass);
        coordinador.logout();

        if (sesion != null) {
            // Registra el cliente con todos sus datos incluido el tipo
            coordinador.registrarClienteConUsuario(
                    nombre, apellido, edad, tel,
                    tipo.equals("(Sin tipo)") ? null : tipo,
                    email, sesion.getId());
        }

        JOptionPane.showMessageDialog(this, "Cuenta creada. Ya puedes iniciar sesión.");
        dispose();
        ventanaLogin.setVisible(true);
    }

    // Agrega campo de texto con etiqueta al panel
    private JTextField campo(JPanel p, GridBagConstraints g,
            String lbl, int fila, JTextField f) {
        g.gridx = 0;
        g.gridy = fila;
        g.gridwidth = 1;
        p.add(new JLabel(lbl), g);
        f.setFont(new Font("SansSerif", Font.PLAIN, 13));
        g.gridx = 1;
        p.add(f, g);
        return f;
    }

    // Agrega campo de contraseña con etiqueta al panel
    private JPasswordField campoPass(JPanel p, GridBagConstraints g,
            String lbl, int fila, JPasswordField f) {
        g.gridx = 0;
        g.gridy = fila;
        g.gridwidth = 1;
        p.add(new JLabel(lbl), g);
        f.setFont(new Font("SansSerif", Font.PLAIN, 13));
        g.gridx = 1;
        p.add(f, g);
        return f;
    }
}