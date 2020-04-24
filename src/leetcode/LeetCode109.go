package main

func sortedListToBST(head *ListNode) *TreeNode {
	indexMap := map[int]int{}
	p := head
	index := 0
	for p != nil {
		indexMap[index] = p.Val
		p = p.Next
		index++
	}
	return sortedListToBSTHelper(0, index-1, indexMap)
}

func sortedListToBSTHelper(left, right int, indexMap map[int]int) *TreeNode {
	if left > right {
		return nil
	}

	p := (left + right) >> 1

	return &TreeNode{
		indexMap[p],
		sortedListToBSTHelper(left, p-1, indexMap),
		sortedListToBSTHelper(p+1, right, indexMap),
	}
}

var leetcode109Node *ListNode

func sortedListToBST1(head *ListNode) *TreeNode {
	if head == nil {
		return nil
	}
	leetcode109Node = head
	length := 0
	p := head
	for p != nil {
		length++
		p = p.Next
	}
	return sortedListToBSTHelper1(0, length-1)
}

func sortedListToBSTHelper1(left, right int) *TreeNode {
	if left > right {
		return nil
	}
	p := (left + right) >> 1
	leftNode := sortedListToBSTHelper1(left, p-1)
	node := &TreeNode{
		leetcode109Node.Val,
		leftNode,
		nil,
	}
	leetcode109Node = leetcode109Node.Next
	node.Right = sortedListToBSTHelper1(p+1, right)
	return node
}
