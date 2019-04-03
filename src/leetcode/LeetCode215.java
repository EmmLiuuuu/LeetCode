package leetcode;

public class LeetCode215 {

    public int findKthLargest(int[] nums, int k) {
        return nums[part(nums, 0, nums.length - 1, k)];
    }

    public int part(int[] nums, int left, int right, int k) {

        int i = left;
        int j = right;
        if (left > right) {
            return -1;
        }

        while (i != j) {
            while (i < j && nums[j] < nums[left]) j--;
            while (i < j && nums[i] >= nums[left]) i++;
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }


        int temp = nums[left];
        nums[left] = nums[i];
        nums[i] = temp;

        if (i == k - 1) {
            return i;
        } else if (i > k - 1) {
            return part(nums, left, i - 1, k);
        } else {
            return part(nums, i + 1, right, k);
        }
    }
}
