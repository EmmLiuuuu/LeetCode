package main

import "strconv"

func binaryTreePaths(root *TreeNode) []string {
	if root == nil {
		return []string{}
	}
	paths := make([]string, 0)
	pathStack := []string{strconv.Itoa(root.Val)}
	stack := []*TreeNode{root}
	for len(stack) > 0 {
		parent := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		path := pathStack[len(pathStack)-1]
		pathStack = pathStack[:len(pathStack)-1]

		if parent.Left == nil && parent.Right == nil {
			paths = append(paths, path)
		} else {
			if parent.Left != nil {
				stack = append(stack, parent.Left)
				pathStack = append(pathStack, path+"->"+strconv.Itoa(parent.Left.Val))
			}

			if parent.Right != nil {
				stack = append(stack, parent.Right)
				pathStack = append(pathStack, path+"->"+strconv.Itoa(parent.Right.Val))
			}
		}
	}
	return paths
}

func binaryTreePaths1(root *TreeNode) []string {
	if root == nil {
		return []string{}
	}
	var paths []string
	binaryTreePathsHelper(root, "", &paths)
	return paths
}

func binaryTreePathsHelper(root *TreeNode, path string, paths *[]string) {
	path += strconv.Itoa(root.Val)
	if root.Left == nil && root.Right == nil {
		*paths = append(*paths, path)
	} else {
		path += "->"
		if root.Left != nil {
			binaryTreePathsHelper(root.Left, path, paths)
		}
		if root.Right != nil {
			binaryTreePathsHelper(root.Right, path, paths)
		}
	}
}
