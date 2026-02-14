
public class Pacientes {
	private double CostoTratamiento;
	private int NumeroDias;
	private double CostoMedicamentos;
	private double TotalCuenta;
	
	public Pacientes() {
		// constructor sin parametros
	}
	public Pacientes(double CostoTratamiento, int NumeroDias,double CostoMedicamentos) {
		super(); // constructor con parametros
	}
	
	public double getCostoTratamiento() {
		return CostoTratamiento;
	}
	public void setCostoTratamiento(double costoTratamiento) {
		CostoTratamiento = costoTratamiento;
	}
	public int getNumeroDias() {
		return NumeroDias;
	}
	public void setNumeroDias(int numeroDias) {
		NumeroDias = numeroDias;
	}
	public double getCostoMedicamentos() {
		return CostoMedicamentos;
	}
	public void setCostoMedicamentos(double costoMedicamentos) {
		CostoMedicamentos = costoMedicamentos;
	}
	public double getTotalCuenta(double total) {
		return TotalCuenta;
	}
	public void setTotalCuenta(double totalCuenta) {
		TotalCuenta = totalCuenta;
	}
	@Override
	public String toString() {
		return "Pacientes [CostoTratamiento=" + CostoTratamiento + ", NumeroDias=" + NumeroDias + ", CostoMedicamentos="
				+ CostoMedicamentos + ", TotalCuenta=" + TotalCuenta + "]";
	}




}

 