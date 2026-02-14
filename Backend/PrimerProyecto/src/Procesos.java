import javax.swing.JOptionPane;
public class Procesos {
	Pacientes miPaciente;
	
public Procesos() {
		
		int preg=0;
		
		do {
			
			crearPaciente();
			calcularCuentaFinal();
			
			
			preg=Integer.parseInt(JOptionPane.showInputDialog("Ingrese 0 "+ "para finalizar, de lo contrario seguira pidiendo operarios"));
			
		} while (preg!=0);
		
		System.out.println("Hasta luego...");
		
}
public void crearPaciente() {
	miPaciente=new Pacientes();
	miPaciente.setCostoTratamiento(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el costo de su tratamiento:")));
	miPaciente.setNumeroDias(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de dias que estuvo hospitalizado: ")));
	miPaciente.setCostoMedicamentos(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el costo de su medicamento:")));
	
}
public void calcularCuentaFinal() {
	double CostoTratamiento=miPaciente.getCostoTratamiento();
	int NumeroDias=miPaciente.getNumeroDias();
	double CostoMedicamentos=miPaciente.getCostoMedicamentos();
	
	
	int diaHospitalizado = 10000;
	double totalHospitalizacion = 0;
	totalHospitalizacion += diaHospitalizado * NumeroDias;
	double total = 0;
	total = CostoTratamiento + totalHospitalizacion + CostoMedicamentos;
	miPaciente.setTotalCuenta(total);
	
	JOptionPane.showMessageDialog(null, "Este es el total que debe pagar el paciente es "+ total);
	
	imprimirInformacion();
}
public void imprimirInformacion() {
	System.out.println();
	System.out.println("___________ CUENTA DEL PACIENTE ________________");
	
	System.out.println(miPaciente);
	System.out.println("________________________________________________");
	System.out.println();

}
}
