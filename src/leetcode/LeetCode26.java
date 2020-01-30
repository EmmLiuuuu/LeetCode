package leetcode;

public class LeetCode26 {

    class Solution {
        //与最优解解法一致
        public int removeDuplicates(int[] nums) {
            if (nums.length <= 1) {
                return nums.length;
            }
            int i = 0;
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] != nums[j]) {
                    nums[++i] = nums[j];
                }
            }
            return i + 1;
        }
    }
}
