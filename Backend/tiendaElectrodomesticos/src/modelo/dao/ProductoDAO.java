package modelo.dao;

import modelo.Conexion;
import modelo.dto.ProductoDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Maneja todas las operaciones SQL de la tabla productos
public class ProductoDAO {

    // Inserta un producto nuevo
    public boolean insertar(ProductoDTO dto) {
        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?,?,?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dto.getNombre());
            ps.setDouble(2, dto.getPrecio());
            ps.setInt(3, dto.getStock());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) { System.err.println("Error insertar producto: " + e.getMessage()); return false; }
    }

    // Actualiza nombre, precio y stock de un producto
    public boolean actualizar(ProductoDTO dto) {
        String sql = "UPDATE productos SET nombre=?, precio=?, stock=? WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dto.getNombre());
            ps.setDouble(2, dto.getPrecio());
            ps.setInt(3, dto.getStock());
            ps.setInt(4, dto.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.err.println("Error actualizar producto: " + e.getMessage()); return false; }
    }

    // Elimina un producto por id
    public boolean eliminar(int id) {
        String sql = "DELETE FROM productos WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.err.println("Error eliminar producto: " + e.getMessage()); return false; }
    }

    // Solo productos con stock > 0 (para el combo de compras)
    public List<ProductoDTO> listarDisponibles() {
        return listar("SELECT id, nombre, precio, stock FROM productos WHERE stock > 0 ORDER BY nombre");
    }

    // Todos los productos incluyendo sin stock (para el admin)
    public List<ProductoDTO> listarTodos() {
        return listar("SELECT id, nombre, precio, stock FROM productos ORDER BY nombre");
    }

    // Reduce el stock al confirmar una compra
    // Solo actualiza si hay suficiente stock disponible
    public boolean reducirStock(int idProducto, int cantidad) {
        String sql = "UPDATE productos SET stock=stock-? WHERE id=? AND stock>=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cantidad); ps.setInt(2, idProducto); ps.setInt(3, cantidad);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.err.println("Error reducir stock: " + e.getMessage()); return false; }
    }

    // Metodo interno reutilizable para ejecutar consultas SELECT de productos
    private List<ProductoDTO> listar(String sql) {
        List<ProductoDTO> lista = new ArrayList<>();
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next())
                lista.add(new ProductoDTO(rs.getInt("id"), rs.getString("nombre"),
                        rs.getDouble("precio"), rs.getInt("stock")));
        } catch (SQLException e) { System.err.println("Error listar productos: " + e.getMessage()); }
        return lista;
    }
}