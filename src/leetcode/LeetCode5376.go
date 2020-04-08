package main

import (
	"fmt"
	"sort"
)

func minSubsequence(nums []int) []int {
	if len(nums) <= 1 {
		return nums
	}
	sort.Ints(nums)
	var res []int
	sum := 0
	for _, data := range nums {
		sum += data
	}

	max := -1
	minLength := len(nums) + 1
	count := 0
	i, j := 0, 0
	for i < len(nums) && j <= len(nums) {
		if count <= sum && j < len(nums) {
			count = count + nums[j]
			sum -= nums[j]
			j++
		} else {
			if count <= sum {
				break
			}
			var temp []int
			for k := i; k < j; k++ {
				temp = append(temp, nums[k])
			}
			if minLength > j-i || (minLength == j-i && max < count) {
				minLength = j - i
				max = count
				res = temp
			}
			count -= nums[i]
			sum += nums[i]
			i++
		}
	}

	sort.Sort(sort.Reverse(sort.IntSlice(res)))
	return res
}

func main() {
	fmt.Println(minSubsequence([]int{1, 8, 1, 5, 10, 10, 10, 7}))
}
