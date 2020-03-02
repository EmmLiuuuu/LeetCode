package leetcode;

public class LeetCode374 {


    public int guessNumber(int n) {
        int low = 1;
        while (low <= n) {
            int mid = low + (n - low) / 2;
            int guessResult = guess(mid);
            if (guessResult == 0) {
                return mid;
            } else if (guessResult == -1) {
                n = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /*
    -1 : 我的数字比较小
    1 : 我的数字比较大
    0 : 恭喜！你猜对了！
     */
    public int guess(int i) {
        return i;
    }
}
