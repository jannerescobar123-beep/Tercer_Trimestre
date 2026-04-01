package modelo.dao;

import modelo.Conexion;
import modelo.dto.UsuarioDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Maneja todas las operaciones SQL de la tabla usuarios
public class UsuarioDAO {

    // Verifica usuario y contraseña. Retorna UsuarioDTO o null si falla
    public UsuarioDTO login(String username, String password) {
        String sql = "SELECT id, username, rol, activo FROM usuarios " +
                     "WHERE username=? AND password=? AND activo=1";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new UsuarioDTO(rs.getInt("id"), rs.getString("username"),
                        rs.getString("rol"), rs.getBoolean("activo"));
            }
        } catch (SQLException e) { System.err.println("Error login: " + e.getMessage()); }
        return null;
    }

    // Registra un nuevo usuario con el rol indicado ('admin' o 'usuario')
    public boolean registrar(String username, String password, String rol) {
        String sql = "INSERT INTO usuarios (username, password, rol) VALUES (?,?,?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, rol);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) { System.err.println("Error registrar: " + e.getMessage()); return false; }
    }

    // Lista todos los usuarios (solo el admin puede usar esto)
    public List<UsuarioDTO> listarTodos() {
        List<UsuarioDTO> lista = new ArrayList<>();
        String sql = "SELECT id, username, rol, activo FROM usuarios ORDER BY username";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next())
                lista.add(new UsuarioDTO(rs.getInt("id"), rs.getString("username"),
                        rs.getString("rol"), rs.getBoolean("activo")));
        } catch (SQLException e) { System.err.println("Error listar: " + e.getMessage()); }
        return lista;
    }

    // Elimina un usuario por id
    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.err.println("Error eliminar: " + e.getMessage()); return false; }
    }

    // Verifica si un username ya existe en la BD
    public boolean existeUsername(String username) {
        String sql = "SELECT id FROM usuarios WHERE username=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            return ps.executeQuery().next();
        } catch (SQLException e) { return false; }
    }
}