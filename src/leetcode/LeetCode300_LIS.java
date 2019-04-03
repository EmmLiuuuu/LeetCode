package leetcode;

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
