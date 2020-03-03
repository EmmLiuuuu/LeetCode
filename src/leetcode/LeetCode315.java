package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode315 {

    public static void main(String[] args) {
        new Solution().countSmaller(new int[]{5, 2, 6, 1});
    }

    //暴力
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length - 1; i++) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }
            result.add(count);
        }
        if (nums.length != 0) {
            result.add(0);
        }
        return result;
    }

    static class Solution {
        public List<Integer> countSmaller(int[] nums) {
            Integer[] ret = new Integer[nums.length];
            Arrays.fill(ret, 0);
            TreeNode root = null;
            for (int i = nums.length - 1; i >= 0; i--) {
                root = insert(root, new TreeNode(nums[i]), ret, i);
            }
            return Arrays.asList(ret);
        }

        public TreeNode insert(TreeNode root, TreeNode node, Integer[] ret, int i) {
            if (root == null) {
                root = node;
                return root;
            }
            if (root.val >= node.val) { // 注意小于等于插入到左子树，防止多加1
                root.count++;
                root.left = insert(root.left, node, ret, i);
            } else {
                ret[i] += root.count + 1;
                root.right = insert(root.right, node, ret, i);
            }
            return root;
        }
    }

    static class TreeNode {
        int val;
        int count;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
            count = 0;
        }
    }
}
