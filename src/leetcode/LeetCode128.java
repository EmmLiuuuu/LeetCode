package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode128 {

    public static void main(String[] args) {
        new LeetCode128().longestConsecutive(new int[]{0, -1});
    }

    public int longestConsecutive(int[] nums) {
        int result = 0;
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return nums == null ? 0 : nums.length;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            min = Math.min(num, min);
            max = Math.max(num, max);
            set.add(num);
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(min + i)) {
                count += 1;
            } else {
                result = Math.max(result, count);
                count = 0;
            }
        }
        result = Math.max(result, count);

        count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(max - i)) {
                count += 1;
            } else {
                result = Math.max(result, count);
                count = 0;
            }
        }
        result = Math.max(result, count);

        return result;
    }
}
