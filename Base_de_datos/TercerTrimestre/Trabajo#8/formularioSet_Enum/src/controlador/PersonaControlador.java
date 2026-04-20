package controlador;
 
import basedatos.Conexion;
import modelo.Persona;
 
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
/**
 * Controlador en el patrón MVC.
 * Recibe datos desde la Vista, los valida y los persiste en MySQL.
 */
public class PersonaControlador {
 
    /**
     * Guarda una Persona en la base de datos.
     * - genero y nivelAcademico se insertan como ENUM  → un solo String
     * - idiomas y habilidades se insertan como SET     → Strings unidos con coma
     *
     * @return true si se guardó correctamente, false si hubo error
     */
    public boolean guardar(Persona p) {
        // Convertir List<String> → "val1,val2,val3"  (formato SET de MySQL)
        String idiomasStr     = String.join(",", p.getIdiomas());
        String habilidadesStr = String.join(",", p.getHabilidades());
 
        String sql = "INSERT INTO personas (nombre, email, genero, nivel_academico, idiomas, habilidades) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
 
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getGenero());          // ENUM
            ps.setString(4, p.getNivelAcademico());  // ENUM
            ps.setString(5, idiomasStr);             // SET
            ps.setString(6, habilidadesStr);         // SET
 
            ps.executeUpdate();
            return true;
 
        } catch (SQLException e) {
            System.err.println("Error al guardar persona: " + e.getMessage());
            return false;
        }
    }
 
    /**
     * Recupera todas las personas guardadas.
     * Convierte el String SET de MySQL ("Java,SQL") → List<String>
     */
    public List<Persona> listarTodos() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM personas ORDER BY creado_en DESC";
 
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
 
            while (rs.next()) {
                Persona p = new Persona();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setEmail(rs.getString("email"));
                p.setGenero(rs.getString("genero"));              // ENUM → String
                p.setNivelAcademico(rs.getString("nivel_academico")); // ENUM → String
 
                // SET → dividir "Java,SQL" en List<String>
                String idiomasRaw     = rs.getString("idiomas");
                String habilidadesRaw = rs.getString("habilidades");
 
                p.setIdiomas(splitSet(idiomasRaw));
                p.setHabilidades(splitSet(habilidadesRaw));
 
                lista.add(p);
            }
 
        } catch (SQLException e) {
            System.err.println("Error al listar personas: " + e.getMessage());
        }
        return lista;
    }
 
    // Convierte "Java,SQL" o null → List<String>
    private List<String> splitSet(String raw) {
        if (raw == null || raw.isBlank()) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(raw.split(",")));
    }
 
    // ── Validaciones básicas ──────────────────────────────
 
    public String validar(String nombre, String email,
                          String genero, String nivel,
                          List<String> idiomas, List<String> habilidades) {
        if (nombre == null || nombre.isBlank())
            return "El nombre es obligatorio.";
        if (email == null || !email.matches("^[\\w.+\\-]+@[\\w\\-]+\\.[a-z]{2,}$"))
            return "Ingresa un correo electrónico válido.";
        if (genero == null || genero.isBlank())
            return "Debes seleccionar un género.";
        if (nivel == null || nivel.isBlank())
            return "Debes seleccionar un nivel académico.";
        if (idiomas.isEmpty())
            return "Selecciona al menos un idioma.";
        if (habilidades.isEmpty())
            return "Selecciona al menos una habilidad.";
        return null; // sin errores
    }
}
