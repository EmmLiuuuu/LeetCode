package main

func inorderTraversal(root *TreeNode) []int {
	res := make([]int, 0)
	helperInorder(root, &res)
	return res
}

func helperInorder(root *TreeNode, res *[]int) {
	if root == nil {
		return
	}
	helperInorder(root.Left, res)
	*res = append(*res, root.Val)
	helperInorder(root.Right, res)
}

func inorderTraversal1(root *TreeNode) []int {
	res := make([]int, 0)
	if root == nil {
		return res
	}
	stack := make([]*TreeNode, 0)
	p := root
	for len(stack) > 0 || p != nil {

		for p != nil {
			stack = append(stack, p)
			p = p.Left
		}

		stackLength := len(stack)
		if stackLength > 0 {
			p = stack[stackLength-1]
			stack = stack[:stackLength-1]
			res = append(res, p.Val)
			p = p.Right
		}
	}
	return res
}
