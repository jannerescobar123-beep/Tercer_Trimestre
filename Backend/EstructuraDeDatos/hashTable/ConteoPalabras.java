package hashTable;

import java.util.Hashtable;

public class ConteoPalabras {
    public void contarFrecuencias(String texto) {
        String[] palabras = texto.toLowerCase().split("\\s+");
        Hashtable<String, Integer> frec = new Hashtable<>();

        for (String p : palabras) {
            if (frec.containsKey(p)) {
                frec.put(p, frec.get(p) + 1);
            } else {
                frec.put(p, 1);
            }
        }

        // Imprimir resultados
        frec.forEach((llave, valor) -> System.out.println(llave + ": " + valor));
    }
}
