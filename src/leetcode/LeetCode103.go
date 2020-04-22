package main

func zigzagLevelOrder(root *TreeNode) [][]int {
	var res [][]int
	if root == nil {
		return res
	}
	queue := []*TreeNode{root}
	direction := 1
	for len(queue) > 0 {
		var tmp []int
		var i int
		length := len(queue)
		if direction == -1 {
			i = length - 1
		}

		var newQueue []*TreeNode
		count := 0
		for ; (direction == 1 && i < length) || (direction == -1 && i >= 0); i += direction {
			node := queue[i]
			tmp = append(tmp, node.Val)
			if direction == 1 {
				if node.Left != nil {
					queue = append(queue, node.Left)
				}
				if node.Right != nil {
					queue = append(queue, node.Right)
				}
			} else {
				if node.Right != nil {
					if newQueue == nil {
						newQueue = make([]*TreeNode, len(queue)+1, len(queue)+1)
						copy(newQueue[1:], queue)
					} else {
						tmpQueue := make([]*TreeNode, len(newQueue)+1, len(newQueue)+1)
						copy(tmpQueue[1:], newQueue)
						newQueue = tmpQueue
					}
					newQueue[0] = node.Right
					count++
				}
				if node.Left != nil {
					if newQueue == nil {
						newQueue = make([]*TreeNode, len(queue)+1, len(queue)+1)
						copy(newQueue[1:], queue)
					} else {
						tmpQueue := make([]*TreeNode, len(newQueue)+1, len(newQueue)+1)
						copy(tmpQueue[1:], newQueue)
						newQueue = tmpQueue
					}
					newQueue[0] = node.Left
					count++
				}
			}

		}

		if direction == 1 {
			queue = queue[length:]
		} else {
			queue = newQueue[:count]
		}
		res = append(res, tmp)
		direction = -direction
	}
	return res
}
