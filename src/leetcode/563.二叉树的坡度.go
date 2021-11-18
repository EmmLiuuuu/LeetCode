/*
 * @lc app=leetcode.cn id=563 lang=golang
 *
 * [563] 二叉树的坡度
 */

package leetcode

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// @lc code=start
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

/*
	坡度的定义是左边sum - 右边sum
*/

var ans563 = 0

func findTilt(root *TreeNode) int {
	// 记得初始化，因为ans已经成为了全局变量，输入的时候只会输入函数，不会整体输入
	ans563 = 0
	sumTilt563(root)
	return ans563
}

func sumTilt563(root *TreeNode) int {
	if root == nil {
		return 0
	}
	sumLeft := sumTilt563(root.Left)
	sumRight := sumTilt563(root.Right)
	ans563 += abs563(sumLeft - sumRight)
	return sumLeft + sumRight + root.Val
}

func abs563(val int) int {
	if val < 0 {
		return 0 - val
	}
	return val
}

// @lc code=end
