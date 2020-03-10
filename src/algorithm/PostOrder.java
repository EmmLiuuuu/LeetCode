package algorithm;

import util.TreeNode;

import java.util.LinkedList;

public class PostOrder {

    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<TreeNode> stack1 = new LinkedList<>();

        stack.addLast(root);
        while (!stack.isEmpty()) {
            //取出来的时候先取右边的
            TreeNode parent = stack.removeLast();
            stack1.addLast(parent);
            //保证stack1取出来的元素顺序为左右中
            if (parent.left != null) {
                stack.addLast(parent.left);
            }
            if (parent.right != null) {
                stack.addLast(parent.right);
            }
        }

        //保证出来的时候必定是左边的先出来
        while (!stack1.isEmpty()) {
            System.out.println(stack1.removeLast().val);
        }
    }

    public void postOrder1(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        TreeNode tmp;
        //p保存已遍历过的节点
        TreeNode p = root;
        while (!stack.isEmpty()) {
            //获取栈顶元素
            tmp = stack.getLast();
            //必须判断left/right不为p（已遍历过的节点），如果不加tmp.right != p会导致已遍历过的左子节点重新加入栈
            if (tmp.left != null && tmp.left != p && tmp.right != p) {
                stack.addLast(tmp.left);
            } else if (tmp.right != null && tmp.right != p) {
                //此时不需要判断左边节点不为p的情况，因为此时左边节点一定是遍历过了的
                stack.addLast(tmp.right);
            } else {
                System.out.println(stack.removeLast().val);
                p = tmp;
            }
        }
    }
}
