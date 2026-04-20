package modelo;
 
import java.util.List;
 
/**
 * Modelo Persona.
 * genero y nivelAcademico → ENUM (un solo valor)
 * idiomas, habilidades   → SET  (múltiples valores)
 */
public class Persona {
 
    private int    id;
    private String nombre;
    private String email;
 
    // ENUM: solo acepta UN valor de los definidos en BD
    private String genero;
    private String nivelAcademico;
 
    // SET: acepta MÚLTIPLES valores separados por coma en BD
    private List<String> idiomas;
    private List<String> habilidades;
 
    public Persona() {}
 
    public Persona(String nombre, String email,
                   String genero, String nivelAcademico,
                   List<String> idiomas, List<String> habilidades) {
        this.nombre        = nombre;
        this.email         = email;
        this.genero        = genero;
        this.nivelAcademico = nivelAcademico;
        this.idiomas       = idiomas;
        this.habilidades   = habilidades;
    }
 
    // ── Getters y Setters ──────────────────────────
 
    public int getId()                      { return id; }
    public void setId(int id)               { this.id = id; }
 
    public String getNombre()               { return nombre; }
    public void setNombre(String nombre)    { this.nombre = nombre; }
 
    public String getEmail()                { return email; }
    public void setEmail(String email)      { this.email = email; }
 
    public String getGenero()               { return genero; }
    public void setGenero(String genero)    { this.genero = genero; }
 
    public String getNivelAcademico()                       { return nivelAcademico; }
    public void setNivelAcademico(String nivelAcademico)   { this.nivelAcademico = nivelAcademico; }
 
    public List<String> getIdiomas()                    { return idiomas; }
    public void setIdiomas(List<String> idiomas)        { this.idiomas = idiomas; }
 
    public List<String> getHabilidades()                    { return habilidades; }
    public void setHabilidades(List<String> habilidades)    { this.habilidades = habilidades; }
 
    @Override
    public String toString() {
        return "Persona{id=" + id + ", nombre='" + nombre + "', genero='" + genero +
               "', nivel='" + nivelAcademico + "', idiomas=" + idiomas +
               ", habilidades=" + habilidades + '}';
    }
}