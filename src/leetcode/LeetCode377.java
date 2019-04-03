package leetcode;

public class LeetCode377 {

//    private int count = 0;
//    public int combinationSum4(int[] nums, int target) {
//
//        combine(0, 0, target, nums);
//
//        return count;
//
//    }
//
//    public void combine(int index, int total, int target, int[] nums) {
//        if (total > target) {
//            return;
//        }
//
//        if (total == target) {
//            count++;
//            return;
//        }
//
//        for (int i = index; i < nums.length; i++) {
//
//            combine(index, total + nums[i], target, nums);
//
//        }
//    } low b 超时算法

    //dp

    //分解为子问题
    /*
    计算得到target为1的方式个数
    那么target为2的方式的个数等于target（2） + target（1）+ target(2-1)+ target（0）
    。。。
    即为 target(n) = target(n) + sum(target(n-m) + target(m)) (m->(0-n),m属于nums)
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];

        for (int num : nums) {
            if (num <= target) {
                dp[num] = 1;
            }
        }

        for (int i = 1; i <= target ; i++) {

            for (int num : nums) {

                if (i >= num) {
                    dp[i] = dp[i] + dp[i - num];
                }
            }
        }

        return dp[target];
    }
}
