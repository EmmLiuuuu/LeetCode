package main

func canJump(nums []int) bool {
	length := len(nums)
	indexFlag := make([]int, length, length)
	indexFlag[length-1] = 1
	return canJump1(nums, indexFlag, 0)
}

func canJump1(nums, indexFlags []int, position int) bool {
	if indexFlags[position] != 0 {
		return indexFlags[position] == 1
	}

	for i := min4(position+nums[position], len(nums)-1); i > position; i-- {
		if canJump1(nums, indexFlags, i) {
			indexFlags[position] = 1
			return true
		}
	}

	indexFlags[position] = -1
	return false
}

func min4(i, j int) int {
	if i < j {
		return i
	}
	return j
}

func canJump2(nums []int) bool {
	length := len(nums)
	index := 0
	for i := 0; i < length; i++ {
		if i > index {
			return false
		}
		if i+nums[i] > index {
			index = i + nums[i]
		}
		if index >= length-1 {
			return true
		}
	}
	return index >= length-1
}

func canJump3(nums []int) bool {
	length := len(nums)
	index := length - 1
	for i := index; i >= 0; i-- {
		if nums[i]+i >= index {
			index = i
		}
	}
	return index == 0
}
