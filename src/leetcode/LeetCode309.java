package leetcode;

public class LeetCode309 {

    //dp数组解决
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];

        for (int i = 0; i < prices.length; i++) {
            if (i - 1 < 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }

            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[(Math.max(i - 2, 0))][0] - prices[i]);
        }

        return prices.length == 0 ? 0 : dp[prices.length - 1][0];
    }

    //化简
    public int maxProfit1(int[] prices) {
        int dpI0 = 0;
        int dpI1 = Integer.MIN_VALUE;
        int dpPreI0 = 0;

        for (int price :
                prices) {
            int temp = dpI0;
            dpI0 = Math.max(dpI0, dpI1 + price);
            dpI1 = Math.max(dpI1, dpPreI0 - price);
            dpPreI0 = temp;
        }

        return dpI0;
    }
}
