package leetcode;

import java.util.Arrays;

public class LeetCode260 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(singleNumber(new int[]{2, 1, 2, 3, 4, 1})));
    }
    public static int[] singleNumber(int[] nums) {

        int[] result = {0,0};

        int diff = 0;
        for (int num1 : nums) {
            diff ^= num1;
        }

        //看不懂
        diff &= -diff;
        for (int num : nums) {
            if ((diff &= num) == 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }

        System.out.println(diff);
//        int i = 0, j = 1;
//        while (i < nums.length && j < nums.length) {
//            if (nums[i] == nums[j]) {
//                if (i == j) {
//                    j++;
//                    continue;
//                }
//                j = 0;
//                i++;
//            } else {
//                j++;
//            }
//        }
//        result[0] = nums[i];
//
//        i = nums.length - 1;
//        j = i - 1;
//        while (i >= 0 && j >= 0) {
//            if (nums[i] == nums[j]) {
//                if (i == j) {
//                    j--;
//                    continue;
//                }
//                j = nums.length - 1;
//                i--;
//            } else {
//                j--;
//            }
//        }
//        result[1] = nums[i];
        return result;
    }

    private static int contains(int[] result, int k) {
        if (result[0] == k) {
            return 0;
        } else if (result[1] == k) {
            return 1;
        }
        return -1;
    }
}
