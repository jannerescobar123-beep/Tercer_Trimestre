package hashTable;

import java.util.Hashtable;

public class contieneDuplicadoCercano {
    public boolean contieneDuplicador (int[] nums, int k) {
        Hashtable<Integer, Integer> tabla = new Hashtable<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (tabla.containsKey(nums[i])) {
                if (i - tabla.get(nums[i]) <= k) return true;
            }
            tabla.put(nums[i], i);
        }
        return false;
    }
}
