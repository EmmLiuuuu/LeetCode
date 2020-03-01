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
                    min = nums[i + 1];
                    break;
                }
            }
        }
        return min;
    }

    public int findMin1(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        //局部有序
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (nums[mid] < nums[high]) {
                //走左边
                high = mid;
            } else if (nums[mid] > nums[high]) {
                //走右边
                low = mid + 1;
            } else {
                high--;
            }
        }
        return nums[low];
    }
}
