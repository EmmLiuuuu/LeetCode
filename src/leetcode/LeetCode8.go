package main

import (
	"fmt"
	"math"
	"strings"
)

func myAtoi(str string) int {
	str = strings.TrimSpace(str)
	length := len(str)
	if length == 0 {
		return 0
	}
	negative := 1
	if str[0] == '-' {
		negative = -1
		str = str[1:]
		length--
	} else if str[0] == '+' {
		str = str[1:]
		length--
	} else if !(str[0] >= '0' && str[0] <= '9') {
		return 0
	}

	res := 0
	for i := 0; i < length && str[i] >= '0' && str[i] <= '9'; i++ {
		res = res*10 + int(str[i]-'0')
		if res >= math.MaxInt32 && negative == 1 {
			return math.MaxInt32
		} else if res >= math.MaxInt32+1 && negative == -1 {
			return math.MinInt32
		}
	}
	return res * negative
}

func main() {
	fmt.Println(myAtoi("+42w23"))
	fmt.Println(myAtoi("   -42"))
	fmt.Println(myAtoi("words and 987"))
	fmt.Println(myAtoi("-91283472332"))
	fmt.Println(myAtoi("91283472332"))
}
