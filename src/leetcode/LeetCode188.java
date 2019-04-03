package leetcode;

import java.util.Stack;

public class LeetCode188 {

    public static void main(String[] args) {
        System.out.println(maxProfit(4, new int[] {1,2,4,2,5,7,2,4,9,0}));
    }

    public static int maxProfit(int k, int[] prices) {

        if (k == 0) {
            return 0;
        }


        int firstBuy = Integer.MAX_VALUE;//第一次买入的价格
        // 接下来都是买入卖出之后的收益

        //用Array保存状态解决问题
        //失败，超内存
        int[] result = new int[k * 2 - 1];
        for (int i = 0; i < k; i++) {
            if (i % 2 == 1) { //初始化使第N次时的buy为Integer.MIN_VALUE
                result[i] = Integer.MIN_VALUE;
            }
        }

        for (int curPrice : prices) {
            firstBuy = Math.min(firstBuy, curPrice);
//            afterFirstSell = Math.max(afterFirstSell, curPrice - firstBuy);
            result[0] = Math.max(result[0], curPrice - firstBuy);
            for (int i = 1; i < result.length - 1; i += 2) {
//                afterSecondBuy = Math.max(afterSecondBuy, afterFirstSell - curPrice);
//                afterSecondSell = Math.max(afterSecondSell, afterSecondBuy + curPrice);
                result[i] = Math.max(result[i], result[i - 1] - curPrice);
                result[i + 1] = Math.max(result[i + 1], result[i] + curPrice);
            }
        }
        return result[result.length - 1];
    }
}
