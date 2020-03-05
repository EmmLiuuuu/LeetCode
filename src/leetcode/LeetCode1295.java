package leetcode;

public class LeetCode1295 {

    public int findNumbers(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            if (((int) Math.log10(num) + 1) % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }
}
