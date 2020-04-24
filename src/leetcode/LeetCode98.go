package main

func isValidBST(root *TreeNode) bool {
	leftMax := -1<<31 - 1
	rightMin := 1 << 31
	return isValidBSTHelper(root, leftMax, rightMin)
}

func isValidBSTHelper(root *TreeNode, lower, upper int) bool {
	if root == nil {
		return true
	}
	if root.Val <= lower || root.Val >= upper {
		return false
	}
	if !isValidBSTHelper(root.Right, root.Val, upper) {
		return false
	}
	return isValidBSTHelper(root.Left, lower, root.Val)
}
