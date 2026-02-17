package arreglosBidimencionales;

import javax.swing.JOptionPane;

public class TableroSuma {
    public static void main(String[] args) {
     //  Crea una matriz de 3x3 y pídela al usuario (o llénala con números aleatorios). Luego,
     //  calcula la suma de todos los elementos de la tabla.

        int tamaño = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tamaño de la matriz:"));
        int[][] matriz = new int [tamaño][tamaño];
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor para la posición [" + i + "][" + j + "]:"));
            }
        }
        int suma = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                suma += matriz[i][j];
            }
        }
        JOptionPane.showMessageDialog(null, "La suma de todos los elementos es: " + suma);
    }
}
