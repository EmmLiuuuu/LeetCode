package leetcode;

public class LeetCode168 {

    public static void main(String[] args) {
        System.out.println(new LeetCode168().convertToTitle(18776));
    }

    public String convertToTitle(int n) {
        StringBuilder builder = new StringBuilder();
        while (n > 0) {
            int s = (n - 1) % 26;
            n = (n - 1) / 26;
            builder.append((char) (s + 'A'));
        }
        return builder.reverse().toString();
    }
}
