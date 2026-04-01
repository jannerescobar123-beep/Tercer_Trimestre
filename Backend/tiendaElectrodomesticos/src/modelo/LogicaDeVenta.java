package modelo;

import modelo.dao.ComprasDAO;
import modelo.dao.ProductoDAO;
import modelo.dto.ClienteDTO;
import modelo.dto.ComprasDTO;
import modelo.dto.ProductoDTO;

public class LogicaDeVenta {

    private final ComprasDAO  comprasDAO  = new ComprasDAO();
    private final ProductoDAO productoDAO = new ProductoDAO();

    public ComprasDTO procesarVenta(ClienteDTO cliente, ProductoDTO producto, int cantidad) {


        if (cantidad <= 0) return null;

        //stock suficiente
        if (cantidad > producto.getStock()) return null;

        double valorUnitario = producto.getPrecio();
        double totalSinDesc  = valorUnitario * cantidad;

        // Calcula el descuento 
        double porcentaje = cliente.getPorcentajeDescuento();
        double descuento  = totalSinDesc * porcentaje;
        double totalFinal = totalSinDesc - descuento;

        // Crea el DTO con los valores calculados
        ComprasDTO compra = new ComprasDTO(
            cliente.getId(), producto.getId(), cantidad,
            valorUnitario, totalSinDesc, descuento, totalFinal
        );

        // Guarda la compra en BD
        boolean guardada = comprasDAO.insertar(compra);
        if (!guardada) return null;

        productoDAO.reducirStock(producto.getId(), cantidad);

        return compra;
    }

    public String procesarVenta(ProductoDTO producto, int idCliente, int cantidad) {
        if (cantidad <= 0)              return "La cantidad debe ser mayor a 0.";
        if (cantidad > producto.getStock())
            return "Stock insuficiente. Solo hay " + producto.getStock() + " unidades.";

        double total = producto.getPrecio() * cantidad;
        ComprasDTO dto = new ComprasDTO(idCliente, producto.getId(), cantidad,
                producto.getPrecio(), total, 0, total);

        boolean ok = comprasDAO.insertar(dto);
        if (!ok) return "Error al registrar la compra en la base de datos.";
        productoDAO.reducirStock(producto.getId(), cantidad);
        return "Compra realizada. Total: $" + String.format("%,.0f", total);
    }
}