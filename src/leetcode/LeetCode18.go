package main

import (
	"fmt"
	"sort"
)

func fourSum(nums []int, target int) [][]int {
	length := len(nums)
	if length < 4 {
		return [][]int{}
	}
	sort.Ints(nums)

	res := make([][]int, 0)
	for i := 0; i < length; i++ {
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}
		if i > length-4 {
			return res
		}

		for j := i + 1; j < length; j++ {
			if j > i+1 && nums[j] == nums[j-1] {
				continue
			}
			if j > length-2 {
				break
			}
			left := j + 1
			right := length - 1
			for left < right {
				num := nums[j] + nums[left] + nums[right]
				if num > target-nums[i] {
					right--
				} else if num < target-nums[i] {
					left++
				} else {
					res = append(res, []int{nums[i], nums[j], nums[left], nums[right]})
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
	}
	return res
}

func main() {
	fmt.Println(fourSum([]int{1, -2, -5, -4, -3, 3, 3, 5}, -11))
}
