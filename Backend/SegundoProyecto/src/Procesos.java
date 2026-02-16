import javax.swing.JOptionPane;

public class Procesos {

    Paciente miPaciente;

    public Procesos() {
        proceso();
    }

    public void proceso() {
    	
    	boolean repetir = true;

        do {
        	
        	int preg = Integer.parseInt(JOptionPane.showInputDialog("Ingrese 0 para finalizar, de lo contrario continuará"));

            if (preg == 0) {
            	repetir = false;
                System.out.println("Hasta luego...");
                break;
            } else {
                crearPaciente();
                calcularCuentaFinal(miPaciente);
            }

        } while (repetir);

    }

    private void crearPaciente() {

        miPaciente = new Paciente();

        double costo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el costo de su tratamiento:"));
        miPaciente.setCostoTratamiento(costo);

        int numeroDias = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de dias hospitalizado:"));
        miPaciente.setNumeroDias(numeroDias);

        double costoMedicamentos = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el costo de medicamentos:"));
        miPaciente.setCostoMedicamentos(costoMedicamentos);
    }

    private void calcularCuentaFinal(Paciente miPaciente) {

        double costoTratamiento = miPaciente.getCostoTratamiento();
        int numeroDias = miPaciente.getNumeroDias();
        double costoMedicamentos = miPaciente.getCostoMedicamentos();

        int diaHospitalizado = 10000;
        double totalHospitalizacion = diaHospitalizado * numeroDias;

        double total = costoTratamiento + totalHospitalizacion + costoMedicamentos;

        miPaciente.setTotalCuenta(total);

        JOptionPane.showMessageDialog(null,
                "Total a pagar: " + total);

        imprimirInformacion();
    }

    private void imprimirInformacion() {

        System.out.println();
        System.out.println("___________ CUENTA DEL PACIENTE ________________");
        System.out.println(miPaciente);
        System.out.println("________________________________________________");
        System.out.println();
    }
}
