package leetcode;

public class LeetCode6 {

    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows <= 1) {
            return s;
        }
        StringBuilder[] builders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            builders[i] = new StringBuilder();
        }
        int index = 0;
        int div = 1;
        for (int i = 0; i < s.length(); i++) {
            builders[index].append(s.charAt(i));
            index += div;
            if (index == numRows - 1 || index == 0) {
                div = -div;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (StringBuilder stringBuilder : builders) {
            builder.append(stringBuilder.toString());
        }
        return builder.toString();
    }
}
