package logica;

import java.util.ArrayList;

import entidad.Operario;

public class ModeloDeDatos {
	
private static ArrayList<Operario> lista = new ArrayList<>();

public static void agregar(Operario o){ 
	lista.add(o); 
}

public static ArrayList<Operario> getLista() { 
	return lista; 
}

public static int totalOperarios(){
	return lista.size(); 
}

}
