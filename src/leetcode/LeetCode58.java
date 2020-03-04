package leetcode;

public class LeetCode58 {

    public int lengthOfLastWord(String s) {
        int count = 0;
        int j = s.length() - 1;
        while (j >= 0 && s.charAt(j) == ' ') {
            j--;
        }
        for (int i = j; i >= 0; i--) {
            char c = s.charAt(i);
            if (c != ' ') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
