package arraysList;

import java.util.ArrayList;
import java.util.Arrays;

public class NumerosPrimos {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10));
        
        System.out.println("Lista original: " + numeros);

        numeros.removeIf(n -> !esPrimo(n));

        System.out.println("Números primos en el ArrayList:");
        for (int n : numeros) {
            System.out.println(n);
        }
    }
    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}
