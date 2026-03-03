import gui.VentanaPrincipal;
import javax.swing.SwingUtilities;

public class main {
	public static void main(String[] args) {
		// SwingUtilities.invokeLater garantiza que la GUI
		// se cree en el hilo correcto de Swing
		SwingUtilities.invokeLater(() -> {
			VentanaPrincipal ventana = new VentanaPrincipal();
			ventana.setVisible(true);
		});
	}
}