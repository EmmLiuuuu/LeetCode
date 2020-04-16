package main

import (
	"fmt"
	"sort"
)

func threeSumSmaller(nums []int, target int) int {
	length := len(nums)
	if length < 3 {
		return 0
	}
	res := 0
	sort.Ints(nums)
	for i := 0; i < length-2; i++ {
		left := i + 1
		right := length - 1
		for left < right {
			num := nums[i] + nums[left] + nums[right]
			if num < target {
				//当判断三个数之和小于目标值时，此时结果应该加上 right-left，因为数组排序了以后，
				//如果加上 num[right] 小于目标值的话，那么加上一个更小的数必定也会小于目标值，然后将左指针右移一位，否则将右指针左移一位
				res += right - left
				left++
			} else {
				right--
			}
		}
	}
	return res
}

func main() {
	fmt.Println(threeSumSmaller([]int{-2, 0, 1, 3}, 2))
}
