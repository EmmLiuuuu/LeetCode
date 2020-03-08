package leetcode;

/**
 * Given two strings s and t, determine if they are both one edit distance apart.
 * <p>
 * Note:
 * <p>
 * There are 3 possiblities to satisify one edit distance apart:
 * <p>
 * Insert a character into s to get t
 * Delete a character from s to get t
 * Replace a character of s to get t
 * Example 1:
 * <p>
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 * Example 2:
 * <p>
 * Input: s = "cab", t = "ad"
 * Output: false
 * Explanation: We cannot get t from s by only one step.
 * Example 3:
 * <p>
 * Input: s = "1203", t = "1213"
 * Output: true
 * Explanation: We can replace '0' with '1' to get t.
 */
public class LeetCode161 {

    public boolean isOneEditDistance(String s, String t) {
        if (s.length() < t.length()) {
            String temp = s;
            s = t;
            t = temp;
        }
        if (s.length() - t.length() > 1) {
            return false;
        } else if (s.length() - t.length() == 1) {
            if (s.charAt(0) == t.charAt(0)) {
                return s.substring(0, s.length() - 1).equals(t);
            } else {
                return s.substring(1).equals(t);
            }
        } else {
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    count++;
                    if (count > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
