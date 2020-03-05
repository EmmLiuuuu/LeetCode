package leetcode;

import java.util.Arrays;

public class LeetCode151 {

    public static void main(String[] args) {
        System.out.println(new LeetCode151().reverseWords("    "));
        System.out.println(Arrays.toString("a good   example".split(" ")));
    }

    public String reverseWords(String s) {
        if (s == null || "".equals(s.trim())) {
            return s == null ? s : s.trim();
        }
        String[] splits = s.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = splits.length - 1; i >= 0; i--) {
            if ("".equals(splits[i])) {
                continue;
            }
            builder.append(splits[i]).append(" ");
        }
        return builder.substring(0, builder.length() - 1);
    }
}
