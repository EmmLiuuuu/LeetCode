package main

func levelOrder(root *TreeNode) [][]int {
	res := make([][]int, 0)
	if root == nil {
		return res
	}
	queue := []*TreeNode{root}
	res = append(res, []int{root.Val})

	for len(queue) > 0 {
		var level []int
		levelLength := len(queue)

		for i := 0; i < levelLength; i++ {
			cur := queue[0]
			queue = queue[1:]
			level = append(level, cur.Val)

			if cur.Left != nil {
				queue = append(queue, cur.Left)
			}
			if cur.Right != nil {
				queue = append(queue, cur.Right)
			}
		}
		res = append(res, level)
	}
	return res
}
