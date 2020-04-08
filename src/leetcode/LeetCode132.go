package main

import "fmt"

//步骤 1：思考状态
//状态就尝试定义成题目问的那样，看看状态转移方程是否容易得到。
//
//dp[i]：表示前缀子串 s[0:i] 分割成若干个回文子串所需要最小分割次数。
//
//步骤 2：思考状态转移方程
//思考的方向是：大问题的最优解怎么由小问题的最优解得到。
//
//即 dp[i] 如何与 dp[i - 1]、dp[i - 2]、...、dp[0] 建立联系。
//
//比较容易想到的是：如果 s[0:i] 本身就是一个回文串，那么不用分割，即 dp[i] = 0 ，这是首先可以判断的，否则就需要去遍历；
//
//接下来枚举可能分割的位置：即如果 s[0:i] 本身不是一个回文串，就尝试分割，枚举分割的边界 j。
//
//如果 s[j + 1, i] 不是回文串，尝试下一个分割边界。
//
//如果 s[j + 1, i] 是回文串，则 dp[i] 就是在 dp[j] 的基础上多一个分割。
//
//于是枚举 j 所有可能的位置，取所有 dp[j] 中最小的再加 1 ，就是 dp[i]。
//
//得到状态转移方程如下：
//
//dp[i] = min([dp[j] + 1 for j in range(i) if s[j + 1, i] 是回文])
//
//https://leetcode-cn.com/problems/palindrome-partitioning-ii/solution/dong-tai-gui-hua-by-liweiwei1419-2/
func minCut(s string) int {
	sLen := len(s)
	if sLen < 2 {
		return 0
	}
	dp := make([]int, sLen)
	for i := 0; i < sLen; i++ {
		dp[i] = i
	}

	checkPalindrome := make([][]bool, sLen)
	for i := 0; i < sLen; i++ {
		checkPalindrome[i] = make([]bool, sLen)
	}
	for right := 0; right < sLen; right++ {
		// 注意：left <= right 取等号表示 1 个字符的时候也需要判断
		for left := 0; left <= right; left++ {
			if s[left] == s[right] && (right-left <= 2 || checkPalindrome[left+1][right-1]) {
				checkPalindrome[left][right] = true
			}
		}
	}

	for i := 1; i < sLen; i++ {
		if checkPalindrome[0][i] {
			dp[i] = 0
			continue
		}
		for j := 0; j < i; j++ {
			if checkPalindrome[j+1][i] {
				dp[i] = min1(dp[i], dp[j]+1)
			}
		}
	}
	return dp[sLen-1]
}

func min1(i, j int) int {
	if i < j {
		return i
	}
	return j
}

func CheckPalindrome1(start, end int, str string) bool {
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
	fmt.Println(minCut("abc"))
}
