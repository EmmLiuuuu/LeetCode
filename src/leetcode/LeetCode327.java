package leetcode;

import java.util.TreeMap;

public class LeetCode327 {

    public static void main(String[] args) {
        System.out.println(new LeetCode327().countRangeSum(new int[]{-2, 5, -1}, -2, 2));
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            long sum = nums[i];
            if (nums[i] >= lower && nums[i] <= upper) {
                count++;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= lower && sum <= upper) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countRangeSum1(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        TreeMap<Long, Integer> treeMap = new TreeMap<>();
        //当值相等的时候，直接返回1
        treeMap.put(0L, 1);

        int count = 0;
        long sum = 0;

        for (int num : nums) {
            sum += num;
            for (int cnt : treeMap.subMap(sum - upper, true, sum - lower, true).values()) {
                count += cnt;
            }
            treeMap.put(sum, treeMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
