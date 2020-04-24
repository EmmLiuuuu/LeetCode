package main

func lowestCommonAncestor236(root, p, q *TreeNode) *TreeNode {
	stack := []*TreeNode{root}
	parent := map[*TreeNode]*TreeNode{root: nil}

	for !contains(parent, p) || !contains(parent, q) {
		node := stack[len(stack)-1]
		stack = stack[:len(stack)-1]

		if node.Left != nil {
			stack = append(stack, node.Left)
			parent[node.Left] = node
		}

		if node.Right != nil {
			stack = append(stack, node.Right)
			parent[node.Right] = node
		}
	}

	set := map[*TreeNode]byte{}
	for ; p != nil; p, _ = parent[p] {
		set[p] = 0
	}

	for _, ok := set[q]; !ok; _, ok = set[q] {
		q, _ = parent[q]
	}

	return q
}

func contains(parent map[*TreeNode]*TreeNode, node *TreeNode) bool {
	_, ok := parent[node]
	return ok
}
