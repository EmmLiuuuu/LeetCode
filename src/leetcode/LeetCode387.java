package leetcode;

public class LeetCode387 {

    //暴力
    public int firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = s.lastIndexOf(c);
            int index2 = s.indexOf(c);
            if (index == index2) {
                return index;
            }
        }
        return -1;
    }

    public int firstUniqChar1(String s) {
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (chars[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
