package main

import "fmt"

func plusOne(digits []int) []int {
	index := len(digits) + 1
	res := make([]int, index)

	index--
	res[index] = 1
	for i := len(digits) - 1; i >= 0; i-- {
		res[index] = res[index] + digits[i]
		if res[index] >= 10 {
			res[index-1] = 1
			res[index] -= 10
		}
		index--
	}
	if res[0] == 0 {
		return res[1:]
	}
	return res
}

func main() {
	fmt.Println(plusOne([]int{9, 9, 9, 9, 9, 9}))
}
