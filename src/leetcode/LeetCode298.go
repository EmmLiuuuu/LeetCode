package main

func longestConsecutive(root *TreeNode) int {
	res := 0
	longestConsecutiveHelper(root, 0, root.Val, &res)
	return res
}

func longestConsecutiveHelper(root *TreeNode, count, lastValue int, res *int) {
	if root == nil {
		return
	}
	if root.Val == lastValue+1 {
		count++
	} else {
		count = 1
	}
	if count > *res {
		*res = count
	}
	longestConsecutiveHelper(root.Left, count, root.Val, res)
	longestConsecutiveHelper(root.Right, count, root.Val, res)
}
