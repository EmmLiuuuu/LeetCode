package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode46 {

    public static void main(String[] args) {
        System.out.println(new LeetCode46().permute(new int[]{5, 4, 6, 2}));
    }

    public List<List<Integer>> permute(int[] nums) {
        // 首先是特判
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();

        if (len == 0) {
            return res;
        }

        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();

        dfs(nums, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int depth,
                     List<Integer> path, boolean[] used,
                     List<List<Integer>> res) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;

                dfs(nums, depth + 1, path, used, res);
                // 注意：这里是状态重置，是从深层结点回到浅层结点的过程，代码在形式上和递归之前是对称的
                used[i] = false;
                path.remove(depth);
            }
        }
    }
}
