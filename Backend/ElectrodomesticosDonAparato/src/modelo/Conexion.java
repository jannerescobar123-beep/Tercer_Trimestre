package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private String url = "jdbc:mysql://localhost:3306/tienda";
    private String user = "root";
    private String pass = "Janner12345@";

    public Connection getConexion(){

        try{
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado a la base de datos");
            return con;

        }catch(Exception e){
            System.out.println("Error al conectar");
            return null;
        }
    }
}