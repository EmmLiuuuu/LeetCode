package main

import "fmt"

func superEggDrop(K int, N int) int {
	memo := make([][]int, N+1)
	for i := 0; i <= N; i++ {
		memo[i] = make([]int, K+1)
	}
	return dp1(K, N, memo)
}

func supperEggDrop1(K int, N int) int {
	dp := make([][]int, N+1)
	for i := 0; i <= N; i++ {
		dp[i] = make([]int, K+1)
	}

	m := 0
	for dp[m][K] < N {
		m++
		for i := 1; i <= K; i++ {
			dp[m][i] = dp[m-1][i] + dp[m-1][i-1] + 1
		}
	}
	return m
}

func dp1(K, N int, memo [][]int) int {
	if K == 1 {
		return N
	}

	if N == 0 {
		return 0
	}

	if memo[N][K] != 0 {
		return memo[N][K]
	}

	res := N + 1
	for i := 1; i <= N; i++ {
		res = min2(res, max(dp1(K, N-i, memo), dp1(K-1, i-1, memo))+1)
	}
	memo[N][K] = res
	return res
}

func min2(i, j int) int {
	if i < j {
		return i
	}
	return j
}

func max(i, j int) int {
	if i < j {
		return j
	}
	return i
}

func main() {
	fmt.Println(supperEggDrop1(2, 6))
}
