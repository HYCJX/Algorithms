package commontypicalproblems;

import java.util.ArrayList;
import java.util.List;

public class BackTrack {

    //Generate all valid parentheses compositions giving the number of pairs of parentheses:
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private static void backtrack(List<String> result, String current, int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }

    //Generate all permutations of a list:
    public static  List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        find(result, new ArrayList<>(), nums);
        return result;
    }

    private static void find(List<List<Integer>> result, List<Integer> workingList, int[] nums) {
        if (workingList.size() == nums.length) {
            result.add(new ArrayList<>(workingList));
        } else {
            for (int num : nums) {
                if (workingList.contains(num)) {
                    continue;
                }
                workingList.add(num);
                find(result, workingList, nums);
                workingList.remove(workingList.size() - 1);
            }
        }
    }
}
