package logica;

import entidad.Estudiante;

public class Procesos {
    public static Estudiante crearEstudiante(String nombre, String documento, String materia, double nota1,double nota2, double nota3) {
        Estudiante e = new Estudiante(nombre, documento, materia, nota1, nota2, nota3);
        ModeloDatos.agregar(e);
        return e;
    }
}
