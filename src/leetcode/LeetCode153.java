package leetcode;

public class LeetCode153 {

    public int findMin(int[] nums) {
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
