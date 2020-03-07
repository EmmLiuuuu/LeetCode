package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode87 {

    private final Map<String, Boolean> map = new HashMap<>(8);
    private final String FORMAT = "%s-%s";

    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }

        Boolean result = map.get(String.format(FORMAT, s1, s2));
        if (result != null) {
            return result;
        }
        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) {
                map.put(String.format(FORMAT, s1, s2), false);
                return false;
            }
        }
        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                map.put(String.format(FORMAT, s1, s2), true);
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                map.put(String.format(FORMAT, s1, s2), true);
                return true;
            }
        }
        map.put(String.format(FORMAT, s1, s2), false);
        return false;
    }

    //https://leetcode-cn.com/problems/scramble-string/solution/miao-dong-de-qu-jian-xing-dpsi-lu-by-sha-yu-la-jia/
    class Solution {
        public boolean isScramble(String s1, String s2) {
            char[] chs1 = s1.toCharArray();
            char[] chs2 = s2.toCharArray();
            int n = s1.length();
            int m = s2.length();
            if (n != m) {
                return false;
            }
            boolean[][][] dp = new boolean[n][n][n + 1];
            //初始化单个字符的情况
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j][1] = chs1[i] == chs2[j];
                }
            }

            //枚举区间长度2～n
            for (int len = 2; len <= n; len++) {
                //枚举S中的起点位置
                for (int i = 0; i <= n - len; i++) {

                    //枚举T中的起点位置
                    for (int j = 0; j <= n - len; j++) {

                        //枚举划分位置
                        for (int k = 1; k <= len - 1; k++) {

                            //第一种情况：S1->T1,S2->T2
                            if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                                dp[i][j][len] = true;
                                break;
                            }
                            //第二种情况：S1->T2,S2->T1
                            //S1起点i，T2起点j + 前面那段长度len-k，S2起点i+前面长度k
                            if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                                dp[i][j][len] = true;
                                break;
                            }
                        }
                    }
                }
            }
            return dp[0][0][n];
        }
    }
}
