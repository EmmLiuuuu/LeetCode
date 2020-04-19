package main

func isSameTree(p *TreeNode, q *TreeNode) bool {
	if p == nil && q == nil {
		return true
	}
	var stack, stack1 []*TreeNode
	if p != nil && q != nil && p.Val == q.Val {
		stack = append(stack, p)
		stack1 = append(stack1, q)
	} else {
		return false
	}

	for len(stack) > 0 && len(stack1) > 0 {
		parent := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		parent1 := stack1[len(stack1)-1]
		stack1 = stack1[:len(stack1)-1]

		if equals(parent.Left, parent1.Left) {
			if parent.Left != nil {
				stack = append(stack, parent.Left)
				stack1 = append(stack1, parent1.Left)
			}
		} else {
			return false
		}

		if equals(parent.Right, parent1.Right) {
			if parent.Right != nil {
				stack = append(stack, parent.Right)
				stack1 = append(stack1, parent1.Right)
			}
		} else {
			return false
		}
	}

	return true
}

func equals(p *TreeNode, q *TreeNode) bool {
	if p == nil && q == nil {
		return true
	} else if p == nil || q == nil {
		return false
	} else {
		return p.Val == q.Val
	}
}
