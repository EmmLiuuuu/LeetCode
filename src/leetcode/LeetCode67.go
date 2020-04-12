package main

import "fmt"

func addBinary(a string, b string) string {
	var res []byte
	lengthA := len(a)
	lengthB := len(b)
	index := 0
	if lengthA > lengthB {
		index = lengthA
		res = make([]byte, lengthA+1)
	} else {
		index = lengthB
		res = make([]byte, lengthB+1)
	}

	for lengthA >= 1 && lengthB >= 1 {
		res[index] = res[index] + a[lengthA-1] + b[lengthB-1] - '0' - '0'
		if res[index] == 2 {
			res[index-1] = 1
			res[index] = 0
		} else if res[index] == 3 {
			res[index] = 1
			res[index-1] = 1
		}
		index--
		lengthA--
		lengthB--
	}
	if lengthA >= 1 {
		for lengthA >= 1 {
			res[index] += a[lengthA-1] - '0'
			if res[index] == 2 {
				res[index-1] = 1
				res[index] = 0
			}
			index--
			lengthA--
		}
	} else if lengthB >= 1 {
		for lengthB >= 1 {
			res[index] += b[lengthB-1] - '0'
			if res[index] == 2 {
				res[index-1] = 1
				res[index] = 0
			}
			index--
			lengthB--
		}
	}
	if res[0] == 0 {
		res = res[1:]
	}
	for i := 0; i < len(res); i++ {
		res[i] += '0'
	}
	return string(res)
}

func main() {
	fmt.Println(addBinary("100", "110010"))
}
