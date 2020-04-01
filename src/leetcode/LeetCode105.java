package leetcode;

import util.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode105 {

    private int preIndex = 0;

    public static void main(String[] args) {
        TreeNode root = new LeetCode105().buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println("111");
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(preorder, 0, inorder.length, map);
    }

    private TreeNode helper(int[] preorder, int inLeft, int inRight, Map<Integer, Integer> map) {
        if (inLeft == inRight || preIndex >= preorder.length) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preIndex]);

        // root splits inorder list
        // into left and right subtrees
        int index = map.get(root.val);

        // recursion，前序遍历数组中已经遍历过的是已经生成节点了的
        preIndex++;
        // build left subtree，生成左子树
        root.left = helper(preorder, inLeft, index, map);
        // build right subtree，生成右子树
        root.right = helper(preorder, index + 1, inRight, map);
        return root;
    }
}
