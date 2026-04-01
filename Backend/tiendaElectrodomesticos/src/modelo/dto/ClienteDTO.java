package modelo.dto;

// Transporta datos de un cliente entre capas
// tipo viene del ENUM de la BD: 'A', 'B', 'C' o null
public class ClienteDTO {

    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String telefono;
    private String tipo; // tipo de afilicion: 'A', 'B', 'C' o null = sin descuento
    private String email;
    private int idUsuario;

    public ClienteDTO() {
    }

    public ClienteDTO(int id, String nombre, String apellido, int edad,
            String telefono, String tipo, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.tipo = tipo;
        this.email = email;
    }

    public ClienteDTO(String nombre, String apellido, int edad,
            String telefono, String tipo, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.tipo = tipo;
        this.email = email;
    }

    // Retorna el porcentaje de descuento según el tipo
    // A=40%, B=20%, C=10%, null=0%
    public double getPorcentajeDescuento() {
        if (tipo == null)
            return 0.0;
        switch (tipo) {
            case "A":
                return 0.40;
            case "B":
                return 0.20;
            case "C":
                return 0.10;
            default:
                return 0.0;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String a) {
        this.apellido = a;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int e) {
        this.edad = e;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String t) {
        this.telefono = t;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String e) {
        this.email = e;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " " + apellido;
    }
}