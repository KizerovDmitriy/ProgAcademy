package program;

import java.util.HashMap;
import java.util.Map;

public class HasMapArrayCount {

    public Map<String, Integer> countElements(String[] strings) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : strings) {
            if (map.containsKey(s)) {
                int tmp = map.get(s) + 1;
                map.put(s, tmp);
            } else {
                map.put(s, 1);
            }
        }
        return map;
    }

    public Map<Integer, Integer> countElements(int[] ints) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer integer : ints) {
            if (map.containsKey(integer)) {
                int tmp = map.get(integer) + 1;
                map.put(integer, tmp);
            } else {
                map.put(integer, 1);
            }
        }
        return map;
    }
}