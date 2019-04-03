package leetcode;

import util.TreeNode;

import java.util.*;

public class LeetCode236LCA {
//
//    [3,5,1,6,2,0,8,null,null,7,4]
//            5
//            4

    private static TreeNode ans;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        //计算机学院这个string之前在常量池内未出现过，所以str2.intern会将这个string的引用放入常量池，并返回这个引用，两者引用了同一个对象，返回true
        String str1 = new StringBuilder().append("计算机").append("学院").toString();

        //str2.intern返回的是常量池内java这个string的引用，而str2本身引用的是堆内创建的string对象，两者不是同一个对象，false
        String str2 = new StringBuilder().append("ja").append("va").toString();

        System.out.println(str1.intern() == str1);
        System.out.println(str2.intern() == str2);
        System.out.println();

        System.out.println(lowestCommonAncestor(root, root.left, root.left.right.right));
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        find(root, p, q);
        System.out.println(find1(root, p, q));
        return ans;
    }

    private static boolean find(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }

        //先找左边
        int left = find(root.left, p, q) ? 1 : 0;

        //再找右边
        int right = find(root.right, p, q) ? 1 : 0;

        //p或q为lca的情况
        int mid = (root.val == p.val || root.val == q.val) ? 1 : 0;

        //当找到两个时，得出lca
        if (left + right + mid >= 2) {
            ans = root;
        }

        return (left + right + mid) > 0;
    }

    private static TreeNode find1(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode, TreeNode> parent = new HashMap<>();

        stack.push(root);
        parent.put(root, null);

        while (!parent.containsKey(p) || !parent.containsKey(q)) {

            //遍历直至找到一个p或q
            TreeNode node = stack.pop();
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }

            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }

        }

        //p先回溯，然后用set存p的祖先
        Set<TreeNode> set = new HashSet<>();

        while (p != null) {
            set.add(p);
            p = parent.get(p);
        }

        //然后q回溯，找到第一个p和q共同的祖先
        while (!set.contains(q)) {
            q = parent.get(q);
        }

        return q;
    }
}
