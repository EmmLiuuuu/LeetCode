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
}
