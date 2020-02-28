package leetcode;

public class LeetCode70 {

    public static void main(String[] args) {
        System.out.println(new LeetCode70().climbStairs2(5));
    }

    //斐波那契数列
    public int climbStairs(int n) {
        if (n < 2) {
            return n;
        }

        int prev = 1;
        int prev2 = 2;

        for (int i = 2; i <= n; i++) {
            int cur = prev + prev2;
            prev = prev2;
            prev2 = cur;
        }
        return prev;
    }

    public int climbStairs1(int n) {
        if (n <= 2) {
            return n;
        }

        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    public int climbStairs2(int n) {
        return fib1(n)[1];
    }

    //计算公式： F2i = Fi * (2 * Fi+1 - Fi)
    //          F2i+1 = Fi * Fi + Fi+1 * Fi+1
    int[] fib1(int n) {
        if (n == 0) return new int[]{0, 1};
        int[] p = fib1(n >> 1);
        int c = p[0] * (2 * p[1] - p[0]);
        int d = p[0] * p[0] + p[1] * p[1];
        if ((n & 1) == 1)
            return new int[]{d, c + d};
        else
            return new int[]{c, d};
    }
}
