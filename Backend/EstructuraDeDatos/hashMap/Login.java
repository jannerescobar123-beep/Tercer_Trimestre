package hashMap;

import java.util.HashMap;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        HashMap<String, String> baseDeDatos = new HashMap<>();
        baseDeDatos.put("admin", "1234");
        baseDeDatos.put("user_java", "p@ssword");

        Scanner sc = new Scanner(System.in);
        System.out.print("Usuario: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        if (baseDeDatos.containsKey(user) && baseDeDatos.get(user).equals(pass)) {
            System.out.println("¡Acceso concedido!");
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
        sc.close();
    }
}
