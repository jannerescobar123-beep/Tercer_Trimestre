package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    public VentanaPrincipal() {
        setTitle("Sistema de Promedios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Bienvenido al Sistema de Promedios", SwingConstants.CENTER);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));

        JButton btnPromedio = new JButton("Registrar promedio");
        JButton btnLista    = new JButton("Consultar lista de estudiantes");
        JButton btnAcerca   = new JButton("Acerca de");

        panelBotones.add(btnPromedio);
        panelBotones.add(btnLista);
        panelBotones.add(btnAcerca);
        add(panelBotones, BorderLayout.CENTER);

        btnPromedio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaPromedio vp = new VentanaPromedio(VentanaPrincipal.this);
                vp.setVisible(true);
            }
        });

        btnLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaInformacion vi = new VentanaInformacion(VentanaPrincipal.this);
                vi.setVisible(true);
            }
        });

        btnAcerca.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaAcerca va = new VentanaAcerca(VentanaPrincipal.this);
                va.setVisible(true);
            }
        });
    }
}