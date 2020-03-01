package leetcode;

public class LeetCode35 {

    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        //记得这里的等号，不能直接跳出，给low，high前进一步找插入位置的机会
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return low;
    }
}
