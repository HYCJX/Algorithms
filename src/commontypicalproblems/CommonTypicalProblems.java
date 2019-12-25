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

}
