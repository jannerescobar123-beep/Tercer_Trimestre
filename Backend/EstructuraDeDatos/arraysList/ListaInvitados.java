package arraysList;
import java.util.ArrayList;

public class ListaInvitados {
    public static void main(String[] args) {
        ArrayList<String> invitados = new ArrayList<String>();
        invitados.add("Pepe");
        invitados.add("Roberto");
        invitados.add("Janner");
        invitados.add("Mariana");
        invitados.add("Sofia");

        System.out.println("Lista de invitados:");
        for (String invitado : invitados) {
            System.out.println(invitado);
        }
        invitados.remove("Roberto");
        System.out.println("Lista de invitados después de eliminar a Roberto:");
        for (String invitado : invitados) {
            System.out.println(invitado);
        }
    }
}
