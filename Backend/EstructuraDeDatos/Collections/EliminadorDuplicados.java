package Collections;

import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class EliminadorDuplicados {
    public static List<String> limpiarLista(List<String> entrada) {
        Set<String> sinDuplicados = new LinkedHashSet<>(entrada);

        return new ArrayList<>(sinDuplicados);
    }

    public static void main(String[] args) {
        List<String> nombres = Arrays.asList("Ana", "Pedro", "Ana", "Luis", "Pedro");
        System.out.println(limpiarLista(nombres));
    }
}
