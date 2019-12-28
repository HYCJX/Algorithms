package commontypicalproblems;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {

    //Longest Substring Without Repeating Characters
    public int LengthOfSubstring(String s) {
        int n = s.length();
        int result = 0, i = 0, j = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (j < n) {
            Character c = s.charAt(j);
            if (map.containsKey(c)) {
                i = Math.max(i, map.get(c) + 1);
            }
            map.put(c, j);
            result = Math.max(result, j - i + 1);
            j++;
        }
        return result;
    }
}
