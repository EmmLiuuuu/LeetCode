package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class NiuKe1 {

    public static void main(String[] args) {


        System.out.println(countBitDiff(1999, 2299));
        System.out.println(calculateMax(new int[]{3,8,5,1,7,8}));
    }

    public static int countBitDiff(int m, int n) {
        if (m == n) {
            return 0;
        }
        int count = 0;
        for(int i = 0; i < 32; i++) {
            int temp = (1 << i);
            if((m & temp) != (n & (1 << i))) {
                count++;
            }
        }
        return count;
    }

    //风口之下，猪都能飞。当今中国股市牛市，真可谓“错过等七年”。 给你一个回顾历史的机会，已知一支股票连续n天的价格走势，以长度为n的整数数组表示，数组中第i个元素（prices[i]）代表该股票第i天的股价。 假设你一开始没有股票，但有至多两次买入1股而后卖出1股的机会，并且买入前一定要先保证手上没有股票。若两次交易机会都放弃，收益为0。 设计算法，计算你能获得的最大收益。 输入数值范围：2<=n<=100,0<=prices[i]<=100
    public static int calculateMax(int[] prices) {
        int firstBuy = Integer.MAX_VALUE;//第一次买入的价格
        // 接下来都是买入卖出之后的收益
        int afterFirstSell = 0;
        int afterSecondBuy = Integer.MIN_VALUE;
        int afterSecondSell = 0;

        for (int curPrice: prices){
            //第一次买入的价格应该是越小越好，最好是负数，倒贴钱
            firstBuy = Math.min(firstBuy, curPrice);
            //第一次卖出后的收益，等于当前价格减去第一次买入价格，越高越好
            afterFirstSell = Math.max(afterFirstSell, curPrice - firstBuy);
            //第二次买入后的收益，等于第一次卖出后的收益减去当前价格，越高越好
            afterSecondBuy = Math.max(afterSecondBuy, afterFirstSell - curPrice);
            //第二次卖出后的收益，等于第二次买入后的收益加上当前价格，越高越好
            afterSecondSell = Math.max(afterSecondSell, afterSecondBuy + curPrice);
        }
        return afterSecondSell;
    }
}
