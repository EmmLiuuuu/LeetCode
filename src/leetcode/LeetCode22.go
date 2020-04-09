package main

import (
	"fmt"
)

func generateParenthesis(n int) []string {
	res := make([]string, 0)
	gen := make([]byte, 0, n)
	backTracingParenthesis(&gen, 0, 0, &res, n)
	return res
}

func backTracingParenthesis(gen *[]byte, open, close int, res *[]string, max int) {
	if len(*gen) == 2*max {
		*res = append(*res, string(*gen))
		return
	}

	if open < max {
		*gen = append(*gen, '(')
		backTracingParenthesis(gen, open+1, close, res, max)
		*gen = (*gen)[:len(*gen)-1]
	}

	if close < open {
		*gen = append(*gen, ')')
		backTracingParenthesis(gen, open, close+1, res, max)
		*gen = (*gen)[:len(*gen)-1]
	}
}

func main() {
	fmt.Println(generateParenthesis(3))
}
