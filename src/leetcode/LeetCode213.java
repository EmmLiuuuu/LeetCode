package leetcode;

public class LeetCode213 {

    //dp[i] = max(dp[i - 2] + nums[i], dp[i - 1])
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        //既然房子是首尾相连的，要么第一间房子被抢最后一间不抢，要么最后一间房子被抢第一间不抢，遍历两种情况，取最大值
        return Math.max(robRange(nums, 0, nums.length - 2), robRange(nums, 1, nums.length - 1));
    }

    public int robRange(int[] nums, int start, int end) {
        int cur = 0, prev = 0, prev_2 = 0;
        for (int i = start; i < end + 1; i++) {
            cur = Math.max(prev, prev_2 + nums[i]);
            prev_2 = prev;
            prev = cur;
        }
        return cur;
    }
}
