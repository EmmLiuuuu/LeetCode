package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        for (int i = 0; i < nums.length - 1; i++) {

            for (int j = i + 1; j <= i + k && j < nums.length; j++) {

                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }

        return false;
    }


    //利用set维护滑动窗口
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        Set<Integer> windows = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (windows.contains(nums[i])) {
                return true;
            }

            windows.add(nums[i]);

            if (windows.size() > k) {
                windows.remove(nums[i - k]);
            }
        }

        return false;
    }
}
