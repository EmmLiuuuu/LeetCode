package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        // 首先是特判
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();

        if (len == 0) {
            return res;
        }

        //先进行排序，方便查找重复元素
        Arrays.sort(nums);

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
                //如果当前节点与前一个节点相同，且前一个节点刚被重置状态，说明以上一个节点为根的树已经遍历过，无需重复遍历
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    //剪枝
                    continue;
                }

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
