package leetcode;

public class LeetCode14 {

    public static void main(String[] args) {
        new LeetCode14().longestCommonPrefix(new String[]{"aca", "cba"});
    }

    public String longestCommonPrefix(String[] strs) {
        int length = Integer.MAX_VALUE;
        for (String str : strs) {
            length = Math.min(str.length(), length);
        }
        if (length == 0 || length == Integer.MAX_VALUE) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = '0';
            for (String str : strs) {
                if (c == '0') {
                    c = str.charAt(i);
                    continue;
                }
                if (str.charAt(i) != c) {
                    return builder.toString();
                }
            }
            builder.append(c);
        }
        return builder.toString();
    }
}
