package main

import "fmt"

func multiply(num1 string, num2 string) string {
	if num1 == "0" || num2 == "0" {
		return "0"
	}
	num1 = reverseString1(num1)
	num2 = reverseString1(num2)
	length := len(num1) + len(num2)
	res := make([]int, length+1)
	for i := 0; i < len(num1); i++ {
		for j := 0; j < len(num2); j++ {
			res[i+j] += int(num1[i]-'0') * int(num2[j]-'0')
		}
	}

	for i := 0; i < length; i++ {
		if res[i] > 9 {
			res[i], res[i+1] = res[i]%10, res[i+1]+res[i]/10
		}
	}

	pos := length
	for res[pos] == 0 && pos >= 0 {
		pos--
	}
	if pos < 0 {
		return "0"
	}
	ans := make([]byte, pos+1)
	index := 0
	for i := pos; i >= 0; i-- {
		ans[index] = byte(res[i]) + '0'
		index++
	}
	return string(ans)
}

func reverseString1(str string) string {
	res := make([]byte, len(str))
	index := len(str) - 1
	for i := 0; i < len(str); i++ {
		res[index] = str[i]
		index--
	}
	return string(res)
}

//考虑进位
func multiply1(num1 string, num2 string) string {
	if num1 == "0" || num2 == "0" {
		return "0"
	}
	x, y := len(num1), len(num2)
	length := x + y
	ans := make([]int, length)
	for i := x - 1; i >= 0; i-- {
		n1 := int(num1[i] - '0')
		for j := y - 1; j >= 0; j-- {
			sum := ans[i+j+1] + n1*int(num2[j]-'0')
			ans[i+j+1] = sum % 10
			ans[i+j] += sum / 10
		}
	}

	res := make([]byte, 0, length)
	for i := 0; i < length; i++ {
		if i == 0 && ans[i] == 0 {
			continue
		}
		res = append(res, byte(ans[i])+'0')
	}
	return string(res)
}

func main() {
	fmt.Println(multiply1("123", "456"))
}
