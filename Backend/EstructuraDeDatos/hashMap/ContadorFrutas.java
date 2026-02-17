package hashMap;

import java.util.HashMap;

public class ContadorFrutas {
    public static void main(String[] args) {
        String[] inventario = {"manzana", "pera", "manzana", "naranja", "pera", "manzana"};
        HashMap<String, Integer> conteo = new HashMap<>();

        for (String fruta : inventario) {
            conteo.put(fruta, conteo.getOrDefault(fruta, 0) + 1);
        }

        System.out.println("Resultados: " + conteo);
    }
}