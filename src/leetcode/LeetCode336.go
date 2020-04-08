package main

import "fmt"

//暴力
func palindromePairs(words []string) [][]int {
	var res [][]int
	for i := 0; i < len(words); i++ {
		for j := 0; j < len(words); j++ {
			if i == j {
				continue
			}
			str := words[i] + words[j]
			revStr := reverseString(str)
			if str == revStr {
				res = append(res, []int{i, j})
			}
		}
	}
	return res
}

func reverseString(s string) string {
	runes := []rune(s)
	for from, to := 0, len(runes)-1; from < to; from, to = from+1, to-1 {
		runes[from], runes[to] = runes[to], runes[from]
	}
	return string(runes)
}

func main() {
	fmt.Println(palindromePairs([]string{"abcd", "dcba", "lls", "s", "sssll"}))
}
