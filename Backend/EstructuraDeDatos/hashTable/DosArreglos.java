package hashTable;
import java.util.ArrayList;
import java.util.Hashtable;

public class DosArreglos{
    public int[] interseccion(int[] nums1, int[] nums2) {
        Hashtable<Integer, Boolean> visto = new Hashtable<>();
        ArrayList<Integer> resultado = new ArrayList<>();

        for (int n : nums1) visto.put(n, true);


        for (int n : nums2) {
            if (visto.containsKey(n)) {
                resultado.add(n);
                visto.remove(n); 
            }
        }
        return resultado.stream().mapToInt(i -> i).toArray();
    }
}