package leetcode;

//https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/yi-ge-tong-yong-fang-fa-tuan-mie-6-dao-gu-piao-wen/
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

    //将股票价格以折线图的形式画出，找到最低的谷（最低买入价格）以及最高的峰（最高卖出价）
    public int maxProfit1(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int profit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > profit) {
                profit = price - minPrice;
            }
        }

        return profit;
    }
}
