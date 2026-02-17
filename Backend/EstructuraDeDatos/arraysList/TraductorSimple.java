package arraysList;

public class TraductorSimple {
    public static void main(String[] args) {
        String[] palabrasIngles = {"hello", "world", "cat", "dog", "house"};
        String[] palabrasEspanol = {"hola", "mundo", "gato", "perro", "casa"};

        String palabraAtraducir = "cat";
        String traduccion = traducir(palabraAtraducir, palabrasIngles, palabrasEspanol);

        if (traduccion != null) {
            System.out.println("La traducción de '" + palabraAtraducir + "' es: " + traduccion);
        } else {
            System.out.println("La palabra '" + palabraAtraducir + "' no se encuentra en el diccionario.");
        }
    }
    public static String traducir(String palabra, String[] ingles, String[] espanol) {
        for (int i = 0; i < ingles.length; i++) {
            if (ingles[i].equalsIgnoreCase(palabra)) {
                return espanol[i];
            }
        }
        return null;
    }
}
