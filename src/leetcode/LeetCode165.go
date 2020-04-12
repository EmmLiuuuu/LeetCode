package main

import (
	"fmt"
	"strconv"
	"strings"
)

func compareVersion(version1 string, version2 string) int {
	splits1 := strings.Split(version1, ".")
	splits2 := strings.Split(version2, ".")
	i := 0
	for ; i < len(splits1) && i < len(splits2); i++ {
		j := skipZero(splits1[i])
		k := skipZero(splits2[i])

		value1, _ := strconv.ParseInt(splits1[i][j:], 10, 32)
		value2, _ := strconv.ParseInt(splits2[i][k:], 10, 32)

		if value1 > value2 {
			return 1
		} else if value1 < value2 {
			return -1
		}
	}

	if i < len(splits1) {
		for i < len(splits1) {
			for j := 0; j < len(splits1[i]); j++ {
				if splits1[i][j] != '0' {
					return 1
				}
			}
			i++
		}
	} else if i < len(splits2) {
		for i < len(splits2) {
			for j := 0; j < len(splits2[i]); j++ {
				if splits2[i][j] != '0' {
					return -1
				}
			}
			i++
		}
	}

	return 0
}

func skipZero(s string) int {
	if len(s) == 1 {
		return 0
	}
	j := 0
	for ; j < len(s); j++ {
		if s[j] != '0' {
			return j
		}
	}
	return j - 1
}

func main() {
	fmt.Println(compareVersion("0.1", "1.1"))
	fmt.Println(compareVersion("1.0.1", "1.1"))
	fmt.Println(compareVersion("7.5.2.4", "7.5.3"))
	fmt.Println(compareVersion("1.0.1", "1.00000.1"))
	fmt.Println(compareVersion("1.1", "1.10"))
	fmt.Println(compareVersion("1.2", "1.10"))
	fmt.Println(compareVersion("1", "1.0.1"))
}
