package main

import (
	"fmt"
	"sort"
)

type pair [][]int

func (res pair) Len() int {
	return len(res)
}

func (res pair) Less(i, j int) bool {
	if res[i][0] == res[j][0] {
		return res[i][1] < res[j][1]
	}
	return res[i][0] < res[j][0]
}

func (res *pair) Swap(i, j int) {
	(*res)[i], (*res)[j] = (*res)[j], (*res)[i]
}

func merge(intervals [][]int) [][]int {
	var value pair = intervals
	sort.Sort(&value)
	res := make([][]int, 0)
	for i := 0; i < len(intervals); i++ {
		length := len(res)
		if length == 0 || res[length-1][1] < intervals[i][0] {
			res = append(res, intervals[i])
		} else {
			if res[length-1][1] >= intervals[i][0] {
				if res[length-1][1] <= intervals[i][1] {
					res[length-1][1] = intervals[i][1]
				}
			}
		}
	}
	return res
}

func main() {
	fmt.Println(merge([][]int{{1, 4}, {2, 3}, {8, 10}, {15, 18}}))
}
