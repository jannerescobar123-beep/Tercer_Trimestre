package logica;

import data.ModeloDatos;

import entidad.Operario;

public class Procesos {
	public static Operario crearOperario(String nombre, double sueldo, int añosAntiguedad, double aumento,String estado) {
		Operario o = new Operario(nombre, sueldo, añosAntiguedad, aumento, estado);
        ModeloDatos.agregar(o);
        return o;
    }
}
