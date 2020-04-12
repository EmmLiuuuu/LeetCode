package main

import "fmt"

func addDigits(num int) int {
	for num >= 10 {
		tmp := 0
		for num > 0 {
			tmp += num % 10
			num /= 10
		}
		if tmp < 10 {
			return tmp
		}
		num = tmp
	}
	return num
}

//surprise
func addDigits1(num int) int {
	return (num-1)%9 + 1
}

func main() {
	fmt.Println(addDigits1(38))
}
