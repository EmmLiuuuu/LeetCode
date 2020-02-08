package leetcode;

//https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/yi-ge-tong-yong-fang-fa-tuan-mie-6-dao-gu-piao-wen/
public class LeetCode122 {

    public int maxProfit(int[] prices) {
        int result = 0;
        int buy = Integer.MAX_VALUE;
        int sell = 0;

        for (int i = 0; i < prices.length; i++) {
            //找到最低的买入价
            buy = Math.min(buy, prices[i]);
            //保证sell一定大于等于0
            sell = Math.max(sell, prices[i] - buy);

            //如果利益为正，重置，累加，进行下一次购买操作
            if (sell != 0) {
                buy = Integer.MAX_VALUE;
                result += sell;
                sell = 0;
                i--;
            }
        }

        return result;
    }

    //不停地找谷，峰，然后计算收益
    public int maxProfit1(int[] prices) {
        int valley = Integer.MAX_VALUE;
        int peek = 0;

        int maxProfit = 0;

        int i = 0;
        while (i < prices.length - 1) {

            //找谷
            while (i < prices.length - 1 && prices[i] < prices[i + 1]) {
                i++;
            }

            valley = prices[i];

            //找峰
            while (i < prices.length - 1 && prices[i] > prices[i + 1]) {
                i++;
            }

            peek = prices[i];

            //收益等于峰-谷
            maxProfit += (peek - valley);
        }

        return maxProfit;
    }

    //不断得找爬坡，累加所得收益
    public int maxProfit2(int[] prices) {
        int profit = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                profit += prices[i + 1] - prices[i];
            }
        }

        return profit;
    }
}
