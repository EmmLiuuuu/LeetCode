package main

func maxDepth(root *TreeNode) int {
	return maxDepthHelper(root, 0)
}

func maxDepthHelper(root *TreeNode, depth int) int {
	if root == nil {
		return depth
	}
	left := maxDepthHelper(root.Left, depth+1)
	right := maxDepthHelper(root.Right, depth+1)

	if left > right {
		return left
	}
	return right
}
