package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import controlador.Coordinador;
import modelo.dto.PersonaDTO;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDialog;

public class VentanaConsultarLista extends JDialog implements ActionListener{
private static final long serialVersionUID = 1L;
private JPanel contentPane;
private Coordinador miCoordinador;
private JTextArea txtAreaResultado;

/**
* Create the frame.
* @param b
* @param ventanaPrincipal
*/
public VentanaConsultarLista(VentanaPrincipal ventanaPrincipal, boolean modal) {
/**Al llamar al constructor super(), le enviamos el
* JFrame Padre y la propiedad booleana que determina
* que es hija*/

super(ventanaPrincipal, modal);
setBounds(100, 100, 453, 327);
contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);
setLocationRelativeTo(null);


iniciarComponentes();
}
public void consultarListaPersonas() {
ArrayList<PersonaDTO> listaPersonas=miCoordinador.consultarListaPersonas();
String msj="";
if (listaPersonas.size()>0) {
for (PersonaDTO personaDTO : listaPersonas) {
msj+="Documento: "+personaDTO.getDocumento()+"\nNombre: "+personaDTO.getNombre()+"\nedad: "+personaDTO.getEdad()+"\n\n";
}
}else {
msj="NO HAY PERSONAS REGISTRADAS";
}
txtAreaResultado.setText(msj);
}


private void iniciarComponentes() {
JLabel lblSistemaGestionUsuarios = new JLabel("CONSULTA LISTA PERSONAS");
lblSistemaGestionUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
lblSistemaGestionUsuarios.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
lblSistemaGestionUsuarios.setBounds(33, 6, 388, 30);
contentPane.add(lblSistemaGestionUsuarios);
txtAreaResultado = new JTextArea();
txtAreaResultado.setColumns(5);
txtAreaResultado.setLineWrap(true); // Opcional: para que las líneas largas se ajusten al ancho

txtAreaResultado.setWrapStyleWord(true); // Opcional: ajusta líneas por palabras
JScrollPane scrollPane = new JScrollPane(txtAreaResultado);
scrollPane.setBounds(21, 48, 410, 210);
scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
contentPane.add(scrollPane);
}

public void setCoordinador(Coordinador miCoordinador) {
this.miCoordinador=miCoordinador;
}

@Override
public void actionPerformed(ActionEvent e) {
}
}


