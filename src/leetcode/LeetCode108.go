package main

func sortedArrayToBST(nums []int) *TreeNode {
	return sortedArrayToBSTHelper(0, len(nums)-1, nums)
}

func sortedArrayToBSTHelper(left, right int, nums []int) *TreeNode {
	if left > right {
		return nil
	}

	p := (left + right) >> 1
	root := TreeNode{nums[p],
		sortedArrayToBSTHelper(left, p-1, nums),
		sortedArrayToBSTHelper(p+1, right, nums)}
	return &root
}
