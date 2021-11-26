/*
 * @lc app=leetcode.cn id=559 lang=golang
 *
 * [559] N 叉树的最大深度
 */

package leetcode

type Node struct {
	Val      int
	Children []*Node
}

// @lc code=start
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func maxDepth(root *Node) int {
	if root == nil {
		return 0
	}
	var max int = 0
	nodeStack, depthStack := []*Node{root}, []int{1}
	for len(nodeStack) > 0 {
		node := nodeStack[len(nodeStack)-1]
		nodeStack = nodeStack[:len(nodeStack)-1]
		nodeDepth := depthStack[len(depthStack)-1]
		depthStack = depthStack[:len(depthStack)-1]

		if nodeDepth > max {
			max = nodeDepth
		}

		for _, child := range node.Children {
			if child != nil {
				nodeStack = append(nodeStack, child)
				depthStack = append(depthStack, nodeDepth+1)
			}
		}
	}
	return max
}

// @lc code=end
