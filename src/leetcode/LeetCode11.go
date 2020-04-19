package main

import "math"

func maxArea(height []int) int {
	max := math.MinInt32
	left := 0
	right := len(height) - 1
	for left < right {
		length := 0
		if height[left] < height[right] {
			length = height[left]
		} else {
			length = height[right]
		}
		if length*(right-left) > max {
			max = length * (right - left)
		}
		if height[left] < height[right] {
			left++
		} else {
			right--
		}
	}
	return max
}
