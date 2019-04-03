package leetcode;

import util.TreeNode;

import java.util.LinkedList;

public class LeetCode222 {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        int count = 0;
        while (queue.size() > 0) {

            int parentSize = queue.size();

            while (parentSize > 0) {
                TreeNode parent = queue.removeFirst();

                if (parent.left != null) {
                    queue.addLast(parent.left);
                }

                if (parent.right != null) {
                    queue.addLast(parent.right);
                }

                parentSize--;
                count++;
            }
        }

        return count;
    }
}
