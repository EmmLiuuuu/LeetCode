package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode343 {

    private final Map<Integer, Integer> map = new HashMap<>(8);

    //递归，备忘录优化
    public int cuttingRope(int n) {
        //基础情况
        if (n == 2) {
            return 1;
        }
        if (map.containsKey(n)) {
            //如果n已经计算过了，直接跳过
            return map.get(n);
        }
        int max = Integer.MIN_VALUE;
        //从0开始没有意义
        for (int i = 1; i < n; i++) {
            //计算得出分出i之后继续cut、分出i，n-i之后的乘积最大值，并更新max
            max = Math.max(max, Math.max(cuttingRope(n - i) * i, i * (n - i)));
        }
        //记录当前n的最大值
        map.put(n, max);
        return max;
    }

    //自底向上，dp
    public int cuttingRope1(int n) {
        int[] dp = new int[n + 1];
        //base case
        dp[2] = dp[1] = dp[0] = 1;
        for (int i = 3; i < n + 1; i++) {
            for (int j = 1; j < i; j++) {
                //dp[i]表示n为i时切分乘积的最大值，j * (i - j)表示切分两部分的乘积，j * dp[i - j]表示切出j之后继续切分i - j
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        //n为最终答案
        return dp[n];
    }

    public int cuttingRope2(int n) {
        int[] dp = new int[]{0, 1, 1};
        for (int i = 3; i < n + 1; i++) {
            dp[i % 3] = Math.max(
                    Math.max(
                            Math.max(dp[(i - 1) % 3], i - 1),
                            2 * Math.max(dp[(i - 2) % 3], i - 2)
                    ),
                    3 * Math.max(dp[(i - 3) % 3], i - 3)
            );
        }
        return dp[n % 3];
    }

    //任何数都可以被分解成2^a * 3^b，取b尽可能大，得出最大值
    public int integerBreak3(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int a = n / 3, b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        }
        if (b == 1) {
            return (int) Math.pow(3, a - 1) * 4;//3^(a-1)*(3+1)
        }
        return (int) Math.pow(3, a) * 2;//3^a * 2
    }

    //如果需要对结果取模呢？mod = 1000000007
    public int integerBreak_mod(int n) {
        if (n <= 3) {
            return n - 1;
        }
        //还是分解为2^a * 3^b，b取最大
        int mod = 1000000007;
        long res = 1;
        while (n > 4) {
            res *= 3;
            res %= mod;
            n -= 3;
        }
        //此时，n为剩下的2
        return (int) ((res * n) % mod);
    }
}
