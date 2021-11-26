/*
 * @lc app=leetcode.cn id=318 lang=golang
 *
 * [318] 最大单词长度乘积
 */

package leetcode

// @lc code=start
func maxProduct1(words []string) int {
	letterMap := make(map[byte]map[string]struct{}, 26)
	for _, word := range words {
		for _, letter := range word {
			if _, exist := letterMap[byte(letter)]; !exist {
				letterMap[byte(letter)] = map[string]struct{}{}
			}
			letterMap[byte(letter)][word] = struct{}{}

			if len(letterMap[byte(letter)]) == len(words) {
				return 0
			}
		}
	}

	var max int
	for _, word := range words {
		conflictMap := map[string]bool{}
		for _, letter := range word {
			for key, _ := range letterMap[byte(letter)] {
				conflictMap[key] = true
			}
		}
		if len(conflictMap) == len(words) {
			continue
		}
		for _, v := range words {
			if conflictMap[v] {
				continue
			}
			if max < len(word)*len(v) {
				max = len(word) * len(v)
			}
		}
	}
	return max
}

func maxProduct(words []string) int {
	wordBitMap := make([]int32, len(words), len(words))
	for index, word := range words {
		for _, letter := range word {
			wordBitMap[index] |= (0x1 << (letter - 'a'))
		}
	}

	var max int
	for i := 0; i < len(words)-1; i++ {
		for j := i + 1; j < len(words); j++ {
			if wordBitMap[i]&wordBitMap[j] == 0 {
				if len(words[i])*len(words[j]) > max {
					max = len(words[i]) * len(words[j])
				}
			}
		}
	}
	return max
}

// @lc code=end
