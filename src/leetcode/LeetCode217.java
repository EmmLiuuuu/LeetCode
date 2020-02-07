package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode217 {

    //官方题解都没有高大上的解法。。
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }

            set.add(num);
        }

        return false;
    }
}
