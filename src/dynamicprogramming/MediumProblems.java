package dynamicprogramming;

public class MediumProblems {

    //Expression matching:
    //Match Two expressions, the second expression can contain '.' and '*'.
    //'.' matches any single character; '*' matches zero or more of the preceding element.
    public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        //Build from tail to head.
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
    public static String manchorsAlgorithm(String s) {
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
            if (expand) {
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
