package algorithm;

public class LCS {

    public int lcs(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        int[][] dp = new int[aLen][bLen];
        int maxLength = 0;

        if (a.charAt(0) == b.charAt(0)) {
            maxLength = 1;
            dp[0][0] = 1;
        }
        for (int i = 1, j = 0; i < aLen; i++) {
            if (a.charAt(i) == b.charAt(j)) {
                maxLength = 1;
                dp[i][j] = 1;
            } else {
                dp[i][j] = dp[i - 1][j];
            }
        }
        for (int j = 1, i = 0; j < bLen; j++) {
            if (b.charAt(j) == a.charAt(i)) {
                maxLength = 1;
                dp[i][j] = 1;
            } else {
                dp[i][j] = dp[i][j - 1];
            }
        }

        for (int i = 1; i < aLen; i++) {
            for (int j = 1; j < bLen; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

                if (dp[i][j] > maxLength) {
                    maxLength = dp[i][j];
                }
            }
        }
        return maxLength;
    }
}
