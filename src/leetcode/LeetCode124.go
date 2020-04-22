package main

func maxPathSum(root *TreeNode) int {
	var max = -1 << 32
	maxPathSumHelper(root, &max)
	return max
}

func maxPathSumHelper(root *TreeNode, max *int) int {
	if root == nil {
		return 0
	}
	leftMax := max1(maxPathSumHelper(root.Left, max), 0)
	rightMax := max1(maxPathSumHelper(root.Right, max), 0)

	sum := root.Val + leftMax + rightMax
	*max = max1(sum, *max)

	return root.Val + max1(leftMax, rightMax)
}

func max1(i, j int) int {
	if i < j {
		return j
	}
	return i
}
