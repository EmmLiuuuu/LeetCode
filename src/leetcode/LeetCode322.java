package leetcode;

import java.util.Arrays;

public class LeetCode322 {

    int res = Integer.MAX_VALUE;
    int[] memo = null;

    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }

        find(coins, amount, 0);

        if (res == Integer.MAX_VALUE) {
            return -1;
        }

        return res;
    }

    public void find(int[] coins, int amount, int count) {
        if (amount < 0) {
            return;
        }

        if (amount == 0) {
            res = Math.min(res, count);
        }

        for (int coin : coins) {
            find(coins, amount - coin, count + 1);
        }
    }

    public int coinChange1(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }
        memo = new int[amount];

        return find(coins, amount);
    }

    // memo[n] 表示钱币n可以被换取的最少的硬币数，不能换取就为-1
    // findWay函数的目的是为了找到 amount数量的零钱可以兑换的最少硬币数量，返回其值int
    public int find(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }

        if (amount == 0) {
            return 0;
        }

        // 记忆化的处理，memo[n]用赋予了值，就不用继续下面的循环
        // 直接的返回memo[n] 的最优值
        if (memo[amount - 1] != 0) {
            return memo[amount - 1];
        }

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            //找出余额的解决方案
            int res = find(coins, amount - coin);
            if (res >= 0 && res < min) {
                // 加1，是为了加上得到res结果的那个步骤中，兑换的一个硬币
                min = res + 1;
            }
        }

        //记录
        memo[amount - 1] = min == Integer.MAX_VALUE ? -1 : min;
        return memo[amount - 1];
    }

    class Solution {
        public int coinChange(int[] coins, int amount) {
            // 自底向上的动态规划
            if (coins.length == 0) {
                return -1;
            }

            // memo[n]的值： 表示的凑成总金额为n所需的最少的硬币个数
            int[] memo = new int[amount + 1];
            // 给memo赋初值，最多的硬币数就是全部使用面值1的硬币进行换
            // amount + 1 是不可能达到的换取数量，于是使用其进行填充
            Arrays.fill(memo, amount + 1);
            memo[0] = 0;
            for (int i = 1; i <= amount; i++) {
                for (int coin : coins) {
                    if (i - coin >= 0) {
                        // memo[i]有两种实现的方式，
                        // 一种是包含当前的coins[i],那么剩余钱就是 i-coins[i],这种操作要兑换的硬币数是 memo[i-coins[j]] + 1
                        // 另一种就是不包含，要兑换的硬币数是memo[i]
                        memo[i] = Math.min(memo[i], memo[i - coin] + 1);
                    }
                }
            }

            return memo[amount] == (amount + 1) ? -1 : memo[amount];
        }
    }
}
