package commontypicalproblems;

public class BitsManipulation {

    //In a list, every number appears twice except one number. Find the unique number:
    public static int singleNumber(int[] nums) {
        int n = 0;
        for (int num : nums) {
            n ^= num;
        }
        return n;
    }

}
