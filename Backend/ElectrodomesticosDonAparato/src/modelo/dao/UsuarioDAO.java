package modelo.dao;

import modelo.*;
import modelo.dto.*;
import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {

    private Conexion c = new Conexion();

    public void registrar(UsuarioDTO u){
        try(Connection con = c.getConexion()){
            String sql="INSERT INTO usuario(username,password,rol) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,u.getUsername());
            ps.setString(2,u.getPassword());
            ps.setString(3,u.getRol());
            ps.executeUpdate();

        }catch(Exception e){
            System.out.println("Error al registrar");
        }
    }

    public UsuarioDTO login(String u,String p){
        try(Connection con = c.getConexion()){
            String sql="SELECT * FROM usuario WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,u);
            ps.setString(2,p);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new UsuarioDTO(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("rol")
                );
            }

        }catch(Exception e){
            System.out.println("Error login");
        }
        return null;
    }

    public ArrayList<UsuarioDTO> listar(){
        ArrayList<UsuarioDTO> lista = new ArrayList<>();

        try(Connection con = c.getConexion()){
            String sql="SELECT * FROM usuario";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                lista.add(new UsuarioDTO(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("rol")
                ));
            }

        }catch(Exception e){
            System.out.println("Error al listar");
        }
        return lista;
    }
}
