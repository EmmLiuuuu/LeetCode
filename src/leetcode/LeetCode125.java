package leetcode;

public class LeetCode125 {
    public static void main(String[] args) {
        System.out.println(new LeetCode125().isPalindrome("OP"));
    }

    public boolean isPalindrome(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            if (('a' <= sChar && 'z' >= sChar) || ('A' <= sChar && 'Z' >= sChar) || ('0' <= sChar && '9' >= sChar)) {
                builder.append(Character.toLowerCase(sChar));
            }
        }
        return builder.toString().equals(builder.reverse().toString());
    }
}
