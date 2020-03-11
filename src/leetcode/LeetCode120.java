package leetcode;

import java.util.List;

public class LeetCode120 {

    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][];
        for (int i = 1; i <= triangle.size(); i++) {
            dp[i - 1] = new int[i];
        }
        dp[0][0] = triangle.get(0).get(0);
        dp[1][0] = dp[0][0] + triangle.get(1).get(0);
        dp[1][1] = dp[0][0] + triangle.get(1).get(1);

        for (int i = 2; i < triangle.size(); i++) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + list.get(j);
                } else if (j == list.size() - 1) {
                    dp[i][j] = dp[i - 1][j - 1] + list.get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + list.get(j);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < dp[dp.length - 1].length; i++) {
            if (min > dp[dp.length - 1][i]) {
                min = dp[dp.length - 1][i];
            }
        }
        return min;
    }
}
