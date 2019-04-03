package leetcode;

import java.util.Arrays;

public class LeetCode238 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 0})));
    }

    public static int[] productExceptSelf(int[] nums) {

        int sum = 1;
        int zero = 0;
        int sumExceptZero = 1;
        for (int num : nums) {
            if (num == 0) {
                ++zero;
            }
            sum *= num;
            sumExceptZero *= (num == 0 ? 1 : num);
        }

        if (zero > 1) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = 0;
            }
            return nums;
        }

        int[] temp = new int[nums.length];

        System.arraycopy(nums, 0, temp, 0, nums.length);

        for (int i = 0; i < nums.length; i++) {
            if (temp[i] == 0) {
                nums[i] = sumExceptZero;
//                for (int j = 0; j < temp.length; j++) {
//                    if (j == i) {
//                        continue;
//                    }
//                    nums[i] *= temp[j];
//                }
            } else {
                nums[i] = sum / nums[i];
            }
        }

        return nums;
    }
}
