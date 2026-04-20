package basedatos;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 * Clase utilitaria para obtener la conexión JDBC con MySQL.
 * Ajusta URL, USER y PASSWORD según tu entorno.
 */
public class Conexion {
 
    private static final String URL = "jdbc:mysql://localhost:3306/personas_db?useSSL=false&serverTimezone=UTC";
    private static final String USER     = "root";
    private static final String PASSWORD = "Janner12345@";
 
    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL no encontrado: " + e.getMessage());
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}