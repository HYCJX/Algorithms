package commontypicalproblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SlidingWindow {

    //Longest substring without repeating characters:
    public static int LengthOfSubstring(String s) {
        int n = s.length();
        int result = 0, i = 0, j = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (j < n) {
            Character c = s.charAt(j);
            if (map.containsKey(c)) {
                //Jump to the position where the key has last appeared if needed.
                i = Math.max(i, map.get(c) + 1);
            }
            map.put(c, j);
            result = Math.max(result, j - i + 1);
            j++;
        }
        return result;
    }

    //Shortest substring that contains all desired characters:
    //@param s: the string on which the substring is taken.
    //@param t: the string that indicates the characters which the substring should include.
    public static String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        Map<Character, Integer> dictT = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }
        int required = dictT.size();
        //Maintain a list of useful characters.
        List<Pair<Integer, Character>> filteredS = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (dictT.containsKey(c)) {
                filteredS.add(new Pair<>(i, c));
            }
        }
        //l - left boundary, r - right boundary, formed - number of characters with their targeted counting reached.
        int l = 0, r = 0, formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();
        int[] ans = {-1, 0, 0};
        while (r < filteredS.size()) {
            char c = filteredS.get(r).getValue();
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);
            if (windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }
            while (l <= r && formed == required) {
                c = filteredS.get(l).getValue();
                int start = filteredS.get(l).getKey();
                int end = filteredS.get(r).getKey();
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (windowCounts.get(c) < dictT.get(c)) {
                    formed--;
                }
                //Try to minimize the window.
                l++;
            }
            //Start a new sliding window.
            r++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    private static class Pair<K, V> {

        private K key;
        private V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

    }

}
