package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class IntroductoryProblems {

    //Two Sum:
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution.");
    }

    //Longest Palindromic Substring:
    public static String longestPalindrome(String s) {
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

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return left - right - 1;
    }

    //Maximum Sub-array:
    public static int maxSubArray(int[] nums) {
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

    //Range Sum with large query numbers:
    public static int sumRange(int[] nums, int i, int j) {
        int[] sum = new int[nums.length - 1];
        sum[0] = 0;
        for (int k = 0; k < nums.length; k++) {
            sum[k + 1] = sum[k] + nums[k];
        }
        return sum[j + 1] - sum[i];
    }

    //Minimum Cost Stairs-climbing:
    //M1: From the head:
    public static int minCostClimbingStairs(int[] cost) {
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

    //M2: From the end:
    public static int minCostStairsClimbing(int[] cost) {
        int res1 = 0, res2 = 0;
        for (int i = cost.length - 1; i >= 0; i--) {
            int res = cost[i] + Math.min(res1, res2);
            res2 = res1;
            res1 = res;
        }
        return Math.min(res1, res2);
    }

    //Expression matching:
    //Match Two expressions, the second expression can contain '.' and '*'.
    //'.' matches any single character; '*' matches zero or more of the preceding element.
    public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean first_match = i < s.length() &&
                        (p.charAt(j) == s.charAt(i) ||
                                p.charAt(j) == '.');
                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    //Longest Palindromic Substring - Manchor's algorithm:
    public String manchorsAlgorithm(String s) {
        int N = s.length();
        if (N == 0) return "";
        N = 2 * N + 1; //Position count.
        int[] L = new int[N]; //LPS Length Array.
        L[0] = 0;
        L[1] = 1;
        int C = 1; //centerPosition.
        int R = 2; //centerRightPosition.
        int i = 2; //currentRightPosition.
        int iMirror; //currentLeftPosition.
        boolean expand;
        int diff = -1;
        int maxLPSLength = 0;
        int maxLPSCenterPosition = 0;
        for (i = 2; i < N; i++) {
            //Get currentLeftPosition iMirror for currentRightPosition i:
            iMirror = 2 * C - i;
            //Reset expand - means no expansion required:
            expand = false;
            diff = R - i;
            //If currentRightPosition i is within centerRightPosition R:
            if (diff > 0) {
                if (L[iMirror] < diff) { // Case 1.
                    L[i] = L[iMirror];
                } else if (L[iMirror] == diff && i == N - 1) { // Case 2.
                    L[i] = L[iMirror];
                } else if (L[iMirror] == diff && i < N - 1) { // Case 3.
                    L[i] = L[iMirror];
                    expand = true;  // expansion required.
                } else if (L[iMirror] > diff) { // Case 4.
                    L[i] = diff;
                    expand = true;  // expansion required.
                }
            } else {
                L[i] = 0;
                expand = true;  // expansion required.
            }
            if (expand == true) {
                //Attempt to expand palindrome centered at currentRightPosition i.
                //Here for odd positions, we compare characters,
                //if match then increment LPS Length by ONE.
                //If even position, we just increment LPS by ONE,
                //without any character comparison.
                while ((i + L[i]) < N &&
                        (i - L[i]) > 0 &&
                        (((i + L[i] + 1) % 2 == 0) ||
                                (s.charAt((i + L[i] - 1) / 2) == s.charAt((i - L[i] - 1) / 2)))) {
                    L[i]++;
                }
                if ((i + L[i] + 1) % 2 != 0) L[i]--;
            }
            if (L[i] > maxLPSLength) { // Track maxLPSLength.
                maxLPSLength = L[i];
                maxLPSCenterPosition = i;
            }
            // If palindrome centered at currentRightPosition i
            // expand beyond centerRightPosition R,
            // adjust centerPosition C based on expanded palindrome.
            if (i + L[i] > R) {
                C = i;
                R = i + L[i];
            }
        }
        int start = (maxLPSCenterPosition - maxLPSLength) / 2;
        int end = start + maxLPSLength;
        return s.substring(start, end);
    }


}
