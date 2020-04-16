package main

func postorderTraversal(root *TreeNode) []int {
	res := make([]int, 0)
	helperPostOrder(root, &res)
	return res
}

func helperPostOrder(root *TreeNode, res *[]int) {
	if root == nil {
		return
	}
	helperPostOrder(root.Left, res)
	helperPostOrder(root.Right, res)
	*res = append(*res, root.Val)
}

func postorderTraversal1(root *TreeNode) []int {
	if root == nil {
		return []int{}
	}
	stack := make([]*TreeNode, 0)
	stack1 := make([]*TreeNode, 0)
	stack = append(stack, root)
	for len(stack) > 0 {
		parent := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		stack1 = append(stack1, parent)

		if parent.Right != nil {
			stack = append(stack, parent.Right)
		}

		if parent.Left != nil {
			stack = append(stack, parent.Left)
		}
	}
	res := make([]int, 0, len(stack1))
	for i := len(stack1) - 1; i >= 0; i-- {
		res = append(res, stack1[i].Val)
	}
	return res
}

func postorderTraversal2(root *TreeNode) []int {
	if root == nil {
		return []int{}
	}
	stack := []*TreeNode{root}
	var tmp *TreeNode
	p := root

	res := make([]int, 0)
	for len(stack) > 0 {
		length := len(stack)
		tmp = stack[length-1]
		if tmp.Left != nil && tmp.Left != p && tmp.Right != p {
			stack = append(stack, tmp.Left)
		} else if tmp.Right != nil && tmp.Right != p {
			stack = append(stack, tmp.Right)
		} else {
			res = append(res, tmp.Val)
			stack = stack[:length-1]
			p = tmp
		}
	}
	return res
}
