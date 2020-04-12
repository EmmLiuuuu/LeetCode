package main

import (
	"fmt"
	"math"
)

func reverse(x int) int {
	negative := 1
	if x < 0 {
		negative = -1
		x *= -1
	}
	res := 0
	num := make([]int, 0, 32)
	for x > 0 {
		num = append(num, x%10)
		x /= 10
	}
	for i := 0; i < len(num); i++ {
		res = res*10 + num[i]
		if res > math.MaxInt32 {
			return 0
		}
	}
	return res * negative
}

func main() {
	fmt.Println(reverse(123))
}
