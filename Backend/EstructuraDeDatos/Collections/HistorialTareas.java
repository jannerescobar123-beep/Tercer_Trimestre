package Collections;

import java.util.ArrayList;

public class HistorialTareas {
    public static void main(String[] args) {
        ArrayList<String> tareas = new ArrayList<>();
        tareas.add("Estudiar Java");
        tareas.add("Ir al gimnasio");
        tareas.add("Comprar café");

        tareas.remove(1); 

        System.out.println("Tareas pendientes: " + tareas);
    }
}