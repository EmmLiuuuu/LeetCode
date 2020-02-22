package leetcode;

import util.TreeNode;

//找出左右的最大值
public class LeetCode124 {

    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPath(root);
        return max;
    }

    public int maxPath(TreeNode node) {
        if (node == null) return 0;
        int leftMax = Math.max(maxPath(node.left), 0);
        int rightMax = Math.max(maxPath(node.right), 0);

        int total = leftMax + rightMax + node.val;

        max = Math.max(max, total);

        return node.val + Math.max(rightMax, leftMax);
    }

}
