package entidad;

public class Estudiante {
    private String nombre, documento, materia;
    private double nota1, nota2, nota3, promedio;
    private String estado;

    public Estudiante(String nombre, String documento, String materia, double nota1, double nota2, double nota3) {
        this.nombre = nombre;
        this.documento = documento;
        this.materia = materia;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.promedio = (nota1 + nota2 + nota3) / 3;
        this.estado = this.promedio >= 3.0 ? "Aprobado" : "Reprobado";
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getMateria() {
        return materia;
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public double getPromedio() {
        return promedio;
    }

    public String getEstado() {
        return estado;
    }
}