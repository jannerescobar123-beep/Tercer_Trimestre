package modelo.conexion;
import java.util.HashMap;
import controlador.Coordinador;
import modelo.dto.PersonaDTO;

public class ConexionDB {
	public static HashMap<String, PersonaDTO> personasMap;
	
	//Relación con el Coordinador
	private Coordinador miCoordinador;
	
	// Constructor
	public ConexionDB() {
	personasMap=new HashMap<String, PersonaDTO>();
	}
	
	// Relación con el Coordinador
	public void setCoordinador(Coordinador miCoordinador) {
	this.miCoordinador=miCoordinador;
	}
}

//🔐 HashMap<String, PersonaDTO> personasMap
//📌 Es una estructura de datos que:
//Usa el número de documento (tipo String ) como clave.
//Guarda un objeto PersonaDTO como valor.
//👉 Funciona como una mini base de datos temporal para almacenar personas.
//✅ Al ser **static** , el HashMap puede ser usado y compartido desde cualquier parte del programa sin necesidad de crear
//una nueva instancia de ConexionBD .