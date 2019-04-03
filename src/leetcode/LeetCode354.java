package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode354 {

    static class T {
        int height;
        int weight;

        public T(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "[" + weight + ", " + height + "]";
        }
    }

    public static int maxEnvelopes(int[][] array) {
        if (array.length == 1) {
            return 1;
        }

        List<T> list = new ArrayList<>(array.length + 1);
        for (int[] ints : array) {
            list.add(new T(ints[1], ints[0]));
        }

        list.sort((e, v) -> { //先对weight排序（升序），如果weight相同，则对height降序
            if (e.weight == v.weight) {
                return v.height - e.height;
            }
            return e.weight - v.weight;
        });
        return lengthOfLIS(list);
    }

    public static int lengthOfLIS(List<T> nums) {
        if (nums.size() == 0) {
            return 0;
        }
        int[] dp = new int[nums.size()];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums.get(i).height > nums.get(j).height) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
}
