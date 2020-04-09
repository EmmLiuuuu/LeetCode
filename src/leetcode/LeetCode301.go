package main

import "math"

//BFS 无剪枝，把所有括号操作（删除）的情况都列一遍
func removeInvalidParentheses(s string) []string {
	level := map[string]byte{s: 0}
	for len(level) > 0 {
		var res []string
		for key := range level {
			if isValidParentheses(key) {
				res = append(res, key)
			}
		}
		if len(res) > 0 {
			//保证不再往下走，只要找到答案就返回
			return res
		}

		nextLevel := map[string]byte{}
		for key := range level {
			for j := 0; j < len(key); j++ {
				if key[j] == '(' || key[j] == ')' {
					nextLevel[key[:j]+key[j+1:]] = 0
				}
			}
		}
		level = nextLevel
	}
	return []string{""}
}

func isValidParentheses(s string) bool {
	count := 0
	for _, value := range s {
		if value == '(' {
			count++
		} else if value == ')' {
			count--
		}
		if count < 0 {
			return false
		}
	}
	return count == 0
}

//BFS 剪枝
func removeInvalidParentheses1(s string) []string {
	level := map[string]byte{s: 0}
	for len(level) > 0 {
		var res []string
		min := math.MaxInt32
		minStr := map[string]byte{}
		for key := range level {
			//计算不符合的字符个数
			count := isValidParenthesesAndCount(key)
			if count == 0 {
				res = append(res, key)
			} else if count < min {
				//只存不符合的字符个数最少的字符串
				minStr = map[string]byte{key: 0}
				min = count
			} else if count == min {
				minStr[key] = 0
			}
		}
		if len(res) > 0 {
			return res
		}

		nextLevel := map[string]byte{}
		//对下一层进行遍历（删除其中一个括号）
		for key := range minStr {
			for j := 0; j < len(key); j++ {
				if key[j] == '(' || key[j] == ')' {
					nextLevel[key[:j]+key[j+1:]] = 0
				}
			}
		}
		level = nextLevel
	}
	return []string{""}
}

func isValidParenthesesAndCount(s string) int {
	count := 0
	unValid := 0
	for i := 0; i < len(s); i++ {
		if s[i] == '(' {
			count++
		} else if s[i] == ')' {
			count--
		}
		if count < 0 {
			unValid += 1
			count = 0
		}
	}
	return count + unValid
}

func main() {

}
