package main

import (
	"fmt"
)

func minDistance(word1 string, word2 string) int {
	return getDistance(word1, word2)
}

func getDistance(word1, word2 string) int {
	n := len(word1)
	m := len(word2)
	if n*m == 0 {
		return n + m
	}
	var dp [][]int
	for i := 0; i <= n; i++ {
		dp = append(dp, make([]int, m+1))
		dp[i][0] = i
	}
	for j := 0; j <= m && dp != nil; j++ {
		dp[0][j] = j
	}

	for i := 1; i <= n && dp != nil; i++ {
		for j := 1; j <= m; j++ {
			if word1[i-1] == word2[j-1] {
				dp[i][j] = dp[i-1][j-1]
			} else {
				dp[i][j] = min(dp[i-1][j-1], min(dp[i-1][j], dp[i][j-1])) + 1
			}
		}
	}
	if dp != nil {
		return dp[n][m]
	} else {
		return 0
	}
}

func min(i, j int) int {
	if i < j {
		return i
	}
	return j
}

func dp(i, j int, word1, word2 string) int {
	if i == -1 {
		return j + 1
	}
	if j == -1 {
		return i + 1
	}

	if word1[i] == word2[j] {
		return dp(i-1, j-1, word1, word2)
	} else {
		return min(dp(i-1, j, word1, word2)+1, min(dp(i, j-1, word1, word2)+1, dp(i-1, j-1, word1, word2)+1))
	}
}

func main() {
	fmt.Println(minDistance("horse", "ros"))
}
