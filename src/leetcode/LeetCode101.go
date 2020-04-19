package main

func isSymmetric(root *TreeNode) bool {
	if root == nil {
		return true
	}
	return symmetricHelper(root.Left, root.Right)
}

func symmetricHelper(left *TreeNode, right *TreeNode) bool {
	if left == nil && right == nil {
		return true
	}
	if left == nil || right == nil {
		return false
	}
	return left.Val == right.Val && symmetricHelper(left.Left, right.Right) && symmetricHelper(left.Right, right.Left)
}

func symmetricCheck(root *TreeNode) bool {
	var queue = []*TreeNode{root.Left, root.Right}
	for len(queue) > 0 {
		p := queue[0]
		q := queue[1]
		queue = queue[2:]

		if p == nil && q == nil {
			continue
		} else if p == nil || q == nil {
			return false
		} else if p.Val != q.Val {
			return false
		}

		queue = append(queue, p.Left)
		queue = append(queue, q.Right)
		queue = append(queue, p.Right)
		queue = append(queue, q.Left)
	}
	return true
}
