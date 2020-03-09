package leetcode;

import java.util.LinkedList;

public class LeetCode316 {

    public static void main(String[] args) {
        System.out.println(new LeetCode316().removeDuplicateLetters("thesqtitxyetpxloeevdeqifkz"));
    }

    public String removeDuplicateLetters(String s) {
        if (s.length() <= 1) {
            return s;
        }
        LinkedList<Character> stack = new LinkedList<>();
        // 记录每个字符出现的最后一个位置，保证获取所有不重复的字符
        int[] lastAppearIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastAppearIndex[s.charAt(i) - 'a'] = i;
        }

        boolean[] isExist = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (isExist[cur - 'a']) {
                continue;
            }
            while (!stack.isEmpty() && stack.getLast() > cur && lastAppearIndex[stack.getLast() - 'a'] >= i) {
                char top = stack.removeLast();
                isExist[top - 'a'] = false;
            }
            isExist[cur - 'a'] = true;
            stack.addLast(cur);
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.removeFirst());
        }
        return builder.toString();
    }
}
