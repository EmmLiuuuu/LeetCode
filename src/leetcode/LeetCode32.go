package main

import "fmt"

//stack
func longestValidParentheses(s string) int {
	res := 0
	var stack = []int{-1}
	for index, value := range s {
		if value == '(' {
			stack = append(stack, index)
		} else {
			stack = stack[:len(stack)-1]
			if len(stack) == 0 {
				stack = append(stack, index)
			} else {
				if res < index-stack[len(stack)-1] {
					res = index - stack[len(stack)-1]
				}
			}
		}
	}
	return res
}

//dp
//dp[i]表示以下标i的s字符结尾的最长有效子字符串长度
func longestValidParentheses1(s string) int {
	res := 0
	dp := make([]int, len(s))
	for i := 1; i < len(s); i++ {
		if s[i] == ')' {
			//....()的情况
			if s[i-1] == '(' {
				if i-2 < 0 {
					dp[i] = 2
				} else {
					//加上之前的长度
					dp[i] = dp[i-2] + 2
				}
			} else
			//...))的情况
			if i-dp[i-1]-1 >= 0 && s[i-dp[i-1]-1] == '(' {
				if i-dp[i-1]-2 >= 0 {
					//               k      i-1  i
					//dp[i-1]的情况为 (   (...)   )的内层有效子字符串情况，dp[i]只包括k到i的情况
					//dp[i-dp[i-1]-2]为 ()((()))  计算最左侧的()的情况
					dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2
				} else {
					dp[i] = dp[i-1] + 2
				}
			}
		}
		if res < dp[i] {
			res = dp[i]
		}
	}
	return res
}

//O(1)空间
func longestValidParentheses2(s string) int {
	left, right, res := 0, 0, 0
	for i := 0; i < len(s); i++ {
		if s[i] == '(' {
			left++
		} else {
			right++
		}
		if left == right {
			if res < 2*right {
				res = 2 * right
			}
		} else if right > left {
			left, right = 0, 0
		}
	}

	left, right = 0, 0
	for i := len(s) - 1; i >= 0; i-- {
		if s[i] == ')' {
			right++
		} else {
			left++
		}

		if left == right {
			if res < 2*left {
				res = 2 * left
			}
		} else if left > right {
			left, right = 0, 0
		}
	}
	return res
}

func main() {
	fmt.Println(longestValidParentheses2("())"))
}
