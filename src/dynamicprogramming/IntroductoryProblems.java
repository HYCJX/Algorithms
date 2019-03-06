package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class IntroductoryProblems {

    //Two Sum
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution.");
    }

    //Longest Palindromic Substring
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int length1 = expandAroundCenter(s, i, i);
            int length2 = expandAroundCenter(s, i, i + 1);
            int length = Math.max(length1, length2);
            if (length > end - start) {
                start = i - (length - 1) / 2;
                end = i + length / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return left - right - 1;
    }

    //Maximum Sub-array
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        if (nums.length == 1) {
            return max;
        }
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i], nums[i] + nums[i - 1]);
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    //Range Sum with large query numbers
    public int sumRange(int[] nums, int i, int j) {
        int[] sum = new int[nums.length - 1];
        sum[0] = 0;
        for (int k = 0; k < nums.length; k++) {
            sum[k + 1] = sum[k] + nums[k];
        }
        return sum[j + 1] - sum[i];
    }

    //Minimum Cost Stairs-climbing
    //From the head
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 0) {
            return 0;
        } else if (cost.length == 1) {
            return cost[0];
        }
        for (int i = 2; i < cost.length; i++) {
            cost[i] = Math.min(cost[i - 2], cost[i - 1]) + cost[i];
        }
        return Math.min(cost[cost.length - 1], cost[cost.length - 2]);
    }
    //From the end
    public int minCostStairsClimbing(int[] cost) {
        int res1 = 0, res2 = 0;
        for (int i = cost.length - 1; i >= 0; i--) {
            int res = cost[i] + Math.min(res1, res2);
            res2 = res1;
            res1 = res;
        }
        return Math.min(res1, res2);
    }


}
