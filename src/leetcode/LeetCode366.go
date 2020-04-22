package main

func findLeaves(root *TreeNode) [][]int {
	var res [][]int
	if root == nil {
		return res
	}
	findLeavesHelper(root, &res)
	return res
}

func findLeavesHelper(root *TreeNode, res *[][]int) int {
	if root == nil {
		return 0
	}
	height := 0
	left := findLeavesHelper(root.Left, res)
	right := findLeavesHelper(root.Right, res)
	if left < right {
		height = right + 1
	} else {
		height = left + 1
	}
	if len(*res) <= height {
		*res = append(*res, []int{root.Val})
	} else {
		(*res)[height] = append((*res)[height], root.Val)
	}
	return height
}
