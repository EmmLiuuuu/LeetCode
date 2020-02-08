package leetcode;

public class LeetCode714 {

    //dp数组
    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];

        for (int i = 0; i < prices.length; i++) {
            if (i - 1 < 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }

            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    //化简
    public int maxProfit1(int[] prices, int fee) {
        int dpI0 = 0;
        int dpI1 = -prices[0];

        for (int price :
                prices) {
            dpI0 = Math.max(dpI0, dpI1 + price - fee);
            dpI1 = Math.max(dpI1, dpI0 - price);
        }

        return dpI0;
    }
}
