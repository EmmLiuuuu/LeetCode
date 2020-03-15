package leetcode;

public class LeetCode695 {

    public static void main(String[] args) {
        System.out.println(new LeetCode695().maxAreaOfIsland(new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        }));
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        boolean[][] memo = new boolean[grid.length][grid[0].length];
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!memo[i][j] && grid[i][j] == 1) {
                    memo[i][j] = true;
                    int count = helper(grid, i, j, memo);
                    max = Math.max(count, max);
                }
            }
        }
        return max;
    }

    public int helper(int[][] grid, int i, int j, boolean[][] memo) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 0;
        }
        if (grid[i][j] == 0) {
            return 0;
        }

        memo[i][j] = true;

        int left = 0;
        if (j - 1 >= 0 && !memo[i][j - 1]) {
            left = helper(grid, i, j - 1, memo);
        }
        int right = 0;
        if (j + 1 < grid[0].length && !memo[i][j + 1]) {
            right = helper(grid, i, j + 1, memo);
        }
        int up = 0;
        if (i - 1 >= 0 && !memo[i - 1][j]) {
            up = helper(grid, i - 1, j, memo);
        }
        int down = 0;
        if (i + 1 < grid.length && !memo[i + 1][j]) {
            down = helper(grid, i + 1, j, memo);
        }

        return left + right + up + down + 1;
    }
}
