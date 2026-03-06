package logica;

import entidad.Operario;

public class Procesos {

    public static Operario calcularYGuardar(Operario operario) {
        double sueldo     = operario.getSueldo();
        int    antiguedad = operario.getAntiguedad();

        double sueldoFinal;
        String detalle;

        if (sueldo < 500 && antiguedad >= 10) {
            sueldoFinal = sueldo * 1.20;
            detalle     = "Aumento del 20% por antigüedad >= 10 años";
        } else if (sueldo < 500 && antiguedad < 10) {
            sueldoFinal = sueldo * 1.05;
            detalle     = "Aumento del 5% por sueldo bajo";
        } else {
            sueldoFinal = sueldo;
            detalle     = "Sueldo sin cambios (>= 500)";
        }

        operario.setSueldoFinal(sueldoFinal);
        operario.setDetalle(detalle);
        ModeloDeDatos.agregar(operario);

        return operario;
    }
}
