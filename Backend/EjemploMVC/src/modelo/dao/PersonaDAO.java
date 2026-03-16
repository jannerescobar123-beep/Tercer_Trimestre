package modelo.dao;
import java.util.ArrayList;

import controlador.Coordinador;
import modelo.conexion.ConexionDB;
import modelo.dto.PersonaDTO;

public class PersonaDAO {
	
	//La clase PersonaDAO (DAO Data Access Object) es la encargada de acceder a los datos de personas.
	//Es decir, aquí están los métodos que permiten guardar, consultar, actualizar y eliminar personas.
	// 📌 Esta clase no guarda directamente los datos, sino que usa la "base de datos simulada" que está en
	// ConexionBD.personasMap .
	
	
	//Esta línea permite que esta clase se conecte con el Coordinador , pero no lo usa directamente aquí, solo guarda la
	//referencia.
	private Coordinador miCoordinador;
	
	//CRUD = Crear, Leer, Actualizar y Eliminar personas. Vamos uno por uno:
	//Sirve para registrar una persona nueva.
	//Si el documento no existe en el mapa, lo agrega y devuelve "si" .
	// Si ya existe, devuelve "no" (para evitar duplicados).
	public String registrarPersona(PersonaDTO persona) {
	if (ConexionDB.personasMap.containsKey(persona.getDocumento())==false) {
	ConexionDB.personasMap.put(persona.getDocumento(), persona);
	return "si";
	}else {
	return "no";
	}
	}
	
	
	public PersonaDTO consultarPersonaPorDocumento(String documento) {
		//Busca a una persona por su documento.
		////Si no, devuelve null .
	if (ConexionDB.personasMap.containsKey(documento)==true) {
	return ConexionDB.personasMap.get(documento);
	}else {
	return null;
	}
}
	
	
	
	public ArrayList<PersonaDTO> consultarListaPersonas(){
		//Devuelve una lista con todas las personas registradas.
		//Recorre el HashMap y agrega cada persona a una ArrayList .
		ArrayList<PersonaDTO> listaPersonas=new ArrayList<PersonaDTO>();
		for (PersonaDTO persona : ConexionDB.personasMap.values()) {
		listaPersonas.add(persona);
		}
		return listaPersonas;
}
	
	
	
		public String actualizarPersona(PersonaDTO persona) {
			//Devuelve una lista con todas las personas registradas.
			//Recorre el HashMap y agrega cada persona a una ArrayList .
		String resp="";
		System.out.println(persona);
		if (ConexionDB.personasMap.containsKey(persona.getDocumento())) {
			ConexionDB.personasMap.put(persona.getDocumento(), persona); // reemplaza el objeto
		resp="ok";
		} else {
		resp="error";
		}
		return resp;
		}
		
		
		public String eliminarPersona(String documento) {
			
			//Devuelve una lista con todas las personas registradas.
			//Recorre el HashMap y agrega cada persona a una ArrayList .
		if (ConexionDB.personasMap.containsKey(documento)) {
			ConexionDB.personasMap.remove(documento);

		return "ok";
		} else {
		return "error";
		}
		}
		public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador=miCoordinador;
		}
}
