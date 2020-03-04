package leetcode;

public class LeetCode28 {

    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        if (haystack == null || haystack.length() < needle.length()) {
            return -1;
        }
        int i = 0;
        int j = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i++) == needle.charAt(j)) {
                j++;
            } else {
                if (j > 0) {
                    i = i - j;
                    j = 0;
                }
            }
            if (j == needle.length()) {
                return i - j;
            }
        }
        return -1;
    }
}
