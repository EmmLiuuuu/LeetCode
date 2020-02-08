package leetcode;

//https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/yi-ge-tong-yong-fang-fa-tuan-mie-6-dao-gu-piao-wen/
public class LeetCode188 {

    public static void main(String[] args) {
        System.out.println(maxProfit(4, new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
    }

    public static int maxProfit(int[] prices) {
        int profit = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                profit += prices[i + 1] - prices[i];
            }
        }

        return profit;
    }

    public static int maxProfit(int k, int[] prices) {
        //当k为0时，收益一定为0
        if (k == 0) {
            return 0;
        }

        //当k大于可交易天数的1/2时，与无限次交易的情况一致（因为不允许连续交易）
        if (k > prices.length / 2) {
            return maxProfit(prices);
        }

        int firstBuy = Integer.MAX_VALUE;//第一次买入的价格
        // 接下来都是买入卖出之后的收益
        //用Array保存状态解决问题
        int[] result = new int[k * 2 - 1];
        for (int i = 0; i < k; i++) {
            if (i % 2 == 1) { //初始化使第N次时的buy为Integer.MIN_VALUE
                result[i] = Integer.MIN_VALUE;
            }
        }

        for (int curPrice : prices) {
            //第一次买入的价格，越低越好
            firstBuy = Math.min(firstBuy, curPrice);
//            afterFirstSell = Math.max(afterFirstSell, curPrice - firstBuy);
            //第一次卖出的收益，越高越好
            result[0] = Math.max(result[0], curPrice - firstBuy);
            for (int i = 1; i < result.length - 1; i += 2) {
//                afterSecondBuy = Math.max(afterSecondBuy, afterFirstSell - curPrice);
//                afterSecondSell = Math.max(afterSecondSell, afterSecondBuy + curPrice);
                //第i次买入后的收益，等于第i-1次卖出后的收益减去当前价格，越高越好
                result[i] = Math.max(result[i], result[i - 1] - curPrice);
                //第i次卖出后的收益，等于第二次买入后的收益加上当前价格，越高越好
                result[i + 1] = Math.max(result[i + 1], result[i] + curPrice);
            }
        }
        //最后一次卖出的价格即为最终收益
        return result[result.length - 1];
    }

    //dp, 三个状态，n天 k次交易 0/1是否拥有股票
    //dp[i][k][0]表示第i天还有k次交易的机会且当前未拥有股票, dp[n - 1][0][0]为最终交易结果
    //dp[-1][k][0] = 0, dp[-1][k][1] = -infinite
    //dp[i][k][0] = max{ dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]} 第i天不持有股票的情况下，最大收益为 上一天未持有股票时的收益 与 上一天持有股票且卖出后的收益 之间的最大值
    //dp[i][k][1] = max{ dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]} 第i天持有股票的情况下，最大收益为 上一天持有股票未卖出时的收益 与 上一天未持有股票但是第i天买入股票后的收益 之间的最大值
    //最终最大收益为dp[n-1][k][0], 意思为最后一天，交易了k次，未持有股票（持有股票时还有股票未卖出，肯定不为最大收益）时的收益
    public static int maxProfit1(int k, int[] prices) {

        //当k大于可交易天数的1/2时，与无限次交易的情况一致（因为不允许连续交易）
        if (k > prices.length / 2) {
            return maxProfit(prices);
        }

        //创建状态转移数组
        int[][][] dp = new int[prices.length][k + 1][2];

        for (int i = 0; i < prices.length; i++) {
            for (int j = k; j >= 1; j--) {
                if (i - 1 < 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }

        return dp[prices.length - 1][k][0];
    }
}
