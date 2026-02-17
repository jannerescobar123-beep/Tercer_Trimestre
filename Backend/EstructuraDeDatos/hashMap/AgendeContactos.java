package hashMap;

import java.util.HashMap;

public class AgendeContactos {
    public static void main(String[] args) {
        HashMap<String, String> agenda = new HashMap<>();

        agenda.put("Janner", "3103757810");
        agenda.put("Oscar", "3042194109");
        agenda.put("Francy", "3103757810");

        String nombre = "Janner";
        if (agenda.containsKey(nombre)) {
            System.out.println("El número de " + nombre + " es: " + agenda.get(nombre));
        } else {
            System.out.println(nombre + " no está en la agenda.");
        }
        System.out.println("Agenda completa: " + agenda);
    }
}
