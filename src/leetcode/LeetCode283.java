package leetcode;

public class LeetCode283 {

    public static void main(String[] args) {
        moveZeroes(new int[]{1});
    }
    public static void moveZeroes(int[] nums) {
        // n方解法
        // int len = nums.length;
        // for (int i = 0; i < len; i++) {
        //     if (nums[i] == 0) {
        //         int temp = nums[i];
        //         for (int j = i; j < nums.length - 1; j++) {
        //             nums[j] = nums[j + 1];
        //         }
        //         nums[nums.length - 1] = temp;
        //         len--;
        //     }
        //     if (nums[i] == 0) {
        //         i--;
        //     }
        // }


//        //线性复杂度sb解法 O(n)
//        if (nums.length == 1) {
//            return;
//        }
//
//        int index = -1;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == 0) {
//                continue;
//            }
//            nums[0] = nums[i];
//            if (i != 0) {
//                nums[i] = 0;
//            }
//            index = 1;
//            break;
//        }
//
//        if (index == -1) {
//            return;
//        }
//
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] != 0) {
//                if (index == i) {
//                    index++;
//                    continue;
//                }
//                nums[index++] = nums[i];
//                nums[i] = 0;
//            }
//        }

        //简单解法
        for (int index = 0, i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index++] = temp;
            }
        }
    }
}
