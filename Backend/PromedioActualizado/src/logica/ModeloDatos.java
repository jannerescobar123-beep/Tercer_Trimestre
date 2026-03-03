package logica;

import entidad.Estudiante;
import java.util.ArrayList;

public class ModeloDatos {
    private static ArrayList<Estudiante> lista = new ArrayList<>();

    public static void agregar(Estudiante e) {
        lista.add(e);
    }

    public static ArrayList<Estudiante> getLista() {
        return lista;
    }
}