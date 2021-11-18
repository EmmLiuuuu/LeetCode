/*
 * @lc app=leetcode.cn id=520 lang=golang
 *
 * [520] 检测大写字母
 */

package leetcode

// @lc code=start
func detectCapitalUse(word string) bool {
	var upperCount, lowerCount int
	for index, letter := range word {
		if isUpperLetter(byte(letter)) {
			if lowerCount > 0 && index > 0 {
				return false
			}
			upperCount++
		} else {
			if upperCount > 1 && index > 0 {
				return false
			}
			lowerCount++
		}
	}
	return true
}

func isUpperLetter(b byte) bool {
	return b <= 'Z' && b >= 'A'
}

// @lc code=end
