package main

func pathSum4(root *TreeNode, sum int) int {
	if root == nil {
		return 0
	}
	return countPath(root, sum) + pathSum4(root.Left, sum) + pathSum4(root.Right, sum)
}

func countPath(root *TreeNode, sum int) int {
	if root == nil {
		return 0
	}
	sum -= root.Val
	left := countPath(root.Left, sum)
	right := countPath(root.Right, sum)
	if sum == 0 {
		return 1 + left + right
	} else {
		return left + right
	}
}

//把每个遍历到的节点当作终点（路径必须包含终点），记录根到终点的路径，从路径往前搜索
//这里需要注意的是：递归栈会保存每次递归的变量，所以可以用一个vector来保存路径，每次递归的过程都取相应的路径，因此递归返回后，要进行弹出。
func countPath1(root *TreeNode, sum int, path *[]int) int {
	if root == nil {
		return 0
	}
	*path = append(*path, root.Val)
	count := 0
	res := 0
	for i := len(*path) - 1; i >= 0; i-- {
		count += (*path)[i]
		if count == sum {
			res++
		}
	}
	res += countPath1(root.Left, sum, path) + countPath1(root.Right, sum, path)
	*path = (*path)[:len(*path)-1]
	return res
}
