package main

func rightSideView(root *TreeNode) []int {
	if root == nil {
		return []int{}
	}
	queue := []*TreeNode{root}
	depthQueue := []int{0}
	depthMap := map[int]int{}
	maxDepth := -1

	for len(queue) > 0 {
		depth := depthQueue[0]
		depthQueue = depthQueue[1:]
		node := queue[0]
		queue = queue[1:]
		if depth > maxDepth {
			maxDepth = depth
		}

		depthMap[depth] = node.Val

		if node.Left != nil {
			queue = append(queue, node.Left)
			depthQueue = append(depthQueue, depth+1)
		}
		if node.Right != nil {
			queue = append(queue, node.Right)
			depthQueue = append(depthQueue, depth+1)
		}
	}

	res := make([]int, 0, len(depthMap))
	for i := 0; i <= maxDepth; i++ {
		if value, ok := depthMap[i]; ok {
			res = append(res, value)
		}
	}
	return res
}
