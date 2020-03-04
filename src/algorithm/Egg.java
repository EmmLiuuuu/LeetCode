package algorithm;

public class Egg {

    /*
      你面前有一栋从 1 到 N 共 N 层的楼，然后给你 K 个鸡蛋 ( K 至少为 1)。
      现在确定这栋楼存在楼层 0 <= F <= N ，在这层楼将鸡蛋扔下去，
      鸡蛋恰好没摔碎(高于 F 的楼层都会碎，低于 F 的楼层都不会碎)。
      现在问你，最坏情况下，你至少要扔几次鸡蛋，才能确定这个楼层 F 呢?
     */

    /*
    状态：鸡蛋个数K，楼层数N
    选择：去哪一楼扔鸡蛋
     */


    //返回楼层
    public int egg(int N, int K) {
        int[][] memo = new int[N + 1][K + 1];
        return dp(N, K, memo);
    }

    //动态规划算法的时间复杂度就是子问题个数 × 函数本身的复杂度。
    //函数本身的复杂度就是忽略递归部分的复杂度，这里 dp 函数中有一个 for 循环，所以函数本身的复杂度是 O(N)。
    //子问题个数也就是不同状态组合的总数，显然是两个状态的乘积，也就是 O(KN)。
    //所以算法的总时间复杂度是 O(K*N^2), 空间复杂度 O(KN)。
    public int dp(int N, int K, int[][] memo) {
        //当只有一个鸡蛋的时候，需要线性遍历查找
        if (K == 1) {
            return N;
        }
        //当楼层为0时，不用进行尝试
        if (N == 0) {
            return 0;
        }
        //消除重叠子问题
        if (memo[N][K] != 0) {
            return memo[N][K];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            res = Math.min(
                    res,
                    //因为我们要求的是最坏情况下扔鸡蛋的次数，
                    //所以鸡蛋在第 i 层楼碎没碎，取决于哪种情况的结果更大
                    //最坏情况下的最少扔鸡蛋次数
                    Math.max(
                            //没碎，往上找
                            dp(N - i, K, memo),
                            //碎，往下找
                            dp(i - 1, K - 1, memo)
                    ) + 1//在第i楼丢了一次
            );
        }
        //备忘录，记录数据
        memo[N][K] = res;
        return res;
    }

    public int dp_binary(int N, int K, int[][] memo) {
        //当只有一个鸡蛋的时候，需要线性遍历查找
        if (K == 1) {
            return N;
        }
        //当楼层为0时，不用进行尝试
        if (N == 0) {
            return 0;
        }
        //消除重叠子问题
        if (memo[N][K] != 0) {
            return memo[N][K];
        }
        int res = Integer.MAX_VALUE;
        int low = 1;
        int high = N;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int broken = dp_binary(mid - 1, K - 1, memo);
            int notBroken = dp_binary(N - mid, K, memo);
            //因为我们要求的是最坏情况下扔鸡蛋的次数，
            //所以鸡蛋在第 i 层楼碎没碎，取决于哪种情况的结果更大
            //最坏情况下的最少扔鸡蛋次数
            if (broken > notBroken) {
                //这种情况下1～mid-1最坏情况下尝试次数最多
                high = mid - 1;
                res = Math.min(res, broken + 1);
            } else {
                low = mid + 1;
                res = Math.min(res, notBroken + 1);
            }
        }
        memo[N][K] = res;
        return res;
    }

    int superEggDrop2(int K, int N) {
        // m 最多不会超过 N 次（线性扫描）
        int[][] dp = new int[K + 1][N + 1];
        // base case:
        // dp[0][..] = 0
        // dp[..][0] = 0
        // Java 默认初始化数组都为 0
        int m = 0;
        //dp[k][m] = n
        // 当前有 k 个鸡蛋，可以尝试扔 m 次鸡蛋
        // 这个状态下，最坏情况下最多能确切测试一栋 n 层的楼
        //
        // 比如说 dp[1][7] = 7 表示：
        // 现在有 1 个鸡蛋，允许你扔 7 次;
        // 这个状态下最多给你 7 层楼，
        // 使得你可以确定楼层 F 使得鸡蛋恰好摔不碎
        // （一层一层线性探查嘛）
        // 当覆盖第N层时，得出答案
        while (dp[K][m] < N) {
            m++;
            //1、无论你在哪层楼扔鸡蛋，鸡蛋只可能摔碎或者没摔碎，碎了的话就测楼下，没碎的话就测楼上。
            //2、无论你上楼还是下楼，总的楼层数 = 楼上的楼层数 + 楼下的楼层数 + 1（当前这层楼）。
            for (int k = 1; k <= K; k++) {
                //dp[k][m - 1] 就是楼上的楼层数，因为鸡蛋个数 k 不变，也就是鸡蛋没碎，扔鸡蛋次数 m 减一；
                //
                //dp[k - 1][m - 1] 就是楼下的楼层数，因为鸡蛋个数 k 减一，也就是鸡蛋碎了，同时扔鸡蛋次数 m 减一。
                //
                //PS：这个 m 为什么要减一而不是加一？之前定义得很清楚，这个 m 是一个允许的次数上界，而不是扔了几次。
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
            }
        }
        return m;
    }
}
