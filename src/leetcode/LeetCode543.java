package leetcode;

import util.TreeNode;

public class LeetCode543 {

    int ans = 1;

    public int diameterOfBinaryTree(TreeNode root) {
        len(root);
        return ans - 1;
    }

    public int len(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = len(node.left);
        int right = len(node.right);
        ans = Math.max(left + right + 1, ans);
        return Math.max(left, right) + 1;
    }
}
