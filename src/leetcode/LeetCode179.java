package leetcode;

import java.util.Arrays;

public class LeetCode179 {

    public static void main(String[] args) {
        System.out.println("3".compareTo("30"));
    }

    public String largestNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strings, (s, t) -> (t + s).compareTo(s + t));

        if ("0".equals(strings[0])) {
            return "0";
        }

        StringBuilder builder = new StringBuilder();
        for (String str : strings) {
            builder.append(str);
        }
        return builder.toString();
    }
}
