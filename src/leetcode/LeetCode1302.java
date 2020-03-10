package leetcode;

import util.TreeNode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CyclicBarrier;

public class LeetCode1302 {

    int leftSum = 0;
    int rightSum = 0;

    public int deepestLeavesSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //先计算左右子树的深度，然后取最大值
        int leftLen = len(root.left);
        int rightLen = len(root.right);
        int maxLen = Math.max(leftLen, rightLen);
        //分别遍历，找到最深的叶子节点
        sum(root.left, maxLen, true);
        sum(root.right, maxLen, false);
        return leftSum + rightSum;
    }

    public int deepestLeavesSumAsync(TreeNode root) {
        if (root == null) {
            return 0;
        }
        final CyclicBarrier barrier = new CyclicBarrier(3);
        final int[] leftLen = {0};
        CompletableFuture.runAsync(() -> {
            leftLen[0] = len(root.left);
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        final int[] rightLen = {0};
        CompletableFuture.runAsync(() -> {
            rightLen[0] = len(root.right);
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        int maxLen = Math.max(leftLen[0], rightLen[0]);
        CompletableFuture.runAsync(() -> {
            sum(root.left, maxLen, true);
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture.runAsync(() -> {
            sum(root.right, maxLen, false);
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        return leftSum + rightSum;
    }

    private void sum(TreeNode root, int depth, boolean left) {
        if (root == null) {
            return;
        }
        sum(root.left, depth - 1, left);
        sum(root.right, depth - 1, left);
        //找到了最深的叶子节点，进行累加
        if (root.left == null && root.right == null && depth == 1) {
            if (left) {
                leftSum += root.val;
            } else {
                rightSum += root.val;
            }
        }
    }

    public int len(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = len(node.left);
        int right = len(node.right);
        return Math.max(left, right) + 1;
    }
}
