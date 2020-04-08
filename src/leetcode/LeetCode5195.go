package main

import (
	"fmt"
	"sort"
)

type Pair struct {
	name  byte
	count int
}

type PairArray []Pair

func (list PairArray) Less(i, j int) bool {
	return list[i].count > list[j].count
}

func (list PairArray) Len() int {
	return len(list)
}

func (list PairArray) Swap(i, j int) {
	list[i], list[j] = list[j], list[i]
}

func longestDiverseString(a int, b int, c int) string {
	list := make(PairArray, 3)
	list = append(list, Pair{'a', a})
	list = append(list, Pair{'b', b})
	list = append(list, Pair{'c', c})
	sort.Sort(list)

	var res []byte
	for {
		for index := range list {
			if list[index].count >= 2 {
				res = append(res, list[index].name, list[index].name)
				list[index].count -= 2
			} else if list[index].count == 1 {
				res = append(res, list[index].name)
				list[index].count -= 1
				break
			} else {
				break
			}
		}
		sort.Sort(list)
		if list[0].count == 0 {
			break
		}
		if res != nil && list[0].name == res[len(res)-1] {
			break
		}
	}
	for i := range list {
		for index := 0; index < len(res)-1 && list[i].count > 0; index++ {
			if res[index] != list[i].name && res[index+1] != list[i].name {
				tmp := append([]byte{}, res[index+1:]...)
				if list[i].count >= 2 {
					res = append(res[:index+1], list[i].name, list[i].name)
					list[i].count -= 2
				} else {
					res = append(res[:index+1], list[i].name)
					list[i].count--
				}
				res = append(res, tmp...)
			}
		}
	}
	return string(res)
}

func main() {
	fmt.Println(longestDiverseString(1, 8, 12))
}
