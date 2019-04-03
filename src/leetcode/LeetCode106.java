package leetcode;

import java.text.SimpleDateFormat;

public class LeetCode106 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        return build(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);

    }

    public TreeNode build(int[] inorder, int[] postorder, int li, int lj, int ri, int rj) {
        if (li > lj || ri > rj) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[rj]);

        for (int i = li; i <= lj; i++) {
            if (inorder[i] == postorder[rj]) {
                root.left = build(inorder, postorder, li, i - 1, ri, ri + ((i - 1) - li));
                root.right = build(inorder, postorder, i + 1, lj, ri + ((i - 1) - li) + 1, rj - 1);
            }
        }

        return root;
    }

}
