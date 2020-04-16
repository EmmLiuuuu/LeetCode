package main

import "fmt"

func countPrimes(n int) int {
	isPrime := make([]bool, n, n)
	for i := 0; i < n; i++ {
		isPrime[i] = true
	}
	for i := 2; i <= n/2; i++ {
		if isPrime[i] {
			for j := 2 * i; j < n; j += i {
				isPrime[j] = false
			}
		}
	}
	count := 0
	for i := 2; i < n; i++ {
		if isPrime[i] {
			count++
		}
	}
	return count
}

func main() {
	fmt.Println(countPrimes(11))
}
