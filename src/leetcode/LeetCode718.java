package leetcode;

/**
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 */
public class LeetCode718 {

    //注意是子数组
    public int findLength(int[] A, int[] B) {
        if (A.length == 0 || B.length == 0) {
            return 0;
        }
        int maxLength = 0;
        int[][] dp = new int[A.length][B.length];
        if (A[0] == B[0]) {
            dp[0][0] = 1;
            maxLength = 1;
        }
        for (int i = 0, j = 0; i < A.length; i++) {
            if (A[i] == B[j]) {
                dp[i][j] = 1;
                maxLength = 1;
            } else {
                dp[i][j] = 0;
            }
        }
        for (int j = 0, i = 0; j < B.length; j++) {
            if (A[i] == B[j]) {
                dp[i][j] = 1;
                maxLength = 1;
            } else {
                dp[i][j] = 0;
            }
        }

        for (int i = 1; i < A.length; i++) {
            for (int j = 1; j < B.length; j++) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                if (dp[i][j] > maxLength) {
                    maxLength = dp[i][j];
                }
            }
        }
        return maxLength;
    }

    /**
     * 设 dp[i][j] 为 A[i:] 和 B[j:] 的最长公共前缀，那么答案就为所有 dp[i][j] 中的最大值 max(dp[i][j])。
     * 如果 A[i] == B[j]，那么状态转移方程为 dp[i][j] = dp[i + 1][j + 1] + 1，否则状态转移方程为 dp[i][j] = 0。
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength1(int[] A, int[] B) {
        int ans = 0;
        int[][] memo = new int[A.length + 1][B.length + 1];
        for (int i = A.length - 1; i >= 0; --i) {
            for (int j = B.length - 1; j >= 0; --j) {
                if (A[i] == B[j]) {
                    memo[i][j] = memo[i + 1][j + 1] + 1;
                    if (ans < memo[i][j]) {
                        ans = memo[i][j];
                    }
                }
            }
        }
        return ans;
    }
}
