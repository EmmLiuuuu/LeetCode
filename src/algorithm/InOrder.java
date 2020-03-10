package algorithm;

import util.TreeNode;

import java.util.LinkedList;

public class InOrder {

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        //思想是先穷举左边所有节点，再开始往右边的左边子节点走
        while (!stack.isEmpty() || p != null) {

            //一直遍历到左子树最下边，边遍历边保存根节点到栈中
            while (p != null) {
                stack.addLast(p);
                p = p.left;
            }

            //当p为空时，说明已经到达左子树最下边，这时需要出栈了
            if (!stack.isEmpty()) {
                p = stack.removeLast();
                System.out.println(p.val);
                //进入右子树，开始新的一轮左子树遍历
                p = p.right;
            }
        }
    }
}
