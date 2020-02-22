package leetcode;

import java.util.Arrays;

public class LeetCode164 {

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        if (nums.length == 2) {
            return Math.abs(nums[0] - nums[1]);
        }

        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

        //这个时候必定为0
        if (max == min) {
            return 0;
        }

        //不用考虑min与max
        //我们要找到那个保证箱子内部的数字不会产生最大 gap，并且尽量大的 interval
        //如果能保证一定有一个空箱子，那么可以保证箱子中最大的gap为interval - 1
        //如何能保证一定有一个空箱子呢
        //有n-2个数字，如果箱子数大于n-2，那么一定会有一个空箱子
        //总范围是max-min，那么interval = （max - min）/箱子数，箱子数取最大（n-1），即可使interval最大
        //gap的计算为取上一个箱子的最大值-下一个箱子的最小值
        int interval = (int) Math.ceil((double) (max - min) / (nums.length - 1));
        int[] bucketMin = new int[nums.length - 1];
        int[] bucketMax = new int[nums.length - 1];

        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        for (int num : nums) {
            int index = (num - min) / interval;
            if (num == min || num == max) {
                continue;
            }

            //计算bucket的最大值、最小值
            bucketMin[index] = Math.min(num, bucketMin[index]);
            bucketMax[index] = Math.max(num, bucketMax[index]);
        }

        int maxGap = 0;
        int preMax = min;

        for (int i = 0; i < nums.length - 1; i++) {
            if (bucketMax[i] == Integer.MIN_VALUE) {
                continue;
            }

            //计算gap
            maxGap = Math.max(bucketMin[i] - preMax, maxGap);
            //上一个箱子的最大值
            preMax = bucketMax[i];
        }

        //边界情况，最大值可能在边界，不在箱子中（比如，有max的值特别大）
        maxGap = Math.max(maxGap, max - preMax);

        return maxGap;
    }
}
