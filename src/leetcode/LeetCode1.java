package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode1 {

    //two sum
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            //先找一下是否有匹配的，找到就返回
            Integer reg = map.get(target - nums[i]);
            if (reg != null) {
                return new int[]{i, reg};
            } else {
                //如果找不到的话，就登记一下
                map.put(nums[i], i);
            }
        }

        return new int[0];
    }
}
