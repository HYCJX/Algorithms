package commontypicalproblems;

import java.util.*;

public class CommonTypicalProblems {

    //Check whether a partially-filled sudoku is valid:
    public static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            Set<Integer> rowSet = new HashSet<>();
            Set<Integer> colSet = new HashSet<>();
            Set<Integer> cubeSet = new HashSet<>();
            for (int j = 0; j < board[0].length; j++) {
                //Check row:
                if (board[i][j] != '.' && (!Character.isDigit(board[i][j]) || !rowSet.add(board[i][j] - '0'))) {
                    return false;
                }
                //Check column:
                if (board[j][i] != '.' && (!Character.isDigit(board[j][i]) || !colSet.add(board[j][i] - '0'))) {
                    return false;
                }
                //Check cube:
                int topLeftICube = 3 * (i / 3);
                int topLeftJCube = 3 * (i % 3);
                int positionI = topLeftICube + (j / 3);
                int positionJ = topLeftJCube + (j % 3);
                if (board[positionI][positionJ] != '.' &&
                        (!Character.isDigit(board[positionI][positionJ]) ||
                                !cubeSet.add(board[positionI][positionJ] - '0'))) {
                    return false;
                }
            }
        }
        return true;
    }

    //Three Sum
    public static List<List<Integer>> threeSum(int[] nums) {
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
}
