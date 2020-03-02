package leetcode;

public class LeetCode162 {

    //寻找峰值的话相当于是找递增子数组的拐点，部分有序
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            //mid在拐点后面，需要将right往前挪
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                //mid在拐点前面，需要将left往前挪
                left = mid + 1;
            }
        }
        //当left==right的时候，找到拐点
        return left;
    }
}
