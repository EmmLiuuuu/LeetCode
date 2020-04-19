package main

func pathSum(root *TreeNode, sum int) [][]int {
	if root == nil {
		return [][]int{}
	}
	var paths [][]int
	stack := []*TreeNode{root}
	countStack := []int{root.Val}
	pathStack := [][]int{{root.Val}}

	for len(stack) > 0 {
		parent := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		count := countStack[len(countStack)-1]
		countStack = countStack[:len(countStack)-1]
		path := pathStack[len(pathStack)-1]
		pathStack = pathStack[:len(pathStack)-1]

		if parent.Left == nil && parent.Right == nil {
			if count == sum {
				paths = append(paths, path)
			}
		} else {
			if parent.Left != nil {
				stack = append(stack, parent.Left)
				countStack = append(countStack, count+parent.Left.Val)
				//只要保证left，right插入的不是同一个切片即可
				tmp := make([]int, len(path), len(path)+1)
				copy(tmp, path)
				tmp = append(tmp, parent.Left.Val)
				pathStack = append(pathStack, tmp)
			}
			if parent.Right != nil {
				stack = append(stack, parent.Right)
				countStack = append(countStack, count+parent.Right.Val)
				path = append(path, parent.Right.Val)
				pathStack = append(pathStack, path)
			}
		}
	}
	return paths
}

func pathSum2(root *TreeNode, sum int) [][]int {
	if root == nil {
		return [][]int{}
	}
	var paths [][]int
	pathSumHelper(root, sum, nil, &paths)
	return paths
}

func pathSumHelper(root *TreeNode, sum int, path []int, paths *[][]int) {
	sum -= root.Val
	path = append(path, root.Val)
	if root.Left == nil && root.Right == nil {
		if 0 == sum {
			tmp := make([]int, len(path), len(path))
			copy(tmp, path)
			*paths = append(*paths, tmp)
		}
	} else {
		if root.Left != nil {
			pathSumHelper(root.Left, sum, path, paths)
		}
		if root.Right != nil {
			pathSumHelper(root.Right, sum, path, paths)
		}
	}
}
