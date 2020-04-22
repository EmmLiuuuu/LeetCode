package main

func isBalanced(root *TreeNode) bool {
	if root == nil {
		return true
	}
	value := maxDepth(root.Left) - maxDepth(root.Right)
	return (value == -1 || value == 1 || value == 0) && isBalanced(root.Left) && isBalanced(root.Right)
}

func isBalanced1(root *TreeNode) bool {
	return isBalancedHelper(root) != -1
}

func isBalancedHelper(root *TreeNode) int {
	if root == nil {
		return 0
	}
	left := isBalancedHelper(root.Left)
	if left == -1 {
		return -1
	}
	right := isBalancedHelper(root.Right)
	if right == -1 {
		return -1
	}
	if abs(left-right) < 2 {
		if left > right {
			return left + 1
		}
		return right + 1
	}
	return -1
}

func abs(i int) int {
	if i < 0 {
		return -i
	}
	return i
}
