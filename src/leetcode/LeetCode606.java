package leetcode;

import util.TreeNode;

public class LeetCode606 {

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.right = new TreeNode(3);
        t.left.right = new TreeNode(4);
        System.out.println(tree2str(t));
    }

    //一直在尝试非递归，想不到比较好的解法
    public static String tree2str(TreeNode t) {
        if(t == null){
            return "";
        }
        if(t.left == null && t.right == null){
            return String.valueOf(t.val);
        }
        String left = "(" + tree2str(t.left)+")";
        String right = tree2str(t.right);
        return t.val + left + ("".equals(right) ? "" : "("+right+")");
    }
}
