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
        if (node == null) {
            return 0;
        }

        //计算往左边走的最大值
        int leftMax = Math.max(maxPath(node.left), 0);

        //计算往右边走的最大值
        int rightMax = Math.max(maxPath(node.right), 0);

        //计算这个根节点的最大值
        int total = leftMax + rightMax + node.val;

        //更新最大值
        max = Math.max(max, total);

        /*
        往较大值的子树方向走
        上面 max_sum = Math.max(max_sum, price_newpath) 已经计算了当前节点作为路径中的一个联接节点的情况，
        return的必须是当前节往左子树或者右子树其中之一的一条路径，这样递归返回给上一层时，上一层的节点才能用的了这一条路径，
        如果return的是当前结点为联接结点的路径，递归返回到父结点，是用不了这条路径的
         */
        return node.val + Math.max(rightMax, leftMax);
    }

}
