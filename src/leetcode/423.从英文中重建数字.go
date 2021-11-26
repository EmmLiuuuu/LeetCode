/*
 * @lc app=leetcode.cn id=423 lang=golang
 *
 * [423] 从英文中重建数字
 */

package leetcode

// @lc code=start

func originalDigits(s string) string {
	var digitalArray = [26]uint16{}
	for _, v := range s {
		digitalArray[v-'a']++
	}
	var cnt = [10]uint16{}
	cnt[0] = digitalArray['z'-'a']
	cnt[2] = digitalArray['w'-'a']
	cnt[4] = digitalArray['u'-'a']
	cnt[6] = digitalArray['x'-'a']
	cnt[8] = digitalArray['g'-'a']

	cnt[3] = digitalArray['h'-'a'] - cnt[8]
	cnt[5] = digitalArray['f'-'a'] - cnt[4]
	cnt[7] = digitalArray['s'-'a'] - cnt[6]
	cnt[9] = digitalArray['i'-'a'] - cnt[5] - cnt[6] - cnt[8]
	cnt[1] = digitalArray['o'-'a'] - cnt[0] - cnt[2] - cnt[4]

	var res []byte
	for i := 0; i < 10; i++ {
		for j := 0; j < int(cnt[i]); j++ {
			res = append(res, byte(i)+'0')
		}
	}

	return string(res)
}

// @lc code=end
