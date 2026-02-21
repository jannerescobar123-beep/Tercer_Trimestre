
public class Persona {
 private  double Peso;
 private double Altura;
 
  public Persona () {
	  
  }
  public Persona(double Peso, double Altura) {
	  this.Peso = Peso;
	  this.Altura =Altura;
  }
  public double getPeso() {
		return Peso;
	}
	 public void setPeso(double peso) {
		Peso = peso;
	 }
	 public double getAltura() {
		return Altura;
	 }
	 public void setAltura(double altura) {
		Altura = altura;
	 }
}
