package leetcode;

public class LeetCode171 {

    public int titleToNumber(String s) {
        int sum = 0;
        if (s.length() > 1) {
            for (int i = 0; i < s.length(); i++) {
                sum += Math.pow(26, s.length() - i - 1) * (s.charAt(i) - 'A' + 1);
            }
            return sum;
        } else {
            return s.charAt(0) - 'A' + 1;
        }
    }
}
