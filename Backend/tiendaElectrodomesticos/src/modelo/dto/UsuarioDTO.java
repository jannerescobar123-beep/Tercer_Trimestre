package modelo.dto;

// Transporta los datos del usuario logueado entre capas
// rol viene del ENUM de la BD: 'admin' o 'usuario'
public class UsuarioDTO {

    private int     id;
    private String  username;
    private String  rol;
    private boolean activo;

    public UsuarioDTO() {}

    public UsuarioDTO(int id, String username, String rol, boolean activo) {
        this.id       = id;
        this.username = username;
        this.rol      = rol;
        this.activo   = activo;
    }

    // Retorna true si el usuario es administrador
    public boolean esAdmin() {
        return "admin".equalsIgnoreCase(rol);
    }

    public int     getId()               { return id; }
    public void    setId(int id)         { this.id = id; }
    public String  getUsername()         { return username; }
    public void    setUsername(String u) { this.username = u; }
    public String  getRol()              { return rol; }
    public void    setRol(String rol)    { this.rol = rol; }
    public boolean isActivo()            { return activo; }
    public void    setActivo(boolean a)  { this.activo = a; }

    @Override
    public String toString() { return username + " (" + rol + ")"; }
}