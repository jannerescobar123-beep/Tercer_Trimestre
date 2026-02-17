import javax.swing.JOptionPane;

public class EncontrarMayor {
    public static void main(String[] args) {
        int[] numeros = new int[5];
        int mayor = 0;
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un numero: "));
            if (numeros[i] > mayor) {
                mayor = numeros[i];
            }
        }
        JOptionPane.showMessageDialog(null, "El mayor es: " + mayor);
    }
}