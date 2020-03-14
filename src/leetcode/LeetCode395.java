package leetcode;

/**
 * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * s = "aaabb", k = 3
 * <p>
 * 输出:
 * 3
 * <p>
 * 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2:
 * <p>
 * 输入:
 * s = "ababbc", k = 2
 * <p>
 * 输出:
 * 5
 * <p>
 * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 */
public class LeetCode395 {

    public static void main(String[] args) {
        System.out.println(new LeetCode395().longestSubstring("weitong", 2));
    }

    public int longestSubstring(String s, int k) {
        if (s == null || s.length() < k) {
            return 0;
        }
        return helper(s, 0, s.length() - 1, k);
    }

    public int helper(String s, int i, int j, int k) {
        if (j - i + 1 < k || i < 0 || i >= s.length() || j < 0 || j >= s.length()) {
            return 0;
        }
        int[] count = new int[26];
        for (int m = i; m <= j; m++) {
            ++count[s.charAt(m) - 'a'];
        }

        while (j - i + 1 >= k && count[s.charAt(i) - 'a'] < k) {
            i++;
        }

        while (j - i + 1 >= k && count[s.charAt(j) - 'a'] < k) {
            j--;
        }

        if (j - i + 1 < k) {
            return 0;
        }

        for (int m = i; m <= j; m++) {
            if (count[s.charAt(m) - 'a'] < k) {
                return Math.max(helper(s, i, m - 1, k), helper(s, m + 1, j, k));
            }
        }
        return j - i + 1;
    }
}
