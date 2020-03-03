package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
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

    public int maxEnvelopes1(int[][] envelopes) {
        if (envelopes.length <= 1) {
            return envelopes.length;
        }
        //先对w进行排序，然后再对h进行排序，然后求h的递增子序列
        Arrays.sort(envelopes, (c, v) -> {
            if (c[0] == v[0]) {
                return c[1] - v[1];
            }
            return c[0] - v[0];
        });
        int max = Integer.MIN_VALUE;
        int[] dp = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            dp[i] = 1;//千万别忘了这里，因为1个数的时候也是递增子序列
            for (int j = i; j >= 0; j--) {
                //题目要求是w和h都大于前一个的时候才能套娃
                if (envelopes[j][1] < envelopes[i][1] && envelopes[i][0] > envelopes[j][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                max = Math.max(dp[i], max);
            }
        }
        return max;
    }
}
