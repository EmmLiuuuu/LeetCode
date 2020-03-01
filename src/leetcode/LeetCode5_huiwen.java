package leetcode;

public class LeetCode5_huiwen {

    private int index, max;
    public String longestPalindrome(String s) {
        if (s.length() == 1) { //当为1时，直接返回
            return s;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            get(i, i, s);  //xxaxx情况
            get(i, i+1, s);//xxaaxx情况
        }
        return s.substring(index, index + max);
    }

    private void get(int i, int j, String s) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            --i;
            ++j;
        }
        if (max < j - i - 1) {
            max = j - i - 1;//当判断不一样时i，j都已经多走了一步，原本是（j-i+1），两个多走一步后max应该为原来的-2
            index = i + 1;//多走了一步，加回来
        }
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode5_huiwen().longestPalindrome1("cbbdcabiuaiiccccc"));
    }

    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        String max = "";
        boolean[][] dp = new boolean[s.length() + 1][s.length() + 1];
        //dp矩阵，dp[j][i]说明子串[j, i)是回文（左闭右开）
        for (int i = 0; i < dp.length; i++) {
            for (int j = i; j >= 0; j--) {
                //当子串是""（空字符串），或者是长度为1时，为回文串
                if (i == j || j == i - 1) {
                    dp[j][i] = true;
                } else if (s.charAt(i - 1) == s.charAt(j) && dp[j + 1][i - 1]) {
                    //当子串左右两个字符相等，且[j + 1, i - 1) 为回文子串时，那么[j, i)也是回文串
                    dp[j][i] = true;
                }

                //当不为空字符串，且[j,i)为回文串，且长度较长时，更新max
                if (i != j && dp[j][i] && max.length() < i - j) {
                    max = s.substring(j, i);
                }
            }
        }
        return max;
    }
}
