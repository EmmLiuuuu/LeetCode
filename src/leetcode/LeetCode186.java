package leetcode;

/**
 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
 * <p>
 * The input string does not contain leading or trailing spaces and the words are always separated by a single space.
 * <p>
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * <p>
 * Could you do it in-place without allocating extra space?
 */
public class LeetCode186 {
    public static void main(String[] args) {
        new LeetCode186().reverseWords(new char[]{'t', 'h', 'e', ' ', 's', 'k', 'y', ' ', 'i', 's', ' ', 'b', 'l', 'u', 'e'});
    }

    public void reverseWords(char[] s) {
        int i = 0, j = s.length - 1;
        reverse(s, i, j);
        int temp = i;
        for (; i < s.length - 1; i++) {
            if (s[i + 1] == ' ') {
                reverse(s, temp, i);
                temp = i + 2;
            }
        }
        reverse(s, temp, i);
        System.out.println(s);
    }

    private void reverse(char[] s, int i, int j) {
        while (i < j) {
            char temp = s[i];
            s[i++] = s[j];
            s[j--] = temp;
        }
    }
}
