
public class Procesos {

    public Procesos() {

    }

    public double calcularPromedio(double n1, double n2, double n3) {
        double prom = (n1 + n2 + n3) / 3;
        return prom;
    }

    public void calcularResultado(Estudiante miEstudiante) {
        String resultado = "";

        if (miEstudiante.getPromedio() >= 3.5) {
            resultado = miEstudiante.getNombre() + " Gana la materia";
        } else {
            resultado = miEstudiante.getNombre() + " Pierde la materia";

            if (miEstudiante.getPromedio() > 2.5) {
                resultado += " - Puede recuperar";
            } else {
                resultado += " - No puede recuperar la materia";
            }
        }

        miEstudiante.setResultado(resultado);
    }
}
