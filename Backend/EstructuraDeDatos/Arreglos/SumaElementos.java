
import javax.swing.JOptionPane;

class SumaElementos{
	public static void main(String[] args) {
		int[] numeros = new int [5];
		int suma = 0;
		for (int i = 0; i < numeros.length; i++) {
			numeros[i] = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un numero: "));
			suma += numeros[i];
		}
		JOptionPane.showMessageDialog(null, "La suma de los elementos es: " + suma);
	}
}

