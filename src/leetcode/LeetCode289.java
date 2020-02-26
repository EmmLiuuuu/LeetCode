package leetcode;

public class LeetCode289 {

    //简单直白，完全按照题目解释做了两个循环。第一个循环用于标记，第二个循环用于赋值。
    //1——保持1
    //-1——1转0
    //0——保持0
    //-2——0转1
    class Solution {
        public void gameOfLife(int[][] board) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    //标记所有位置
                    board[i][j] = checkLoc(board, i, j);
                }
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    //做变换
                    board[i][j] = board[i][j] == 1 || board[i][j] == -2 ? 1 : 0;
                }
            }
        }

        public int checkLoc(int[][] board, int i, int j) {
            int count = 0;
            int left = Math.max(j - 1, 0);
            int right = Math.min(j + 1, board[i].length - 1);
            int top = Math.max(i - 1, 0);
            int bottom = Math.min(i + 1, board.length - 1);
            for (int k = top; k <= bottom; k++) {
                for (int l = left; l <= right; l++) {
                    //为什么是-1而不是-2？
                    //因为-2是遍历过一次后才会变成1，此时该位置上不为1
                    count = board[k][l] == 1 || board[k][l] == -1 ? count + 1 : count;
                }
            }
            //对3，4的解释：
            //遍历的时候会把自己也算进去，需要+1
            return board[i][j] == 1 ? (count == 3 || count == 4 ? 1 : -1) : (count == 3 ? -2 : 0);
        }
    }
}
