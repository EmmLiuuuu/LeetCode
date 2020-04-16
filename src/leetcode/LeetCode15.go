package main

import "sort"

func threeSum(nums []int) [][]int {
	length := len(nums)
	if length < 3 {
		return [][]int{}
	}
	sort.Ints(nums)
	if nums[0] > 0 || nums[length-1] < 0 {
		return [][]int{}
	}
	res := make([][]int, 0)
	for i := 0; i < length; i++ {
		if nums[i] > 0 {
			return res
		}
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}
		left := i + 1
		right := length - 1
		for left < right {
			sum := nums[i] + nums[left] + nums[right]
			if sum > 0 {
				right--
			} else if sum < 0 {
				left++
			} else {
				ans := []int{nums[i], nums[left], nums[right]}
				res = append(res, ans)
				for left < right && nums[left] == nums[left+1] {
					left++
				}
				for left < right && nums[right] == nums[right-1] {
					right--
				}
				left++
				right--
			}
		}
	}
	return res
}

func main() {

}
