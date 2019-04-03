package leetcode;

import java.util.*;

public class LeetCode40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Set<List<Integer>> result = new HashSet<>(candidates.length);
        List<Integer> numbers = new ArrayList<>();

        Arrays.sort(candidates);//先排序
        combina(numbers, candidates, 0, target, 0, result);//进行大问题拆分成子问题

        return new ArrayList<>(result);
    }

    public void combina(List<Integer> numbers, int[] candidates, int total, int target, int index, Set<List<Integer>> result) {
        if (total > target || index > candidates.length) {
            return;
        }

        if (total == target) {
            result.add(new ArrayList<>(numbers));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            numbers.add(candidates[i]);

            //按照题目说法，不允许重复下标
            combina(numbers, candidates, total + candidates[i], target, i + 1, result);

            numbers.remove(numbers.size() - 1);
        }
    }
}
