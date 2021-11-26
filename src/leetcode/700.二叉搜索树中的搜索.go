/*
 * @lc app=leetcode.cn id=700 lang=golang
 *
 * [700] 二叉搜索树中的搜索
 */

package leetcode

// @lc code=start
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

func searchBST(root *TreeNode, val int) *TreeNode {
	node := root
	for node != nil {
		if val > node.Val {
			if node.Right == nil {
				return nil
			} else {
				node = node.Right
			}
		} else if val < node.Val {
			if node.Left == nil {
				return nil
			} else {
				node = node.Left
			}
		} else {
			return node
		}
	}
	return nil
}

// @lc code=end
