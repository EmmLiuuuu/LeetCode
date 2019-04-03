package leetcode;

import java.util.*;

public class LeetCode39 {

    //化解成子问题求解
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        combina(candidates, 0, target, numbers, 0, result);
        return result;
    }

    public void combina(int[] candidates, int total, int target, List<Integer> numbers, int index, List<List<Integer>> result) {
        if (total > target) {
            return ;
        }

        if (total == target) {
            result.add(new ArrayList<>(numbers));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            numbers.add(candidates[i]);
            combina(candidates, total + candidates[i], target, numbers, i, result);
            numbers.remove(numbers.size() - 1);
        }
    }
}
