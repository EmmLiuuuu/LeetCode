package leetcode;

import java.util.HashMap;
import java.util.TreeSet;

//https://leetcode-cn.com/problems/contains-duplicate-iii/solution/cun-zai-zhong-fu-yuan-su-iii-by-leetcode/
public class LeetCode220 {

    public static void main(String[] args) {

    }

    //超时
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length - 1; i++) {

            for (int j = i + 1; j <= i + k && j < nums.length; j++) {

                if (Math.abs(((long) nums[i]) - ((long) nums[j])) <= (long) t) {
                    return true;
                }
            }
        }

        return false;
    }

    //桶排序思想，将元素分范围存放，只要检查区间中是否存在该元素，且相减结果绝对值符合题目要求即可
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        HashMap<Long, Long> map = new HashMap<>(k);

        for (int i = 0; i < nums.length; i++) {
            long tap = ((long) nums[i]) / ((long) t + 1);
            if (map.containsKey(tap)) {
                return true;
            } else if (map.containsKey(tap + 1) && Math.abs(map.get(tap + 1) - (long) nums[i]) < t) {
                return true;
            } else if (map.containsKey(tap - 1) && Math.abs(map.get(tap - 1) - (long) nums[i]) < t) {
                return true;
            } else {
                map.put(tap, (long) nums[i]);

                if (map.size() > k) {
                    map.remove(((long) nums[i - k]) / ((long) t + 1));
                }
            }
        }
        return false;
    }

    //利用平衡二叉树，维护滑动窗口的有序，取出滑动窗口中nums[i]的前后数字
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            // Find the successor of current element
            Integer s = set.ceiling(nums[i]);
            if (s != null && s <= nums[i] + t) return true;

            // Find the predecessor of current element
            Integer g = set.floor(nums[i]);
            if (g != null && nums[i] <= g + t) return true;

            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}