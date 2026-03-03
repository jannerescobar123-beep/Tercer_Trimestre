package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;

    public VentanaPrincipal() {
        setTitle("Sistema de Promedios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 280);

        JPanel panel = new JPanel(new BorderLayout(10, 20));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(panel);

        JLabel titulo = new JLabel("Bienvenido al Sistema de Promedios", SwingConstants.CENTER);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel botones = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton btnPromedio = new JButton("Registrar promedio de estudiante");
        JButton btnInfo     = new JButton("Ver información del programa");
        JButton btnLista    = new JButton("Mostrar lista de estudiantes");
        botones.add(btnPromedio);
        botones.add(btnInfo);
        botones.add(btnLista);
        panel.add(botones, BorderLayout.CENTER);

        btnPromedio.addActionListener(e -> new VentanaPromedio(this).setVisible(true));

        btnInfo.addActionListener(e -> JOptionPane.showMessageDialog(this,
            "Sistema de promedios.\nHecha por Janner Andrey Carvajal Escobar \n" +
            "Permite registrar notas y calcular promedios.",
            "Información", JOptionPane.INFORMATION_MESSAGE));

        btnLista.addActionListener(e -> new VentanaInformacion(this).setVisible(true));
    }
}
