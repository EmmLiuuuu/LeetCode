package leetcode;

public class LeetCode198 {

    //dp[i] = max(dp[i - 2] + nums[i], dp[i - 1])
    public int rob(int[] nums) {
        //dp[i], dp[i - 2], dp[i - 1]
        int cur = 0, prev = 0, prev1 = 0;
        for (int num : nums) {
            //dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
            cur = Math.max(prev1, prev + num);
            prev = prev1;
            prev1 = cur;
        }
        return cur;
    }

    //递归，反过来想，从最后一户开始算
    public int rob(int[] nums, int i, int sum) {
        //跳出条件
        if (i < 0) {
            //直接返回走完一轮后的收益
            return sum;
        }

        //要么不偷第i户，去偷下一户，要么偷第i户，然后接着去偷第i-2户
        return Math.max(rob(nums, i - 1, sum), rob(nums, i - 2, sum + nums[i]));
    }
}
