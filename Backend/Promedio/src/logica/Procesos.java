package logica;

import java.util.HashMap;
import entidad.Estudiante;

public class Procesos {

    private HashMap<String, Estudiante> listaEstudiantes = new HashMap<>();

    public String guardarEstudiante(Estudiante e) {
        String llave = e.getDocumento() + "-" + e.getMateria();

        if (listaEstudiantes.containsKey(llave)) {
            return "existente";
        } else {
            listaEstudiantes.put(llave, e);
            return "guardado";
        }
    }

    public void actualizarEstudiante(Estudiante e) {
        String llave = e.getDocumento() + "-" + e.getMateria();
        listaEstudiantes.replace(llave, e);
    }

    public Estudiante consultarEstudiante(String documento, String materia) {
        String llave = documento + "-" + materia;
        return listaEstudiantes.get(llave);
    }

    public HashMap<String, Estudiante> obtenerLista() {
        return listaEstudiantes;
    }

    public double calcularPromedio(double n1, double n2, double n3) {
        return (n1 + n2 + n3) / 3;
    }

    public void calcularResultado(Estudiante e) {
        String resultado;

        if (e.getPromedio() >= 3.5) {
            resultado = e.getNombre() + " - Gana la materia";
        } else {
            resultado = e.getNombre() + " - Pierde la materia";

            if (e.getPromedio() > 2.5) {
                resultado += " - Puede recuperar";
            } else {
                resultado += " - No puede recuperar";
            }
        }

        e.setResultado(resultado);
    }
}