package leetcode;

import java.util.Arrays;

public class LeetCode334 {

    //dp
    public boolean increasingTriplet1(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

                if (dp[i] == 3) {
                    return true;
                }
            }
        }

        return false;
    }

    //双指针
    //m1, m2保存两个较小数，找出一个同时大于m1和m2的数即返回。
    public boolean increasingTriplet(int[] nums) {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;

        for (int num : nums) {
            if (a >= num) {
                a = num;
            } else if (b >= num) {
                b = num;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean increasingTriplet2(int[] nums) {
        int[] forward = new int[nums.length];
        forward[0] = Integer.MAX_VALUE;
        int[] backward = new int[nums.length];
        backward[nums.length - 1] = Integer.MIN_VALUE;

        for (int i = 1; i < nums.length; i++) {
            forward[i] = Math.min(nums[i], forward[i - 1]);
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            backward[i] = Math.max(nums[i], backward[i + 1]);
        }

        for (int i = 0; i < nums.length; i++) {
            if (forward[i] < nums[i] && nums[i] < backward[i]) {
                return true;
            }
        }

        return false;
    }
}
