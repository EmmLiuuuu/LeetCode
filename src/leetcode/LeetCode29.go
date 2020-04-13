package main

import (
	"fmt"
	"math"
)

//brute force
func divide(dividend int, divisor int) int {
	if dividend == 0 {
		return 0
	}
	if divisor == 1 {
		return dividend
	}
	negative := 1
	if dividend < 0 && divisor < 0 {
		negative = 1
		dividend *= -1
		divisor *= -1
	} else if divisor < 0 {
		negative = -1
		divisor *= -1
	} else if dividend < 0 {
		negative = -1
		dividend *= -1
	}
	res := 0
	for dividend >= divisor {
		dividend -= divisor
		res++
	}
	if res*negative > math.MaxInt32 && negative == 1 {
		return math.MaxInt32
	} else if res*negative > math.MaxInt32+1 && negative == -1 {
		return math.MinInt32
	}
	return res * negative
}

func divide1(dividend int, divisor int) int {
	if dividend == 0 {
		return 0
	}
	if divisor == 1 {
		return dividend
	}
	negative := 1
	if dividend < 0 && divisor < 0 {
		negative = 1
		dividend = -dividend
		divisor = -divisor
	} else if divisor < 0 {
		negative = -1
		divisor = -divisor
	} else if dividend < 0 {
		negative = -1
		dividend = -dividend
	}
	res := div(dividend, divisor)
	if res > math.MaxInt32 && negative == 1 {
		return math.MaxInt32
	} else if res > math.MaxInt32+1 && negative == -1 {
		return math.MinInt32
	}
	if negative == 1 {
		return res
	}
	return -res
}

//https://leetcode-cn.com/problems/divide-two-integers/solution/po-su-de-xiang-fa-mei-you-wei-yun-suan-mei-you-yi-/
func div(i, j int) int {
	if i < j {
		return 0
	}
	count := 1
	b := j
	for b+b <= i {
		count += count
		b += b
	}
	return count + div(i-b, j)
}

func main() {
	fmt.Println(divide1(-9, 3))
}
