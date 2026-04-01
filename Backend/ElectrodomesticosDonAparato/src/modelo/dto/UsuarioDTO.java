package modelo.dto;

public class UsuarioDTO {
    private String username;
    private String password;
    private String rol;

    public UsuarioDTO(String u,String p,String r){
        username=u; password=p; rol=r;
    }

    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public String getRol(){ return rol; }
}
