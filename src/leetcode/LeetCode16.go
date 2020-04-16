package main

import "sort"

func threeSumClosest(nums []int, target int) int {
	length := len(nums)
	sort.Ints(nums)
	res := nums[0] + nums[1] + nums[2]
	for i := 0; i < length-2; i++ {
		left := i + 1
		right := length - 1
		for left < right {
			num := nums[i] + nums[left] + nums[right]
			if num > target {
				right--
			} else if num < target {
				left++
			} else {
				return target
			}
			absNum := target - num
			absRes := target - res
			if absNum < 0 {
				absNum = -absNum
			}
			if absRes < 0 {
				absRes = -absRes
			}
			if absNum < absRes {
				res = num
			}
		}
	}
	return res
}

func main() {

}
