package modelo.dao;

import modelo.Conexion;
import modelo.dto.ComprasDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Maneja todas las operaciones SQL de la tabla compras
public class ComprasDAO {

    public boolean insertar(ComprasDTO dto) {
        String sql = "INSERT INTO compras (id_cliente, id_producto, cantidad, " +
                "valor_unitario, total_sin_desc, descuento, total_final) VALUES (?,?,?,?,?,?,?)";
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dto.getIdCliente());
            ps.setInt(2, dto.getIdProducto());
            ps.setInt(3, dto.getCantidad());
            ps.setDouble(4, dto.getValorUnitario());
            ps.setDouble(5, dto.getTotalSinDesc());
            ps.setDouble(6, dto.getDescuento());
            ps.setDouble(7, dto.getTotalFinal());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error insertar compra: " + e.getMessage());
            return false;
        }
    }

    // Todas las compras (para el admin)
    public List<ComprasDTO> listarTodas() {
        return consultar(-1);
    }

    // Solo las compras de un cliente (para el usuario normal)
    public List<ComprasDTO> listarPorCliente(int idCliente) {
        return consultar(idCliente);
    }

    // Ejecuta el SELECT con JOIN. Filtra por cliente si idCliente > 0
    private List<ComprasDTO> consultar(int idCliente) {
        List<ComprasDTO> lista = new ArrayList<>();
        String sql = "SELECT c.id, c.cantidad, c.valor_unitario, c.total_sin_desc, " +
                "c.descuento, c.total_final, " +
                "cl.nombre AS nombre_cliente, pr.nombre AS nombre_producto " +
                "FROM compras c " +
                "JOIN clientes cl  ON c.id_cliente  = cl.id " +
                "JOIN productos pr ON c.id_producto = pr.id " +
                (idCliente > 0 ? "WHERE c.id_cliente=? " : "") +
                "ORDER BY c.id DESC";
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            if (idCliente > 0)
                ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ComprasDTO dto = new ComprasDTO();
                dto.setId(rs.getInt("id"));
                dto.setCantidad(rs.getInt("cantidad"));
                dto.setValorUnitario(rs.getDouble("valor_unitario"));
                dto.setTotalSinDesc(rs.getDouble("total_sin_desc"));
                dto.setDescuento(rs.getDouble("descuento"));
                dto.setTotalFinal(rs.getDouble("total_final"));
                dto.setNombreCliente(rs.getString("nombre_cliente"));
                dto.setNombreProducto(rs.getString("nombre_producto"));
                lista.add(dto);
            }
        } catch (SQLException e) {
            System.err.println("Error listar compras: " + e.getMessage());
        }
        return lista;
    }
}