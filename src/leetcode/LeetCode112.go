package main

func hasPathSum(root *TreeNode, sum int) bool {
	if root == nil {
		return false
	}
	stack := []*TreeNode{root}
	countStack := []int{root.Val}
	for len(stack) > 0 {
		parent := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		count := countStack[len(countStack)-1]
		countStack = countStack[:len(countStack)-1]

		if parent.Left == nil && parent.Right == nil {
			if count == sum {
				return true
			}
		} else {
			if parent.Left != nil {
				stack = append(stack, parent.Left)
				countStack = append(countStack, count+parent.Left.Val)
			}
			if parent.Right != nil {
				stack = append(stack, parent.Right)
				countStack = append(countStack, count+parent.Right.Val)
			}
		}
	}
	return false
}

func hasPathSum1(root *TreeNode, sum int) bool {
	if root == nil {
		return false
	}
	return hasPathSumHelper(root, sum, 0)
}

func hasPathSumHelper(root *TreeNode, sum, count int) bool {
	count += root.Val
	flag := false
	if root.Left == nil && root.Right == nil {
		return count == sum
	} else {
		if root.Left != nil {
			flag = flag || hasPathSumHelper(root.Left, sum, count)
		}
		if root.Right != nil {
			flag = flag || hasPathSumHelper(root.Right, sum, count)
		}
		return flag
	}
}

func hasPathSum2(root *TreeNode, sum int) bool {
	if root == nil {
		return false
	}
	sum -= root.Val
	if root.Left == nil && root.Right == nil {
		return sum == 0
	}
	return hasPathSum2(root.Left, sum) || hasPathSum2(root.Right, sum)
}
