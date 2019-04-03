package leetcode;

public class LeetCode122 {

    public int maxProfit(int[] prices) {
        int result = 0;
        int buy = Integer.MAX_VALUE;
        int sell = 0;

        for (int i = 0; i < prices.length; i++) {
            buy = Math.min(buy, prices[i]);
            sell = Math.max(sell, prices[i] - buy);

            if (sell != 0) {
                buy = Integer.MAX_VALUE;
                result += sell;
                sell = 0;
                i--;
            }
        }

        return result;
    }
}
