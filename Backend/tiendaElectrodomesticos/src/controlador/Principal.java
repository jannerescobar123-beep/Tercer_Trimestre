package controlador;

import gui.VentanaLogin;

// Punto de entrada de la app

public class Principal {
    public static void main(String[] args) {
        Coordinador coordinador = new Coordinador();
        new VentanaLogin(coordinador).setVisible(true);
    }
}