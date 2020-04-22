package main

func rob(root *TreeNode) int {
	return robHelper(root, &map[*TreeNode]int{})
}

func robHelper(root *TreeNode, memo *map[*TreeNode]int) int {
	if root == nil {
		return 0
	}

	if value, ok := (*memo)[root]; ok {
		return value
	}

	leftNotTaken := robHelper(root.Left, memo)
	rightNotTaken := robHelper(root.Right, memo)
	notTaken := leftNotTaken + rightNotTaken

	left := 0
	right := 0
	if root.Left != nil {
		left += robHelper(root.Left.Left, memo) + robHelper(root.Left.Right, memo)
	}
	if root.Right != nil {
		right += robHelper(root.Right.Left, memo) + robHelper(root.Right.Right, memo)
	}
	taken := left + right + root.Val

	if notTaken > taken {
		(*memo)[root] = notTaken
		return notTaken
	} else {
		(*memo)[root] = taken
		return taken
	}
}
