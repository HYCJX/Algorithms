package dataStructure.treeUsage;

import java.util.*;

public class BreadthFirstSearch {

    //Word ladder:
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (beginWord == null || beginWord.isEmpty() ||
                endWord == null || endWord.isEmpty())
            return 0;
        int length = 1;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                for (int j = 0; j < curr.length(); j++) {
                    char[] charCurr = curr.toCharArray();
                    for (char c = 'a'; c < 'z'; c++) {
                        charCurr[j] = c;  // change one character at a time
                        String strCurr = new String(charCurr);
                        if (dict.contains(strCurr)) {
                            if (strCurr.equals(endWord)) {
                                return length + 1;
                            } else {
                                queue.offer(strCurr);
                                dict.remove(strCurr);
                            }
                        }
                    }
                }
            }
            length++;
        }
        return 0;
    }
}
