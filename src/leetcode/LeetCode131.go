package main

import "fmt"

//树的深度优先遍历，遇到不为回文串的子树就进行剪枝，不再继续往下遍历
//https://leetcode-cn.com/problems/palindrome-partitioning/solution/hui-su-you-hua-jia-liao-dong-tai-gui-hua-by-liweiw/
func partition(s string) [][]string {
	sLen := len(s)
	if sLen == 0 {
		return [][]string{}
	}
	res := make([][]string, 0)
	path := make([]string, 0)
	backTracing(s, 0, sLen, &path, &res)
	return res
}

func backTracing(s string, start, sLen int, path *[]string, res *[][]string) {
	if start == sLen {
		//走到底时，将当前路径存下来
		tmp := make([]string, len(*path))
		copy(tmp, *path)
		*res = append(*res, tmp)
		return
	}
	for i := start; i < sLen; i++ {
		//如果子串不是回文串，剪枝
		if !CheckPalindrome(start, i, s) {
			continue
		}
		//addLast
		*path = append(*path, s[start:i+1])
		backTracing(s, i+1, sLen, path, res)
		//removeLast
		*path = (*path)[:len(*path)-1]
	}
}

func CheckPalindrome(start, end int, str string) bool {
	for start < end {
		if str[start] != str[end] {
			return false
		}
		start++
		end--
	}
	return true
}

func main() {
	fmt.Println(partition("aab"))
}
