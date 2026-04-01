package modelo;

import modelo.dao.UsuarioDAO;
import modelo.dto.UsuarioDTO;
import java.util.ArrayList;

public class ServicioUsuario {

    private UsuarioDAO dao = new UsuarioDAO();

    public void registrar(String u,String p,String r){
        dao.registrar(new UsuarioDTO(u,p,r));
    }

    public UsuarioDTO login(String u,String p){
        return dao.login(u,p);
    }

    public ArrayList<UsuarioDTO> listar(){
        return dao.listar();
    }
}
