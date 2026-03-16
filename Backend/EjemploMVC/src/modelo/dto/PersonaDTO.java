package modelo.dto;

public class PersonaDTO {
	
	//Esta clase corresponde a un DTO en el que se crearán los objetos de tipo persona.

	private String documento;
	private String nombre;
	private int edad;
	public PersonaDTO() {
		
	}
	
	public PersonaDTO(String documento, String nombre, int edad) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.edad = edad;
		}
		public String getDocumento() {
		return documento;
		}
		public void setDocumento(String documento) {
		this.documento = documento;
		}
		public String getNombre() {
		return nombre;
		}
		public void setNombre(String nombre) {
		this.nombre = nombre;

		//EJEMPLO APLICANDO PATRON MVC 14

		}
		public int getEdad() {
		return edad;
		}
		public void setEdad(int edad) {
		this.edad = edad;
		}
		@Override
		public String toString() {
		return "PersonaDTO [documento=" + documento + ", nombre=" + nombre + ", edad=" + edad+ "]";
		}

		}
	
