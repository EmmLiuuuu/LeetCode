package leetcode;

public class LeetCode669 {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        if (root.val < L) {//如果值小于L，那么整理右边
            return trimBST(root.right, L, R);
        }

        if (root.val > R) {//如果值大于R，那么整理左边
            return trimBST(root.left, L, R);
        }

        root.left = trimBST(root.left, L, R);//整理左边
        root.right = trimBST(root.right, L, R);//整理右边

        return root;

    }
}
