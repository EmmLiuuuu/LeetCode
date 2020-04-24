package main

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
type BSTIterator struct {
	index, length int
	content       []int
	stack         []*TreeNode //可控遍历，无需事先存储
}

func Constructor(root *TreeNode) BSTIterator {
	if root == nil {
		return BSTIterator{}
	}
	iterator := BSTIterator{}
	var stack []*TreeNode
	p := root
	for len(stack) > 0 || p != nil {
		for p != nil {
			stack = append(stack, p)
			p = p.Left
		}

		stackLength := len(stack)
		if stackLength > 0 {
			p = stack[stackLength-1]
			stack = stack[:stackLength-1]
			iterator.content = append(iterator.content, p.Val)
			iterator.length++
			p = p.Right
		}
	}
	return iterator
}

/** @return the next smallest number */
func (this *BSTIterator) Next() int {
	value := this.content[this.index]
	this.index++
	return value
}

/** @return whether we have a next smallest number */
func (this *BSTIterator) HasNext() bool {
	return this.index < this.length
}

func Constructor1(root *TreeNode) BSTIterator {
	iterator := BSTIterator{}
	iterator.push(root)
	return iterator
}

func (this *BSTIterator) push(root *TreeNode) {
	for root != nil {
		this.stack = append(this.stack, root)
		root = root.Left
	}
}

func (this *BSTIterator) Next1() int {
	value := this.stack[len(this.stack)-1]
	this.stack = this.stack[:len(this.stack)-1]
	this.push(value.Right)
	return value.Val
}

func (this *BSTIterator) HasNext1() bool {
	return len(this.stack) > 0
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * obj := Constructor(root);
 * param_1 := obj.Next();
 * param_2 := obj.HasNext();
 */
