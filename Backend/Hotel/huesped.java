package Backend.Hotel;

import java.util.HashMap;

public class huesped {
    private int NumeroDeNoches;
    private double ValorPorNoche;
    private HashMap<String, Integer> ConsumoAdicional = new HashMap<>();


    public huesped() {

    }
    public huesped(int NumeroDeNoches, double ValorPorNoche,HashMap<String, Integer> Mapa) {
        this.NumeroDeNoches = NumeroDeNoches;
        this.ValorPorNoche = ValorPorNoche;
        this.ConsumoAdicional = Mapa;
    }
    public int getNumeroDeNoches() {
        return NumeroDeNoches;
    }
    public void setNumeroDeNoches(int numeroDeNoches) {
        NumeroDeNoches = numeroDeNoches;
    }
    public double getValorPorNoche() {
        return ValorPorNoche;
    }
    public void setValorPorNoche(double valorPorNoche) {
        ValorPorNoche = valorPorNoche;
    }
    public HashMap<String, Integer> getConsumoAdicional() {
        return ConsumoAdicional;
    }
    public void setConsumoAdicional(HashMap<String, Integer> consumoAdicional) {
        ConsumoAdicional = consumoAdicional;
    }
}
