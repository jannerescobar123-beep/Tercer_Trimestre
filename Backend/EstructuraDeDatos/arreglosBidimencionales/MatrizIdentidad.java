package arreglosBidimencionales;
import javax.swing.JOptionPane;

public class MatrizIdentidad {
    public static void main(String[] args) {
        // Crea una matriz del tamaño que pida el usuario y llénala con la matriz identidad (1 en la diagonal principal y 0 en el resto).

        int tamaño = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tamaño de la matriz identidad:"));
        int[][] matrizIdentidad = new int[tamaño][tamaño];

        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (i == j) {
                    matrizIdentidad[i][j] = 1; //  Se coloca 1 en la diagonal principal
                } else {
                    matrizIdentidad[i][j] = 0; //Se coloca 0 en el resto
                }
            }
        }

        // Imprime la matriz identidad
        System.out.println("Matriz Identidad de " + tamaño + "x" + tamaño + ":");
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                System.out.print(matrizIdentidad[i][j] + " ");
            }
            System.out.println();
        }

    }
}
