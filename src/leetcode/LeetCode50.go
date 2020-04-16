package main

import "fmt"

//快速幂
func myPow(x float64, n int) float64 {
	var result float64 = 1
	if n < 0 {
		x = 1.0 / x
		n = -n
	}
	for n > 0 {
		if n%2 == 0 {
			x *= x
			n /= 2
		} else {
			result *= x
			n -= 1
			x *= x
			n /= 2
		}
	}
	return result
}

func main() {
	fmt.Println(myPow(2.0, 10))
}
