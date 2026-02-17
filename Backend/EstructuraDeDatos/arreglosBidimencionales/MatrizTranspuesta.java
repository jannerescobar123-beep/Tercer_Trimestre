package arreglosBidimencionales;

public class MatrizTranspuesta {
    public static void main(String[] args) {
       // Dada una matriz de m X n (por ejemplo, 2 filas y 3 columnas), crea una nueva matriz que sea su transpuesta. Transponer una matriz significa que las filas de la original se conviertan en las columnas de la nueva.
        int[][] matrizOriginal = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int filasOriginal = matrizOriginal.length;
        int columnasOriginal = matrizOriginal[0].length;

        int[][] matrizTranspuesta = new int[columnasOriginal][filasOriginal];

        for (int i = 0; i < filasOriginal; i++) {
            for (int j = 0; j < columnasOriginal; j++) {
                matrizTranspuesta[j][i] = matrizOriginal[i][j];
            }
        }

        // la matriz transpuesta
        System.out.println("Matriz Original:");
        for (int i = 0; i < filasOriginal; i++) {
            for (int j = 0; j < columnasOriginal; j++) {
                System.out.print(matrizOriginal[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Matriz Transpuesta:");
        for (int i = 0; i < columnasOriginal; i++) {
            for (int j = 0; j < filasOriginal; j++) {
                System.out.print(matrizTranspuesta[i][j] + " ");
            }
            System.out.println();
        }
    }
}
