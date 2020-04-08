package main

import (
	"fmt"
	"math"
)

func numSteps(s string) int {
	if len(s) == 0 {
		return 0
	}
	count := 0
	sBytes := []byte(s)
	for len(sBytes) > 1 {
		index := len(sBytes) - 1
		if sBytes[index] == '1' {
			sBytes[index] = '0'
			for i := index - 1; i >= 0; i-- {
				if sBytes[i] == '1' {
					sBytes[i] = '0'
				} else {
					sBytes[i] = '1'
					break
				}
			}
			if sBytes[0] == '0' {
				temp := make([]byte, len(sBytes)+1)
				copy(temp[1:], sBytes)
				temp[0] = '1'
				sBytes = temp
			}
			count++
		} else {
			sBytes = sBytes[:len(sBytes)-1]
			count++
		}
	}
	return count
}

func toInt(s string) int {
	res := 0
	sLen := len(s) - 1
	sByte := []byte(s)
	for _, value := range sByte {
		res += int(value-'0') * int(math.Pow(2, float64(sLen)))
		sLen--
	}
	return res
}

func main() {
	fmt.Println(toInt("1101"))
	fmt.Println(numSteps("1101"))
}
