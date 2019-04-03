package leetcode;

import java.util.LinkedList;

public class LeetCode958 {

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
//        System.out.println(isCompleteTree(root));
    }

    public boolean isCompleteTree(TreeNode root) {

        return check(root, height(root));
    }

    //思路是先计算整棵树的高度，然后每一层判断树的结点数是否为2^(h-1)个，如果不是，直接返回false
    //当到倒数第二层时，进行具体判断
    public boolean check(TreeNode root, int he) {
        if (root == null) {
            return false;
        }
        if (he == 1) {
            return true;
        }

        int h = 0;

        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<TreeNode> lastNode = null;

        queue.add(root);

        while (queue.size() > 0) {
            int nodeCount = queue.size();

            if (nodeCount != (1 << h) && (h != he - 1)) {//当不为倒数第二层且该层节点数不为2^(h-1)时，判定失败
                return false;
            }

            if (h == he - 1) {
                break;
            }

            h++;

            if (h == he - 1) {
                lastNode = new LinkedList<>(queue);
            }

            while (nodeCount > 0) {
                TreeNode p = queue.removeFirst();
                nodeCount--;

                if (p.left != null) {
                    queue.add(p.left);
                }

                if (p.right != null) {
                    queue.add(p.right);
                }

            }
        }

        //倒数第二层
        while (queue.size() > 0 && lastNode.size() > 0) {
            //倒数第二层的节点
            TreeNode p = lastNode.removeFirst();

            //倒数第二层的节点左边不可能为null的同时最后一层还有节点存在
            if (p.left == null) {
                return false;
            }

            //如果父亲节点的右子节点不存在，则判定最后一层不应该再存在节点
            if (p.right == null) {
                queue.removeFirst();
                return queue.size() <= 0;
            }

            //如果左右两个子节点都存在，移出队列，再进行判断
            queue.removeFirst();
            queue.removeFirst();
        }

        return true;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}
