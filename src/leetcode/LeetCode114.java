package leetcode;

public class LeetCode114 {

    //后序遍历将二叉树展成链表
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        //先整理好左边的子树，让其全部偏向右边
        //再处理右边子树
        //最后根结点
        flatten(root.left);
        flatten(root.right);

        if (root.left != null && root.right != null) {
            //当节点上左右子树都存在，先暂存左右子树，然后将左子树放在右边，再把原来的右子树放在原来的左子树的最右边
            TreeNode left = root.left;
            TreeNode right = root.right;

            root.right = left;
            while (left.right != null) {
                left = left.right;
            }
            left.right = right;//放在最右边
        } else if (root.left != null) {//只有左子树的情况，那就把左子树放在右边
            root.right = root.left;
        }
        root.left = null;//将左子树指向null
    }
}
