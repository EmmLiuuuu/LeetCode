package leetcode;

import util.TreeNode;

import java.util.*;

public class LeetCode199 {

    //bfs
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>(0);
        }
        Map<Integer, Integer> map = new HashMap<>(8);
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> depthQueue = new LinkedList<>();
        queue.addLast(root);
        depthQueue.addLast(0);
        int maxDepth = -1;
        while (!queue.isEmpty()) {
            int depth = depthQueue.removeFirst();
            TreeNode parent = queue.removeFirst();
            maxDepth = Math.max(depth, maxDepth);

            //不断更新当前depth的值，直至最后一个，bfs同一层是从左到右遍历的
            map.put(depth, parent.val);

            if (parent.left != null) {
                queue.addLast(parent.left);
                depthQueue.addLast(depth + 1);
            }
            if (parent.right != null) {
                queue.addLast(parent.right);
                depthQueue.addLast(depth + 1);
            }
        }
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i <= maxDepth; i++) {
            if (map.containsKey(i)) {
                list.add(map.get(i));
            }
        }
        return list;
    }

    public List<Integer> rightSideView1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>(0);
        }
        Map<Integer, Integer> map = new HashMap<>(8);
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> depthStack = new LinkedList<>();
        stack.addLast(root);
        depthStack.addLast(0);
        int maxDepth = -1;
        while (!stack.isEmpty()) {
            int depth = depthStack.removeLast();
            TreeNode parent = stack.removeLast();
            maxDepth = Math.max(depth, maxDepth);

            //dfs不断往下走，最后一个肯定是当前分支最右节点或者是最深节点，
            //当depth更新的时候，说明已经到了下一层的右边
            //当右边不存在值时，必定是比右边深的左边
            if (!map.containsKey(depth)) {
                map.put(depth, parent.val);
            }

            if (parent.left != null) {
                stack.addLast(parent.left);
                depthStack.addLast(depth + 1);
            }
            if (parent.right != null) {
                stack.addLast(parent.right);
                depthStack.addLast(depth + 1);
            }
        }
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i <= maxDepth; i++) {
            if (map.containsKey(i)) {
                list.add(map.get(i));
            }
        }
        return list;
    }

    private int len(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = len(root.left);
        int right = len(root.right);
        return Math.max(left, right) + 1;
    }
}
