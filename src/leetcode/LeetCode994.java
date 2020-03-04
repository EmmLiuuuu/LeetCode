package leetcode;

import java.util.LinkedList;

public class LeetCode994 {

    //利用bfs搜索所有的橘子
    public int orangesRotting(int[][] grid) {
        int count = 0;
        LinkedList<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    count++;
                } else if (grid[i][j] == 2) {
                    queue.addLast(new int[]{i, j});
                }
            }
        }

        int round = 0;
        while (count > 0 && !queue.isEmpty()) {
            round++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] rottedOrange = queue.removeFirst();
                if (rottedOrange[0] - 1 >= 0 && grid[rottedOrange[0] - 1][rottedOrange[1]] == 1) {
                    count--;
                    grid[rottedOrange[0] - 1][rottedOrange[1]] = 2;
                    queue.addLast(new int[]{rottedOrange[0] - 1, rottedOrange[1]});
                }
                if (rottedOrange[0] + 1 < grid.length && grid[rottedOrange[0] + 1][rottedOrange[1]] == 1) {
                    count--;
                    grid[rottedOrange[0] + 1][rottedOrange[1]] = 2;
                    queue.addLast(new int[]{rottedOrange[0] + 1, rottedOrange[1]});
                }
                if (rottedOrange[1] - 1 >= 0 && grid[rottedOrange[0]][rottedOrange[1] - 1] == 1) {
                    count--;
                    grid[rottedOrange[0]][rottedOrange[1] - 1] = 2;
                    queue.addLast(new int[]{rottedOrange[0], rottedOrange[1] - 1});
                }
                if (rottedOrange[1] + 1 < grid[rottedOrange[0]].length && grid[rottedOrange[0]][rottedOrange[1] + 1] == 1) {
                    count--;
                    grid[rottedOrange[0]][rottedOrange[1] + 1] = 2;
                    queue.addLast(new int[]{rottedOrange[0], rottedOrange[1] + 1});
                }
            }
        }

        if (count > 0) {
            return -1;
        }
        return round;
    }
}
