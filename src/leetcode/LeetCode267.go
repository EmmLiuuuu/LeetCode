package main

import "fmt"

/*
  Given a string s, return all the palindromic permutations (without duplicates) of it.
  Return an empty list if no palindromic permutation could be form.
  For example:
  Given s = “aabb”, return [“abba”, “baab”].
  Given s = “abc”, return [].
  如果回文全排列存在，只需要生成前半段字符串即可
*/

func generatePalindromes(s string) []string {
	countMap := make(map[byte]int)
	//value不需要记录
	resMap := make(map[string]byte)
	var midStr string
	var t string
	for i := 0; i < len(s); i++ {
		countMap[s[i]] += 1
	}
	for key, value := range countMap {
		if value%2 == 1 {
			if len(midStr) >= 1 {
				return []string{}
			}
			midStr = string(key)
		}
		for i := 0; i < value/2; i++ {
			t += string(key)
		}
	}

	tByte := []byte(t)
	permute(&tByte, 0, midStr, &resMap)
	res := make([]string, len(resMap))
	for key := range resMap {
		res = append(res, key)
	}
	return res
}

func permute(t *[]byte, start int, mid string, res *map[string]byte) {
	if start >= len(*t) {
		revT := make([]byte, len(*t))
		for i, j := 0, len(*t)-1; i < len(*t) && j >= 0; i, j = i+1, j-1 {
			revT[j] = (*t)[i]
		}
		(*res)[string(*t)+mid+string(revT)] = 0
	}

	for i := start; i < len(*t); i++ {
		if i != start && (*t)[i] == (*t)[start] {
			continue
		}
		(*t)[i], (*t)[start] = (*t)[start], (*t)[i]
		permute(t, start+1, mid, res)
		(*t)[i], (*t)[start] = (*t)[start], (*t)[i]
	}
}

func main() {
	fmt.Println(generatePalindromes("aabb"))
}
