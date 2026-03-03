package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAcerca extends JDialog {

    private static final long serialVersionUID = 1L;

    public VentanaAcerca(Frame owner) {
        super(owner, "Acerca de", true);
        setBounds(200, 200, 350, 220);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

        JLabel lblTitulo = new JLabel("Trabajo istema de Promedios", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel lblVersion = new JLabel("siuuuuuuuu!", SwingConstants.CENTER);
        JLabel lblDesc    = new JLabel("Gestión de notas y promedios de estudiantes", SwingConstants.CENTER);
        JLabel lblAutor   = new JLabel("Desarrollado por Janner Escobar en Java Swing ", SwingConstants.CENTER);
        lblAutor.setForeground(Color.GRAY);

        panel.add(lblTitulo);
        panel.add(lblVersion);
        panel.add(lblDesc);
        panel.add(lblAutor);
        add(panel, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel panelBtn = new JPanel();
        panelBtn.add(btnCerrar);
        add(panelBtn, BorderLayout.SOUTH);
    }
}