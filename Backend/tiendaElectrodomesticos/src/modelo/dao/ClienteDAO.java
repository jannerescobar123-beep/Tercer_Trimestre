package modelo.dao;

import modelo.Conexion;
import modelo.dto.ClienteDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Maneja todas las operaciones SQL de la tabla clientes
public class ClienteDAO {

    public boolean insertar(ClienteDTO dto) {
        String sql = "INSERT INTO clientes (nombre, apellido, edad, telefono, tipo, email, id_usuario) " +
                "VALUES (?,?,?,?,?,?,?)";
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getApellido());
            ps.setInt(3, dto.getEdad());
            ps.setString(4, dto.getTelefono());
            // tipo puede ser null si el cliente no tiene afiliación
            if (dto.getTipo() != null && !dto.getTipo().isEmpty())
                ps.setString(5, dto.getTipo());
            else
                ps.setNull(5, Types.VARCHAR);
            ps.setString(6, dto.getEmail());
            if (dto.getIdUsuario() > 0)
                ps.setInt(7, dto.getIdUsuario());
            else
                ps.setNull(7, Types.INTEGER);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error insertar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(ClienteDTO dto) {
        String sql = "UPDATE clientes SET nombre=?, apellido=?, edad=?, telefono=?, tipo=?, email=? WHERE id=?";
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getApellido());
            ps.setInt(3, dto.getEdad());
            ps.setString(4, dto.getTelefono());
            if (dto.getTipo() != null && !dto.getTipo().isEmpty())
                ps.setString(5, dto.getTipo());
            else
                ps.setNull(5, Types.VARCHAR);
            ps.setString(6, dto.getEmail());
            ps.setInt(7, dto.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id=?";
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error eliminar cliente: " + e.getMessage());
            return false;
        }
    }

    // Retorna clientes
    public List<ClienteDTO> listarTodos() {
        List<ClienteDTO> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, edad, telefono, tipo, email, id_usuario FROM clientes ORDER BY nombre";
        try (Connection con = Conexion.getConexion();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next())
                lista.add(mapear(rs));
        } catch (SQLException e) {
            System.err.println("Error listar clientes: " + e.getMessage());
        }
        return lista;
    }

    public ClienteDTO buscarPorIdUsuario(int idUsuario) {
        String sql = "SELECT id, nombre, apellido, edad, telefono, tipo, email, id_usuario FROM clientes WHERE id_usuario=?";
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return mapear(rs);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    // Convierte ClienteDTO
    private ClienteDTO mapear(ResultSet rs) throws SQLException {
        ClienteDTO dto = new ClienteDTO(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getInt("edad"),
                rs.getString("telefono"),
                rs.getString("tipo"), // puede ser null
                rs.getString("email"));
        dto.setIdUsuario(rs.getInt("id_usuario"));
        return dto;
    }
}