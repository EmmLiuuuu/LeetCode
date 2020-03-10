package algorithm;

import util.TreeNode;

import java.util.LinkedList;

public class PreOrder {

    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);

        while (!stack.isEmpty()) {
            TreeNode parent = stack.removeLast();
            System.out.println(parent.val);

            if (parent.right != null) {
                stack.addLast(parent.right);
            }

            if (parent.left != null) {
                stack.addLast(parent.left);
            }
        }
    }
}
