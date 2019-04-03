package leetcode;

public class LeetCode121 {

    public int maxProfit(int[] prices) {
        int firstBuy = Integer.MAX_VALUE;
        int afterFirstSell = 0;

        for (int price : prices) {
            firstBuy = Math.min(price, firstBuy);
            afterFirstSell = Math.max(afterFirstSell, price - firstBuy);
        }
        return afterFirstSell;
    }
}
