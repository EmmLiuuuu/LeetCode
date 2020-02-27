package leetcode;

import java.util.Arrays;

public class LeetCode300_LIS {

    private int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];

        int ans = 1;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    private int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //mArray[i][j]存储着
        //表示使用 nums[i] 作为上一个被认为包含/不包含在 lis 中的元素的 lis 可能的长度，其中 nums[j] 作为当前被认为包含/不包含在 lis 中的元素
        int[][] mArray = new int[nums.length][nums.length];
        for (int[] array : mArray) {
            Arrays.fill(array, -1);
        }
        return lengthOfLIS2(nums, mArray, -1, 0);
    }

    private int lengthOfLIS2(int[] nums, int[][] mArray, int preIndex, int cur) {
        if (cur == nums.length) {
            return 0;
        }

        //如果当前从preIndex+1到cur位置的递增子序列长度大于0，直接返回
        if (mArray[preIndex + 1][cur] >= 0) {
            return mArray[preIndex + 1][cur];
        }

        //递归计算，如果当前值取入递增子序列，得出这种做法得到的递增子序列长度
        int taken = 0;
        if (preIndex < 0 || nums[preIndex] < nums[cur]) {
            taken = 1 + lengthOfLIS2(nums, mArray, cur, cur + 1);
        }
        //不取入递增子序列中，跳过
        int noTaken = lengthOfLIS2(nums, mArray, preIndex, cur + 1);
        //得到当前preIndex+1到cur位置的最长递增子序列长度
        mArray[preIndex + 1][cur] = Math.max(taken, noTaken);
        //返回长度
        return mArray[preIndex + 1][cur];
    }


    //纯递归
    private int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return lengthOfLIS1(nums, Integer.MIN_VALUE, 0);
    }

    private int lengthOfLIS1(int[] nums, int comp, int start) {
        if (start == nums.length) {
            return 0;
        }
        int taken = 0;
        if (nums[start] > comp) {
            taken = 1 + lengthOfLIS1(nums, nums[start], start + 1);
        }
        int noTaken = lengthOfLIS1(nums, comp, start + 1);

        return Math.max(taken, noTaken);
    }

//    public int part(int[] a, int left, int right, int k) {
//        int i = left;
//        int j = right;
//
//        while (i < j) {
//            while (i < j && a[i] > a[left]) i++;
//            while (i < j && a[j] < a[left]) j--;
//
//            if (i < j) {
//                int temp = a[i];
//                a[i] = a[j];
//                a[j] = temp;
//            }
//        }
//
//        int temp = a[i];
//        a[i] = a[left];
//        a[left] = temp;
//
//        if (i == k) {
//            return i;
//        }
//
//        if (i > k) {
//            return part(a, 0, i, k);
//        } else {
//            return part(a, i, right, k);
//        }
//    }
}
