package main

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

func preorderTraversal(root *TreeNode) []int {
	var res = make([]int, 0)
	if root == nil {
		return res
	}
	helper(root, &res)
	return res
}

func helper(root *TreeNode, res *[]int) {
	if root == nil {
		return
	}
	*res = append(*res, root.Val)
	helper(root.Left, res)
	helper(root.Right, res)
}

func preorderTraversal1(root *TreeNode) []int {
	ans := make([]int, 0)
	if root == nil {
		return ans
	}

	stack := make([]*TreeNode, 0)
	stack = append(stack, root)
	for len(stack) > 0 {
		parent := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		ans = append(ans, parent.Val)

		if parent.Right != nil {
			stack = append(stack, parent.Right)
		}

		if parent.Left != nil {
			stack = append(stack, parent.Left)
		}
	}
	return ans
}
