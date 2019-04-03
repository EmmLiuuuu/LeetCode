package leetcode;

public class LeetCode154 {

    //二分
    public int findMin(int[] nums) {

        int pre = nums.length / 2;

        int min = nums[0];
        boolean flag = true;

        for (int i = 0; i < pre; i++) {
            if (nums[i] > nums[i+1]) {
                min = nums[i+1];
                flag = false;
                break;
            }
        }

        if (flag) {
            for (int i = pre; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    min = nums[i+1];
                    break;
                }
            }
        }
        return min;
    }
}
