package controlador;
import java.sql.Array;
import java.util.ArrayList;
// import sirve para dar  acceso a tu clase actual a
//otras clases que se encuentran en paquetes distintos.
import modelo.Procesos;
import modelo.conexion.ConexionDB;
import modelo.dao.PersonaDAO;
import modelo.dto.PersonaDTO;
import vista.VentanaConsultaIndividual;
import vista.VentanaConsultarLista;
import vista.VentanaOperaciones;
import vista.VentanaPrincipal;
import vista.VentanaRegistro;

public class Coordinador {
 // Coordinador actúa como mediador. Permite que las vistas 
 //	interactúen con la lógica del negocio sin conocerse
 //	directamente.

	private VentanaPrincipal ventanaPrincipal;
	private VentanaRegistro ventanaRegistro;
	private VentanaOperaciones ventanaOperaciones;
	private Procesos procesos;
	private PersonaDAO miPersonaDAO;
	private ConexionDB miConexionBD;
	private VentanaConsultaIndividual ventanaConsultaIndividual;
	private VentanaConsultarLista ventanaConsultarLista;
	
	
	// Métodos setters para establecer las relaciones
	// Establecer la comunicación entre objetos
	public void setVentanaRegistro(VentanaRegistro ventanaRegistro) {
	this.ventanaRegistro = ventanaRegistro;
	}

	public void setVentanaConsultaIndividual(VentanaConsultaIndividual ventanaConsultaIndividual) {

	this.ventanaConsultaIndividual=ventanaConsultaIndividual;
	}

	public void setVentanaOperaciones(VentanaOperaciones ventanaOperaciones) {
	this.ventanaOperaciones=ventanaOperaciones;
	}
	public void setProcesos(Procesos procesos) {
	this.procesos = procesos;
	}

	public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
	this.ventanaPrincipal = ventanaPrincipal;
	}

	public void setVentanaConsultarLista(VentanaConsultarLista ventanaConsultarLista) {
	this.ventanaConsultarLista=ventanaConsultarLista;
	}
	
	public void setMiPersonaDAO(PersonaDAO miPersonaDAO) {
		this.miPersonaDAO = miPersonaDAO;
		}
		public void setMiConexionBD(ConexionDB miConexionBD) {
		this.miConexionBD = miConexionBD;
		}

		// Método para mostrar la ventana principal
		public void mostrarVentanaPrincipal() {
		ventanaPrincipal.setVisible(true);
		}
		public void mostrarVentanaOperaciones() {
		ventanaOperaciones.setVisible(true);
		}
		public void mostrarVentanaRegistro() {
		ventanaRegistro.limpiarFormulario();
		ventanaRegistro.setVisible(true);
		}

		public void mostrarVentanaConsultaIndividual() {
		ventanaConsultaIndividual.setVisible(true);
		}

		public void mostrarVentanaConsultarLista() {
		ventanaConsultarLista.consultarListaPersonas();
		ventanaConsultarLista.setVisible(true);
		}
		public String obtenerMensajeConsulta() {
		return procesos.obtenerMensajeEjemplo();
		}
	
		
		//Metodo que delega el llamado a la clase de procesos y devuelve una respuesta
		public String consultarDatos(String nombre) {
		String datos = procesos.obtenerDatos(nombre);
		return datos;
		}
		public String calcularOperacion(String seleccion, String num1, String num2) {
		return procesos.calcularOperaciones(seleccion,num1,num2);
		}

		public boolean validarDatoTexto(String valor) {
		return procesos.validarNombre(valor);
		}
		
		
		public boolean validarNumero(String valor) {
			return procesos.validarNumero(valor);
		}

			public String registrarPersona(PersonaDTO persona) {
			return miPersonaDAO.registrarPersona(persona);
		}
			public String actualizarPersona(PersonaDTO personaNueva) {
			return miPersonaDAO.actualizarPersona(personaNueva);
		}

			public PersonaDTO consultarPersona(String documento) {
			return miPersonaDAO.consultarPersonaPorDocumento(documento);
		}

			public ArrayList<PersonaDTO> consultarListaPersonas() {
			return miPersonaDAO.consultarListaPersonas();
		}

			public String eliminarPersona(String documento) {
			return miPersonaDAO.eliminarPersona(documento);
		}
	
	
}
