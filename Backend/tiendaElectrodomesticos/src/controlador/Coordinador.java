package controlador;

import modelo.LogicaDeVenta;
import modelo.dao.*;
import modelo.dto.*;
import java.util.List;

// Puente entre la GUI y el Modelo
// La GUI nunca habla directo con los DAO, siempre pasa por aquí
public class Coordinador {

    private UsuarioDTO sesion = null;

    private final UsuarioDAO    usuarioDAO  = new UsuarioDAO();
    private final ClienteDAO    clienteDAO  = new ClienteDAO();
    private final ProductoDAO   productoDAO = new ProductoDAO();
    private final ComprasDAO    comprasDAO  = new ComprasDAO();
    private final LogicaDeVenta logica      = new LogicaDeVenta();

    // --- SESIÓN ---
    public UsuarioDTO login(String username, String password) {
        sesion = usuarioDAO.login(username, password);
        return sesion;
    }
    public void       logout()       { 
    	sesion = null; 
    }
    public UsuarioDTO getSesion()    { 
    	return sesion; 
    }

    // --- USUARIOS ---
    public boolean registrarUsuario(String username, String password, String rol) {
        if (usuarioDAO.existeUsername(username)) return false;
        return usuarioDAO.registrar(username, password, rol);
    }
    public List<UsuarioDTO> obtenerUsuarios()    { return usuarioDAO.listarTodos(); }
    public boolean eliminarUsuario(int id) {
        if (sesion != null && sesion.getId() == id) return false;
        return usuarioDAO.eliminar(id);
    }

    // --- CLIENTES ---
    public boolean registrarCliente(ClienteDTO dto) {
        return clienteDAO.insertar(dto);
    }
    public boolean registrarClienteConUsuario(String nombre, String apellido, int edad,String telefono, String tipo,String email, int idUsuario) {
        ClienteDTO dto = new ClienteDTO(nombre, apellido, edad, telefono, tipo, email);
        dto.setIdUsuario(idUsuario);
        return clienteDAO.insertar(dto);
    }
    public boolean actualizarCliente(ClienteDTO dto) {
    	return clienteDAO.actualizar(dto); 
    }
    public boolean eliminarCliente(int id) {
    	return clienteDAO.eliminar(id); }
    public List<ClienteDTO> obtenerClientes() { 
    	return clienteDAO.listarTodos(); 
    }
    public ClienteDTO obtenerClientePorUsuario(int id) {
    	return clienteDAO.buscarPorIdUsuario(id); 
    }

    // --- PRODUCTOS ---
    public boolean agregarProducto(String nombre, double precio, int stock) {
        return productoDAO.insertar(new ProductoDTO(nombre, precio, stock));
    }
    
    public boolean actualizarProducto(ProductoDTO dto)     { 
    	return productoDAO.actualizar(dto); 
    }
    
    public boolean eliminarProducto(int id)                { 
    	return productoDAO.eliminar(id); 
    }
    
    public List<ProductoDTO> obtenerProductosDisponibles() { 
    	return productoDAO.listarDisponibles(); 
    }
    public List<ProductoDTO> obtenerTodosProductos()       { 
    	return productoDAO.listarTodos(); 
    }

    // --- COMPRAS ---

    // Procesa la compra con descuento según el tipo del cliente
    // Retorna ComprasDTO con todos los valores calculados
    public ComprasDTO realizarCompraConDescuento(ClienteDTO cliente,ProductoDTO producto, int cantidad) {
        return logica.procesarVenta(cliente, producto, cantidad);
    }

    // Versión simple para el admin (sin descuento por tipo)
    public String realizarCompra(ProductoDTO producto, int idCliente, int cantidad) {
        return logica.procesarVenta(producto, idCliente, cantidad);
    }

    public List<ComprasDTO> obtenerHistorialCompras(){
    	return comprasDAO.listarTodas(); 
    }
    
    public List<ComprasDTO> obtenerComprasPorCliente(int id) { 
    	return comprasDAO.listarPorCliente(id); 
    }
    
}