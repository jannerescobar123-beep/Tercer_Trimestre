import javax.swing.JOptionPane;

public class InvertirArray {
    public static void main(String[] args) {
        int tamaño = Integer.parseInt(JOptionPane.showInputDialog("CUANTOS NUMEROS DESEA INGRESAR"));
        int[] numeros = new int[tamaño];
        int [] numerosInvertidos = new int[tamaño];
        

        for(int i = 0; i < numeros.length; i++){
            numeros[i] = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el número" + (i + 1) + ":"));
        }
        for (int i = 0; i < numeros.length; i++) {
            numerosInvertidos[i] = numeros[numeros.length - 1 - i];
        }
        String mensaje = "Array invertido: " + java.util.Arrays.toString(numerosInvertidos);
        JOptionPane.showInputDialog(mensaje);
    }
}
