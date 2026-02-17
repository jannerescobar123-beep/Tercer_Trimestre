package Collections;

import java.util.HashMap;

public class Inventario {
    public static void main(String[] args) {
        HashMap<String, Double> productos = new HashMap<>();
        
        productos.put("Manzana", 1.50);
        productos.put("Pan", 0.80);
        productos.put("Leche", 1.20);

        String busca = "Pan";
        if (productos.containsKey(busca)) {
            System.out.println("El precio de " + busca + " es: $" + productos.get(busca));
        }
        // imprime todo el inventario
        System.out.println("Inventario completo: " + productos);
    }
}