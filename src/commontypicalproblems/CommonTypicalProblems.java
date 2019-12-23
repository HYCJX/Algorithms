package commontypicalproblems;

import java.util.*;

public class CommonTypicalProblems {

    //Three Sum
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            int num1 = nums[i];
            int target = 0 - num1;
            int p = i + 1, q = n - 1;
            if (i != 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            while (p < q) {
                int num2 = nums[p];
                int num3 = nums[q];
                if (num2 + num3 == target) {
                    List<Integer> solution = new ArrayList<>();
                    solution.add(num1);
                    solution.add(num2);
                    solution.add(num3);
                    result.add(solution);
                    while (p < q && nums[p + 1] == nums[p]) {
                        p++;
                    }
                    while (p < q && nums[q - 1] == nums[q]) {
                        q--;
                    }
                    p++;
                    q--;
                } else if (num2 + num3 < target) {
                    p++;
                } else {
                    q--;
                }
            }
        }
        return result;
    }

    //Longest Substring Without Repeating Characters
    //Method: Sliding Window
    public int LengthOfSubstring(String s){
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
