package leetcode;
import leetcode.LeetCode106.TreeNode;

public class LeetCode235LCA_SORTED {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

//        if (p.val > root.val && q.val > root.val) {
//            return lowestCommonAncestor(root.right, p, q);
//        } else if (p.val < root.val && q.val < root.val) {
//            return lowestCommonAncestor(root.left, p, q);
//        } else {
//            return root;
//        }//递归

        while (root != null) {
            if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
    }
}
