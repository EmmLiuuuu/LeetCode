package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode532 {

    public static int findPairs(int[] nums, int k) {
//        int pre = Integer.MAX_VALUE;
//        int pre1 = Integer.MAX_VALUE;
//        int count = 0;
//        Arrays.sort(nums);
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (Math.abs(nums[j] - nums[i]) == k && i != j) {
//                    if(!((pre == nums[i] && pre1 == nums[j]) ||
//                            (pre == nums[j] && pre1 == nums[i]))) {
//                        count++;
//                        pre = nums[i];
//                        pre1 = nums[j];
//                    }
//                }
//                if (nums[j] - nums[i] > k) {
//                    break;
//                }
//            }
//        }
//        return count;

        if (k < 0) {
            return 0;
        }
        int size = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer key : map.keySet()) {
            if (k != 0) {
                if (map.containsKey(key + k)) {
                    ++size;
                }
            } else {
                if (map.get(key) > 1) {
                    ++size;
                }
            }
        }
        return size;
    }

    public static void main(String[] args) {
        int[] a = new int[]{6,3,5,7,2,3,3,8,2,4};
        System.out.println(findPairs(a, 2));
    }
}
