package main

func lowestCommonAncestor(root, p, q *TreeNode) *TreeNode {
	for root != nil {
		if p.Val > root.Val && q.Val > root.Val {
			root = root.Right
		} else if q.Val < root.Val && p.Val < root.Val {
			root = root.Left
		} else {
			return root
		}
	}
	return root
}
