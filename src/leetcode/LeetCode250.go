package main

//Given a binary tree, count the number of uni-value subtrees.
//
//A Uni-value subtree means all nodes of the subtree have the same value.

func countUnivalSubtrees(root *TreeNode) int {
	if root == nil {
		return 0
	}

}

func countUnivalSubtreesHelper(root *TreeNode, count *int, pre int) bool {
	if root == nil {
		return true
	}
	left := countUnivalSubtreesHelper(root.Left, count, root.Val)
	right := countUnivalSubtreesHelper(root.Right, count, root.Val)
	if left && right {
		*count++
	}
	return left && right && pre == root.Val
}
