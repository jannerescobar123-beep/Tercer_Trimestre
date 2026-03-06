package entidad;

public class Operario {

	private String nombre;
    private double sueldo;
    private int    antiguedad;
    private double sueldoFinal;
    private String detalle;

    public Operario(String nombre, double sueldo, int antiguedad) {
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.antiguedad = antiguedad;
    }

    public String getNombre(){
    	return nombre; 
    }
    public double getSueldo(){ 
    	return sueldo; 
    }
    public int    getAntiguedad(){ 
    	return antiguedad; 
    }
    public double getSueldoFinal(){ 
    	return sueldoFinal;
    }
    public String getDetalle(){ 
    	return detalle;
    }
    public void   setSueldoFinal(double s){ 
    	this.sueldoFinal = s; 
    }
    public void   setDetalle(String d){
    	this.detalle = d; 
    }
    
}
	
	

