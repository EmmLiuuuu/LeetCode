package main

import "fmt"

func shortestPalindrome(s string) string {
	n := len(s)
	var revBytes []byte
	for i := n - 1; i >= 0; i-- {
		revBytes = append(revBytes, s[i])
	}
	rev := string(revBytes)
	for i := 0; i < n; i++ {
		if s[:n-i] == rev[i:] {
			return rev[:i] + s
		}
	}
	return ""
}

func main() {
	fmt.Println(shortestPalindrome("abcd"))
}
