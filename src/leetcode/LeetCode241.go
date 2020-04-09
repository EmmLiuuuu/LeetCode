package main

import "fmt"

//给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
//
//示例 1:
//
//输入: "2-1-1"
//输出: [0, 2]
//解释:
//((2-1)-1) = 0
//(2-(1-1)) = 2
//示例 2:
//
//输入: "2*3-4*5"
//输出: [-34, -14, -10, -10, 10]
//解释:
//(2*(3-(4*5))) = -34
//((2*3)-(4*5)) = -14
//((2*(3-4))*5) = -10
//(2*((3-4)*5)) = -10
//(((2*3)-4)*5) = 10

var memo = map[string][]int{}

func diffWaysToCompute(input string) []int {
	if value, exist := memo[input]; exist {
		return value
	}
	if len(input) == 0 {
		return []int{}
	}
	res := make([]int, 0)
	i := 0
	num := 0
	for ; i < len(input) && '9' >= input[i] && '0' <= input[i]; i++ {
		num = num*10 + int(input[i]-'0')
	}
	if i == len(input) {
		res = append(res, num)
		return res
	}
	for i := 0; i < len(input); i++ {
		if !('9' >= input[i] && '0' <= input[i]) {
			res1 := diffWaysToCompute(input[:i])
			res2 := diffWaysToCompute(input[i+1:])
			for j := 0; j < len(res1); j++ {
				for k := 0; k < len(res2); k++ {
					switch input[i] {
					case '-':
						res = append(res, res1[j]-res2[k])
					case '+':
						res = append(res, res1[j]+res2[k])
					case '*':
						res = append(res, res1[j]*res2[k])
					}
				}
			}
		}
	}
	memo[input] = res
	return res
}

func main() {
	fmt.Println(diffWaysToCompute("2*3-4*5"))
}
