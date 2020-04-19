package main

func invertTree(root *TreeNode) *TreeNode {
	if root == nil {
		return root
	}
	tmp := root.Right
	root.Right = invertTree(root.Left)
	root.Left = invertTree(tmp)
	return root
}

func invertTree1(root *TreeNode) *TreeNode {
	if root == nil {
		return root
	}
	queue := []*TreeNode{root}
	for len(queue) > 0 {
		p := queue[0]
		queue = queue[1:]

		p.Left, p.Right = p.Right, p.Left
		if p.Left != nil {
			queue = append(queue, p.Left)
		}
		if p.Right != nil {
			queue = append(queue, p.Right)
		}
	}
	return root
}
