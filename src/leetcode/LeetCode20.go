package main

import "fmt"

func isValid(s string) bool {
	sLen := 0
	var stack []byte
	for i := 0; i < len(s); i++ {
		switch s[i] {
		case '(', '{', '[':
			sLen++
			stack = append(stack, s[i])
		case ')', '}', ']':
			if stack == nil || sLen == 0 {
				return false
			}
			switch stack[sLen-1] {
			case '(':
				if s[i] != ')' {
					return false
				}
			case '{':
				if s[i] != '}' {
					return false
				}
			case '[':
				if s[i] != ']' {
					return false
				}
			}
			stack = stack[:sLen-1]
			sLen--
		}
	}
	return len(stack) == 0
}

func main() {
	fmt.Println(isValid("(]"))
}
