package leetcode;

public class LeetCode345 {

    public String reverseVowels(String s) {
        int i = 0, j = s.length() - 1;
        char[] charArray = s.toCharArray();
        while (i < j) {
            boolean containsI = judge(charArray[i]);
            boolean containsJ = judge(charArray[j]);
            if (containsI && !containsJ) {
                j--;
            } else if (!containsI && containsJ) {
                i++;
            } else if (!containsI) {
                j--;
                i++;
            }
            if (i < j && (containsI && containsJ)) {
                char temp = charArray[i];
                charArray[i] = charArray[j];
                charArray[j] = temp;
                i++;
                j--;
            }
        }
        return String.valueOf(charArray);
    }

    private boolean judge(char c) {
        switch (c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                return true;
            default:
                return false;
        }
    }
}
