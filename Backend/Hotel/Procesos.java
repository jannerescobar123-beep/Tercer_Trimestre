
import javax.swing.JOptionPane;

public class Procesos {
    Huesped miHuesped;

    public Procesos() {
        int preg = 0;
        do {
            crearHuesped();
            mostrarTotal();

            preg = Integer.parseInt(JOptionPane.showInputDialog("Ingrese 0 " + "para finalizar, de lo contrario seguira pidiendo huespedes"));

        } while (preg != 0);
    }

    public void crearHuesped() {
        miHuesped = new Huesped();
        miHuesped.setNumeroDeNoches(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de noches hospedado")));
        miHuesped.setValorPorNoche(Double.parseDouble(JOptionPane.showInputDialog("Ingrese valor por noche:")));
        pedirConsumos();
    }

    private void pedirConsumos() {
        int continuar;
        do {
            String servicio = JOptionPane.showInputDialog("Ingrese nombre del servicio:");
            int valor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese valor del servicio:"));
            miHuesped.agregarConsumo(servicio, valor);
            continuar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese 0 para dejar de agregar servicios, otro número para continuar"));
        } while (continuar != 0);
    }
    private void mostrarTotal() {
        double total = miHuesped.calcularTotal();
        JOptionPane.showMessageDialog(null,"El total a pagar es: $" + total);
    }
}