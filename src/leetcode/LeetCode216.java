package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode216 {

    public List<List<Integer>> combinationSum3(int k, int n) {

        Set<List<Integer>> result = new HashSet<>();
        List<Integer> nums = new ArrayList<>();

        combine(1, 0, n, k, nums, result);

        return new ArrayList<>(result);
    }

    public void combine(int index, int total, int target, int k, List<Integer> nums, Set<List<Integer>> result) {
        if (total > target) {
            return;
        }

        if (k == 0 && total != target) {
            return;
        }

        if (total == target && k == 0) {
            result.add(new ArrayList<>(nums));
            return;
        }

        for (int i = index; i <= 9; i++) {
            nums.add(i);

            combine(i + 1, total + i, target, k - 1, nums, result);

            nums.remove(nums.size() - 1);
        }

    }

}
