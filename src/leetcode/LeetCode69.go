package main

func mySqrt(x int) int {
	if x < 2 {
		return x
	}
	left := 2
	right := x / 2
	for left <= right {
		mid := (left + right) >> 1
		num := mid * mid
		if num > x {
			right = mid - 1
		} else if num < x {
			left = mid + 1
		} else {
			return mid
		}
	}
	return right
}

//trick
func mySqrt1(x int) int {
	if x < 2 {
		return x
	}
	left := mySqrt1(x>>2) << 1
	right := left + 1
	if right*right > x {
		return left
	}
	return right
}

func main() {

}
