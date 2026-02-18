

import java.util.HashMap;
import java.util.Map;

public class Huesped {

    private int numeroDeNoches;
    private double valorPorNoche;
    private HashMap<String, Integer> consumoAdicional;

    public Huesped() {
        consumoAdicional = new HashMap<>();
    }

    public Huesped(int numeroDeNoches, double valorPorNoche) {
        this.numeroDeNoches = numeroDeNoches;
        this.valorPorNoche = valorPorNoche;
        this.consumoAdicional = new HashMap<>();
    }

    public void agregarConsumo(String servicio, int valor) {
        consumoAdicional.put(servicio, valor);
    }

    public double calcularTotal() {
        double totalConsumo = 0;

        for (Map.Entry<String, Integer> entry : consumoAdicional.entrySet()) {
            totalConsumo += entry.getValue();
        }

        return (numeroDeNoches * valorPorNoche) + totalConsumo;
    }

    public int getNumeroDeNoches() {
        return numeroDeNoches;
    }

    public void setNumeroDeNoches(int numeroDeNoches) {
        this.numeroDeNoches = numeroDeNoches;
    }

    public double getValorPorNoche() {
        return valorPorNoche;
    }

    public void setValorPorNoche(double valorPorNoche) {
        this.valorPorNoche = valorPorNoche;
    }

    public HashMap<String, Integer> getConsumoAdicional() {
        return consumoAdicional;
    }
}
