
public class Paciente {

    private double costoTratamiento;
    private int numeroDias;
    private double costoMedicamentos;
    private double totalCuenta;

    // Constructor vacío
    public Paciente() {
    }

    // Constructor con parámetros
    public Paciente(double costoTratamiento, int numeroDias,double costoMedicamentos, double totalCuenta) {
        this.costoTratamiento = costoTratamiento;
        this.numeroDias = numeroDias;
        this.costoMedicamentos = costoMedicamentos;
        this.totalCuenta = totalCuenta;
    }

    public double getCostoTratamiento() {
        return costoTratamiento;
    }

    public void setCostoTratamiento(double costoTratamiento) {
        this.costoTratamiento = costoTratamiento;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    public double getCostoMedicamentos() {
        return costoMedicamentos;
    }

    public void setCostoMedicamentos(double costoMedicamentos) {
        this.costoMedicamentos = costoMedicamentos;
    }

    public double getTotalCuenta() {
        return totalCuenta;
    }

    public void setTotalCuenta(double totalCuenta) {
        this.totalCuenta = totalCuenta;
    }

    @Override
    public String toString() {
        return "Paciente [CostoTratamiento=" + costoTratamiento +
                ", NumeroDias=" + numeroDias +
                ", CostoMedicamentos=" + costoMedicamentos +
                ", TotalCuenta=" + totalCuenta + "]";
    }
}

