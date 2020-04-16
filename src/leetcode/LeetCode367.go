package main

import "fmt"

func isPerfectSquare(num int) bool {
	if num <= 1 {
		return true
	}
	left := 1
	right := num / 2
	for left <= right {
		mid := (left + right) >> 1
		tmp := mid * mid
		if tmp > num {
			right = mid - 1
		} else if tmp < num {
			left = mid + 1
		} else {
			return true
		}
	}
	return false
}

func main() {
	fmt.Println(isPerfectSquare(16))
}
