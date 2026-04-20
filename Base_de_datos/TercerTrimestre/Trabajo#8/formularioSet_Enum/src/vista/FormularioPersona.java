package vista;
 
import controlador.PersonaControlador;
import modelo.Persona;
 
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
 
/**
 * Vista principal — JFrame con patrón MVC básico.
 * ENUM  → JComboBox (desplegable) y JRadioButton
 * SET   → JCheckBox (múltiple selección)
 */
public class FormularioPersona extends JFrame {
 
    // ── Controlador ──────────────────────────────────────
    private final PersonaControlador controlador = new PersonaControlador();
 
    // ── Campos de texto ──────────────────────────────────
    private JTextField txtNombre;
    private JTextField txtEmail;
 
    // ── ENUM: Género — JComboBox (lista desplegable) ─────
    private JComboBox<String> cboGenero;
 
    // ── ENUM: Nivel Académico — JRadioButton ─────────────
    private JRadioButton rbPrimaria, rbSecundaria, rbTecnico, rbUniversitario, rbPosgrado;
    private ButtonGroup  bgNivel;
 
    // ── SET: Idiomas — JCheckBox ──────────────────────────
    private JCheckBox chkEspanol, chkIngles, chkFrances, chkPortugues, chkAleman, chkItaliano;
 
    // ── SET: Habilidades — JCheckBox ─────────────────────
    private JCheckBox chkJava, chkPython, chkJS, chkSQL, chkHTML, chkSpring, chkReact;
 
    // ── Botones ───────────────────────────────────────────
    private JButton btnGuardar;
    private JButton btnLimpiar;
 
    // ── Área de resultados ────────────────────────────────
    private JTextArea txtResultado;
 
    // ─────────────────────────────────────────────────────
    public FormularioPersona() {
        initUI();
    }
 
    // ══════════════════════════════════════════════════════
    //  CONSTRUCCIÓN DE LA INTERFAZ
    // ══════════════════════════════════════════════════════
    private void initUI() {
        setTitle("Registro de Personas — ENUM & SET");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(680, 720);
        setLocationRelativeTo(null);
        setResizable(false);
 
        // Panel raíz con scroll
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBackground(Color.WHITE);
        root.setBorder(new EmptyBorder(16, 20, 16, 20));
 
        // ── Encabezado ───────────────────────────────────
        root.add(crearEncabezado());
        root.add(Box.createVerticalStrut(14));
 
        // ── Datos básicos ────────────────────────────────
        root.add(crearSeccion("Datos Personales", crearPanelDatosBasicos()));
        root.add(Box.createVerticalStrut(10));
 
        // ── ENUM: Género (ComboBox) ──────────────────────
        root.add(crearSeccion("Género  [ENUM — selección única]", crearPanelGenero()));
        root.add(Box.createVerticalStrut(10));
 
        // ── ENUM: Nivel Académico (RadioButton) ──────────
        root.add(crearSeccion("Nivel Académico  [ENUM — selección única]", crearPanelNivel()));
        root.add(Box.createVerticalStrut(10));
 
        // ── SET: Idiomas (CheckBox) ───────────────────────
        root.add(crearSeccion("Idiomas hablados  [SET — selección múltiple]", crearPanelIdiomas()));
        root.add(Box.createVerticalStrut(10));
 
        // ── SET: Habilidades (CheckBox) ───────────────────
        root.add(crearSeccion("Habilidades  [SET — selección múltiple]", crearPanelHabilidades()));
        root.add(Box.createVerticalStrut(14));
 
        // ── Botones ───────────────────────────────────────
        root.add(crearPanelBotones());
        root.add(Box.createVerticalStrut(10));
 
        // ── Área de estado ────────────────────────────────
        root.add(crearAreaResultado());
 
        JScrollPane scroll = new JScrollPane(root);
        scroll.setBorder(null);
        add(scroll);
    }
 
    // ──────────────────────────────────────────────────────
    //  ENCABEZADO
    // ──────────────────────────────────────────────────────
    private JPanel crearEncabezado() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        p.setBackground(new Color(0x3B4FC8));
        p.setBorder(new EmptyBorder(12, 16, 12, 16));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
 
        JLabel titulo = new JLabel("📋  Sistema de Registro");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 17));
        titulo.setForeground(Color.WHITE);
 
        JLabel sub = new JLabel("   Tipos ENUM y SET · MySQL + Java Swing");
        sub.setFont(new Font("SansSerif", Font.PLAIN, 11));
        sub.setForeground(new Color(0xBBCCFF));
 
        p.add(titulo);
        p.add(sub);
        return p;
    }
 
    // ──────────────────────────────────────────────────────
    //  SECCIÓN GENÉRICA (título + contenido)
    // ──────────────────────────────────────────────────────
    private JPanel crearSeccion(String titulo, JPanel contenido) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 6));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, contenido.getPreferredSize().height + 40));
 
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbl.setForeground(new Color(0x3B4FC8));
        lbl.setBorder(new EmptyBorder(0, 0, 4, 0));
 
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(0xDDDDFF));
 
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.add(lbl, BorderLayout.CENTER);
        header.add(sep, BorderLayout.SOUTH);
 
        panel.add(header,   BorderLayout.NORTH);
        panel.add(contenido, BorderLayout.CENTER);
        return panel;
    }
 
    // ──────────────────────────────────────────────────────
    //  DATOS BÁSICOS
    // ──────────────────────────────────────────────────────
    private JPanel crearPanelDatosBasicos() {
        JPanel p = new JPanel(new GridLayout(2, 2, 10, 8));
        p.setBackground(Color.WHITE);
 
        txtNombre = new JTextField();
        txtEmail  = new JTextField();
 
        p.add(labelField("Nombre completo *"));
        p.add(labelField("Correo electrónico *"));
        p.add(txtNombre);
        p.add(txtEmail);
        return p;
    }
 
    // ──────────────────────────────────────────────────────
    //  ENUM → JComboBox — GÉNERO
    // ──────────────────────────────────────────────────────
    private JPanel crearPanelGenero() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 4));
        p.setBackground(Color.WHITE);
 
        // Los valores del JComboBox deben coincidir EXACTAMENTE
        // con los del ENUM en la base de datos
        String[] opcionesEnum = {
            "", "Masculino", "Femenino", "No binario", "Prefiero no decir"
        };
        cboGenero = new JComboBox<>(opcionesEnum);
        cboGenero.setPreferredSize(new Dimension(220, 28));
        cboGenero.setFont(new Font("SansSerif", Font.PLAIN, 13));
 
        JLabel nota = new JLabel("  ← ENUM: solo se guarda un valor");
        nota.setFont(new Font("SansSerif", Font.ITALIC, 11));
        nota.setForeground(new Color(0x888888));
 
        p.add(cboGenero);
        p.add(nota);
        return p;
    }
 
    // ──────────────────────────────────────────────────────
    //  ENUM → JRadioButton — NIVEL ACADÉMICO
    // ──────────────────────────────────────────────────────
    private JPanel crearPanelNivel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
        p.setBackground(Color.WHITE);
 
        bgNivel        = new ButtonGroup();
        rbPrimaria     = new JRadioButton("Primaria");
        rbSecundaria   = new JRadioButton("Secundaria");
        rbTecnico      = new JRadioButton("Técnico");
        rbUniversitario = new JRadioButton("Universitario");
        rbPosgrado     = new JRadioButton("Posgrado");
 
        JRadioButton[] rbs = {rbPrimaria, rbSecundaria, rbTecnico, rbUniversitario, rbPosgrado};
        for (JRadioButton rb : rbs) {
            rb.setBackground(Color.WHITE);
            rb.setFont(new Font("SansSerif", Font.PLAIN, 13));
            bgNivel.add(rb);
            p.add(rb);
        }
 
        JLabel nota = new JLabel("  ← ENUM: exclusivo");
        nota.setFont(new Font("SansSerif", Font.ITALIC, 11));
        nota.setForeground(new Color(0x888888));
        p.add(nota);
        return p;
    }
 
    // ──────────────────────────────────────────────────────
    //  SET → JCheckBox — IDIOMAS
    // ──────────────────────────────────────────────────────
    private JPanel crearPanelIdiomas() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
        p.setBackground(Color.WHITE);
 
        // Los valores deben coincidir con el SET en la BD
        chkEspanol   = new JCheckBox("Español");
        chkIngles    = new JCheckBox("Inglés");
        chkFrances   = new JCheckBox("Francés");
        chkPortugues = new JCheckBox("Portugués");
        chkAleman    = new JCheckBox("Alemán");
        chkItaliano  = new JCheckBox("Italiano");
 
        JCheckBox[] chs = {chkEspanol, chkIngles, chkFrances, chkPortugues, chkAleman, chkItaliano};
        for (JCheckBox ch : chs) {
            ch.setBackground(Color.WHITE);
            ch.setFont(new Font("SansSerif", Font.PLAIN, 13));
            p.add(ch);
        }
 
        JLabel nota = new JLabel("  ← SET: múltiples permitidos");
        nota.setFont(new Font("SansSerif", Font.ITALIC, 11));
        nota.setForeground(new Color(0x44AA66));
        p.add(nota);
        return p;
    }
 
    // ──────────────────────────────────────────────────────
    //  SET → JCheckBox — HABILIDADES
    // ──────────────────────────────────────────────────────
    private JPanel crearPanelHabilidades() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
        p.setBackground(Color.WHITE);
 
        chkJava   = new JCheckBox("Java");
        chkPython = new JCheckBox("Python");
        chkJS     = new JCheckBox("JavaScript");
        chkSQL    = new JCheckBox("SQL");
        chkHTML   = new JCheckBox("HTML/CSS");
        chkSpring = new JCheckBox("Spring");
        chkReact  = new JCheckBox("React");
 
        JCheckBox[] chs = {chkJava, chkPython, chkJS, chkSQL, chkHTML, chkSpring, chkReact};
        for (JCheckBox ch : chs) {
            ch.setBackground(Color.WHITE);
            ch.setFont(new Font("SansSerif", Font.PLAIN, 13));
            p.add(ch);
        }
 
        JLabel nota = new JLabel("  ← SET: múltiples permitidos");
        nota.setFont(new Font("SansSerif", Font.ITALIC, 11));
        nota.setForeground(new Color(0x44AA66));
        p.add(nota);
        return p;
    }
 
    // ──────────────────────────────────────────────────────
    //  BOTONES
    // ──────────────────────────────────────────────────────
    private JPanel crearPanelBotones() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        p.setBackground(Color.WHITE);
 
        btnGuardar = new JButton("💾  Guardar Registro");
        btnGuardar.setBackground(new Color(0x3B4FC8));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new Dimension(200, 34));
        btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
 
        btnLimpiar = new JButton("🗑  Limpiar");
        btnLimpiar.setBackground(new Color(0xF0F0F0));
        btnLimpiar.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setPreferredSize(new Dimension(130, 34));
        btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
 
        btnGuardar.addActionListener(e -> accionGuardar());
        btnLimpiar.addActionListener(e -> accionLimpiar());
 
        p.add(btnGuardar);
        p.add(btnLimpiar);
        return p;
    }
 
    // ──────────────────────────────────────────────────────
    //  ÁREA DE RESULTADO / LOG
    // ──────────────────────────────────────────────────────
    private JPanel crearAreaResultado() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
 
        txtResultado = new JTextArea(5, 40);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtResultado.setBackground(new Color(0xF8F8FF));
        txtResultado.setBorder(new EmptyBorder(8, 10, 8, 10));
        txtResultado.setText("[ Resultados de operaciones aparecerán aquí ]");
 
        JScrollPane sp = new JScrollPane(txtResultado);
        sp.setBorder(BorderFactory.createLineBorder(new Color(0xCCCCEE)));
        p.add(sp);
        return p;
    }
 
    // ══════════════════════════════════════════════════════
    //  ACCIONES (comunicación Vista → Controlador)
    // ══════════════════════════════════════════════════════
 
    private void accionGuardar() {
        // 1. Recoger valores del formulario
        String nombre = txtNombre.getText().trim();
        String email  = txtEmail.getText().trim();
 
        // ENUM: un solo valor String
        String genero = (String) cboGenero.getSelectedItem();
        String nivel  = getNivelSeleccionado();
 
        // SET: lista de valores marcados
        List<String> idiomas     = getIdiomasSeleccionados();
        List<String> habilidades = getHabilidadesSeleccionadas();
 
        // 2. Validar en el Controlador
        String error = controlador.validar(nombre, email, genero, nivel, idiomas, habilidades);
        if (error != null) {
            mostrarMensaje("⚠ " + error, false);
            return;
        }
 
        // 3. Construir Modelo
        Persona p = new Persona(nombre, email, genero, nivel, idiomas, habilidades);
 
        // 4. Delegar guardado al Controlador
        boolean ok = controlador.guardar(p);
 
        if (ok) {
            mostrarMensaje(
                "✔ Registro guardado correctamente.\n" +
                "  Nombre : " + nombre + "\n" +
                "  Género : " + genero + "  [ENUM]\n" +
                "  Nivel  : " + nivel  + "  [ENUM]\n" +
                "  Idiomas    : " + String.join(", ", idiomas)     + "  [SET]\n" +
                "  Habilidades: " + String.join(", ", habilidades) + "  [SET]",
                true
            );
            accionLimpiar();
        } else {
            mostrarMensaje("✖ Error al guardar. Revisa la conexión con la base de datos.", false);
        }
    }
 
    private void accionLimpiar() {
        txtNombre.setText("");
        txtEmail.setText("");
        cboGenero.setSelectedIndex(0);
        bgNivel.clearSelection();
 
        JCheckBox[] todos = {
            chkEspanol, chkIngles, chkFrances, chkPortugues, chkAleman, chkItaliano,
            chkJava, chkPython, chkJS, chkSQL, chkHTML, chkSpring, chkReact
        };
        for (JCheckBox ch : todos) ch.setSelected(false);
    }
 
    // ──────────────────────────────────────────────────────
    //  HELPERS — leer checkboxes y radiobuttons
    // ──────────────────────────────────────────────────────
 
    /** ENUM: devuelve el JRadioButton seleccionado (nivel académico) */
    private String getNivelSeleccionado() {
        if (rbPrimaria.isSelected())      return rbPrimaria.getText();
        if (rbSecundaria.isSelected())    return rbSecundaria.getText();
        if (rbTecnico.isSelected())       return rbTecnico.getText();
        if (rbUniversitario.isSelected()) return rbUniversitario.getText();
        if (rbPosgrado.isSelected())      return rbPosgrado.getText();
        return "";
    }
 
    /** SET: devuelve lista de idiomas marcados */
    private List<String> getIdiomasSeleccionados() {
        List<String> lista = new ArrayList<>();
        if (chkEspanol.isSelected())   lista.add(chkEspanol.getText());
        if (chkIngles.isSelected())    lista.add(chkIngles.getText());
        if (chkFrances.isSelected())   lista.add(chkFrances.getText());
        if (chkPortugues.isSelected()) lista.add(chkPortugues.getText());
        if (chkAleman.isSelected())    lista.add(chkAleman.getText());
        if (chkItaliano.isSelected())  lista.add(chkItaliano.getText());
        return lista;
    }
 
    /** SET: devuelve lista de habilidades marcadas */
    private List<String> getHabilidadesSeleccionadas() {
        List<String> lista = new ArrayList<>();
        if (chkJava.isSelected())   lista.add(chkJava.getText());
        if (chkPython.isSelected()) lista.add(chkPython.getText());
        if (chkJS.isSelected())     lista.add(chkJS.getText());
        if (chkSQL.isSelected())    lista.add(chkSQL.getText());
        if (chkHTML.isSelected())   lista.add(chkHTML.getText());
        if (chkSpring.isSelected()) lista.add(chkSpring.getText());
        if (chkReact.isSelected())  lista.add(chkReact.getText());
        return lista;
    }
 
    private void mostrarMensaje(String msg, boolean exito) {
        txtResultado.setForeground(exito ? new Color(0x116633) : new Color(0xCC2200));
        txtResultado.setText(msg);
    }
 
    private JLabel labelField(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("SansSerif", Font.PLAIN, 12));
        l.setForeground(new Color(0x444444));
        return l;
    }
 
    // ══════════════════════════════════════════════════════
    //  MAIN — punto de entrada
    // ══════════════════════════════════════════════════════
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new FormularioPersona().setVisible(true);
        });
    }
}