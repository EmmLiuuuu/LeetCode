package leetcode;

import java.util.Stack;

public class LeetCode402 {

    public static void main(String[] args) {
        System.out.println(removeKdigits("112", 1));
    }

    public static String removeKdigits(String num, int k) {
        if (num.length() == k) {
            return "0";
        }

        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < num.length()) {

            while (k > 0 && !stack.empty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i++));
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder builder = new StringBuilder();

        while (!stack.empty()) {
            builder.append(stack.pop());
        }

        builder.reverse();

        while (builder.length() > 1 && builder.charAt(0) == '0') {
            builder.deleteCharAt(0);
        }

        return builder.toString();
    }
}
