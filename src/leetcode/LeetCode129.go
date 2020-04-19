package main

import (
	"fmt"
	"strconv"
)

func sumNumbers(root *TreeNode) int {
	if root == nil {
		return 0
	}
	sum := 0
	stack := []*TreeNode{root}
	numStack := []string{strconv.Itoa(root.Val)}
	for len(stack) > 0 {
		parent := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		num := numStack[len(numStack)-1]
		numStack = numStack[:len(numStack)-1]

		if parent.Left == nil && parent.Right == nil {
			realNum, err := strconv.Atoi(num)
			if err != nil {
				panic(fmt.Sprintf("str:%s can not cast to int", num))
			}
			sum += realNum
		} else {
			if parent.Left != nil {
				stack = append(stack, parent.Left)
				numStack = append(numStack, num+strconv.Itoa(parent.Left.Val))
			}
			if parent.Right != nil {
				stack = append(stack, parent.Right)
				numStack = append(numStack, num+strconv.Itoa(parent.Right.Val))
			}
		}
	}
	return sum
}

func sumNumbers1(root *TreeNode) int {
	if root == nil {
		return 0
	}
	return sumNumbersHelper(root, "", 0)
}

func sumNumbersHelper(root *TreeNode, num string, sum int) int {
	num += strconv.Itoa(root.Val)
	if root.Left == nil && root.Right == nil {
		realNum, err := strconv.Atoi(num)
		if err != nil {
			panic(fmt.Sprintf("str:%s can not cast to int", num))
		}
		return sum + realNum
	} else {
		left := 0
		right := 0
		if root.Left != nil {
			left = sumNumbersHelper(root.Left, num, sum)
		}
		if root.Right != nil {
			right = sumNumbersHelper(root.Right, num, sum)
		}
		return sum + left + right
	}
}
