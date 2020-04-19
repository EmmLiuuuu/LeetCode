package main

import "math"

func minDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}
	depthStack := []int{0}
	stack := []*TreeNode{root}
	minDepth := math.MaxInt32

	for len(stack) > 0 {
		parent := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		depth := depthStack[len(depthStack)-1]
		depthStack = depthStack[:len(depthStack)-1]
		if parent.Left == nil && parent.Right == nil {
			if depth < minDepth {
				minDepth = depth
			}
		} else {
			if parent.Left != nil {
				stack = append(stack, parent.Left)
				depthStack = append(depthStack, depth+1)
			}
			if parent.Right != nil {
				stack = append(stack, parent.Right)
				depthStack = append(depthStack, depth+1)
			}
		}
	}
	return minDepth
}

func minDepth1(root *TreeNode) int {
	if root == nil {
		return 0
	}
	return minDepthHelper(root, 0)
}

func minDepthHelper(root *TreeNode, depth int) int {
	depth += 1
	if root.Left == nil && root.Right == nil {
		return depth
	}
	left := math.MaxInt32
	right := math.MaxInt32
	if root.Left != nil {
		left = minDepthHelper(root.Left, depth)
	}
	if root.Right != nil {
		right = minDepthHelper(root.Right, depth)
	}

	if left < right {
		return left
	} else {
		return right
	}
}
