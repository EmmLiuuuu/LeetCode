package leetcode;

public class LeetCode72 {

    //https://leetcode-cn.com/problems/edit-distance/solution/zi-di-xiang-shang-he-zi-ding-xiang-xia-by-powcai-3/
    public static int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        if (n * m == 0) {
            return n + m;
        }

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    //如果相等的话，不需要任何操作
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //如果不等，将取增删改操作的最小值+1
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
    }

    public int minDistance1(String word1, String word2) {
        return dp(word1.length() - 1, word2.length() - 1, word1, word2);
    }

    public int dp(int i, int j, String word1, String word2) {
        //如果未指向word的字符，那么相当于是比较的字符串长度
        if (i == -1) {
            return j + 1;
        }

        if (j == -1) {
            return i + 1;
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            //如果两字符相等，啥都不用做
            return dp(i - 1, j - 1, word1, word2);
        } else {
            //如果不相等，尝试进行增删改的操作
            return Math.min(
                    //word2删一个字符
                    dp(i - 1, j, word1, word2) + 1,
                    Math.min(
                            //word2增一个字符
                            dp(i, j - 1, word1, word2) + 1,
                            //word2改一个字符
                            dp(i - 1, j - 1, word1, word2) + 1
                    )
            );
        }
    }
}
