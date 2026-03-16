package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import controlador.Coordinador;
import modelo.dto.PersonaDTO;
import java.awt.Font;

public class VentanaRegistro extends JDialog implements ActionListener{

	private Coordinador coordinador;
	private JButton btnRegistrar;
	private JTextField txtNombre;
	private JLabel lblResultado;
	private JButton btnCalculos;
	private JLabel lblDocumento;
	private JTextField txtDocumento;
	private JLabel lblEdad;
	private JTextField txtEdad;
	private JButton btnConsultar;
	private JLabel lblRegistrarUsuarios;
	
	public VentanaRegistro(VentanaPrincipal ventanaPrincipal, boolean modal) {
		/**Al llamar al constructor super(), le enviamos el
		* JFrame Padre y la propiedad booleana que determina
		* que es hija*/
		super(ventanaPrincipal, modal);
		setTitle("Ventana Registro Persona");
		setSize(382, 277);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		iniciarComponentes();
		}

		//EJEMPLO APLICANDO PATRON MVC 20

		private void iniciarComponentes() {
		txtNombre = new JTextField();
		txtNombre.setBounds(50, 74, 200, 30);
		getContentPane().add(txtNombre);
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(260, 74, 100, 30);
		btnRegistrar.addActionListener(this);
		getContentPane().add(btnRegistrar);
		lblResultado = new JLabel("Resultado:");
		lblResultado.setBounds(50, 178, 300, 30);
		getContentPane().add(lblResultado);
		btnCalculos = new JButton("Hacer Calculos");
		btnCalculos.setBounds(39, 213, 160, 30);
		btnCalculos.addActionListener(this);
		btnCalculos.setVisible(false);
		getContentPane().add(btnCalculos);
		JLabel lblNombre = new JLabel("Ingrese su nombre");
		lblNombre.setBounds(50, 48, 300, 30);
		getContentPane().add(lblNombre);
		lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(50, 110, 91, 30);
		getContentPane().add(lblDocumento);
		txtDocumento = new JTextField();
		txtDocumento.setBounds(140, 110, 110, 30);
		getContentPane().add(txtDocumento);
		lblEdad = new JLabel("Edad");
		lblEdad.setBounds(270, 110, 49, 30);
		getContentPane().add(lblEdad);
		txtEdad = new JTextField();
		txtEdad.setBounds(313, 110, 41, 30);
		getContentPane().add(txtEdad);
		btnConsultar = new JButton("Consultar Registros");
		btnConsultar.setBounds(211, 214, 149, 29);
		btnConsultar.setVisible(false);
		btnConsultar.addActionListener(this);
		getContentPane().add(btnConsultar);
		lblRegistrarUsuarios = new JLabel("REGISTRAR USUARIOS");
		lblRegistrarUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarUsuarios.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblRegistrarUsuarios.setBounds(0, 6, 376, 30);
		getContentPane().add(lblRegistrarUsuarios);
		}
		// Método para establecer el coordinador
		public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
		}

		//EJEMPLO APLICANDO PATRON MVC 21

		@Override
		public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnRegistrar) {
		validaRegistro();
		}else if(e.getSource()==btnCalculos) {
		coordinador.mostrarVentanaOperaciones();
		}else if(e.getSource()==btnConsultar) {
		coordinador.mostrarVentanaConsultarLista();;
		}
		}

		private void validaRegistro() {
		boolean validaNombre=coordinador.validarDatoTexto(txtNombre.getText());
		boolean validaDocumento=coordinador.validarDatoTexto(txtNombre.getText());
		boolean validaEdad=coordinador.validarNumero(txtEdad.getText());
		verificaCampo(validaNombre, txtNombre);
		verificaCampo(validaDocumento, txtDocumento);
		verificaCampo(validaEdad, txtEdad);
		if(validaNombre==true && validaDocumento==true && validaEdad==true) {
		String res=coordinador.consultarDatos(txtNombre.getText());
		if(registrarPersona()==true) {
		lblResultado.setText("Resultado: "+res+", Registro Ok");
		}else {
		lblResultado.setText("Resultado: "+res+", No Registra");
		}

		lblResultado.setForeground(Color.black);
		btnCalculos.setVisible(true);
		btnConsultar.setVisible(true);
		}else {
		lblResultado.setText("Valide los tipos de datos ingresados");
		lblResultado.setForeground(Color.red);
		btnCalculos.setVisible(false);
		btnConsultar.setVisible(false);
		}
		}
		private boolean registrarPersona() {
		PersonaDTO miPersonaDTO=new PersonaDTO();
		miPersonaDTO.setDocumento(txtDocumento.getText());
		miPersonaDTO.setNombre(txtNombre.getText());
		miPersonaDTO.setEdad(Integer.parseInt(txtEdad.getText()));
		String resp=coordinador.registrarPersona(miPersonaDTO);
		if (resp.equals("si")) {
		return true;
		}else {
 
		//EJEMPLO APLICANDO PATRON MVC 22

		return false;
		}
		}
		public void limpiarFormulario() {
		txtNombre.setText("");
		txtDocumento.setText("");
		txtEdad.setText("");
		lblResultado.setText("");
		btnCalculos.setVisible(false);
		btnConsultar.setVisible(false);
		}
		private void verificaCampo(boolean validarCampo, JTextField campo) {
		if (validarCampo==true) {
		campo.setBackground(Color.white);
		}else {
		campo.setBackground(Color.red);
		}
		}
}
