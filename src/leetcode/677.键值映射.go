/*
 * @lc app=leetcode.cn id=677 lang=golang
 *
 * [677] 键值映射
 */

package leetcode

// @lc code=start

type TireNode struct {
	val      int
	keyNode  bool
	chirdren [26]*TireNode
}

func (this *MapSum) addVal(key string, val int) {
	ptr := this.node
	for _, v := range key {
		if ptr.chirdren[v-'a'] == nil {
			ptr.chirdren[v-'a'] = &TireNode{chirdren: [26]*TireNode{}}
		}
		ptr = ptr.chirdren[v-'a']
	}
	ptr.val = val
	ptr.keyNode = true
}

func (this *MapSum) sumVal(node *TireNode) int {
	var tmp int
	if node == nil {
		return 0
	}
	if node.keyNode {
		tmp += node.val
	}
	for i := 0; i < 26; i++ {
		if node.chirdren[i] != nil {
			tmp += this.sumVal(node.chirdren[i])
		}
	}
	return tmp
}

type MapSum struct {
	node *TireNode
	// prefixMap map[string]int
	// sourceMap map[string]int
}

func Constructor() MapSum {
	return MapSum{
		node: &TireNode{chirdren: [26]*TireNode{}},
		// prefixMap: make(map[string]int, 50),
		// sourceMap: make(map[string]int, 50),
	}
}

func (this *MapSum) Insert(key string, val int) {
	this.addVal(key, val)
	// old, _ := this.sourceMap[key]
	// for i := 1; old != val && i <= len(key); i++ {
	// 	this.prefixMap[key[:i]] += (val - old)
	// }
	// this.sourceMap[key] = val
}

func (this *MapSum) Sum(prefix string) int {
	ptr := this.node
	for _, v := range prefix {
		if ptr.chirdren[v-'a'] != nil {
			ptr = ptr.chirdren[v-'a']
		} else {
			return 0
		}
	}
	return this.sumVal(ptr)
	// val, exist := this.prefixMap[prefix]
	// if exist {
	// 	return val
	// }
	// var sum int
	// for source, val := range this.sourceMap {
	// 	if strings.HasPrefix(source, prefix) {
	// 		sum += val
	// 	}
	// }
	// this.prefixMap[prefix] = sum
	// return sum
}

/**
 * Your MapSum object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Insert(key,val);
 * param_2 := obj.Sum(prefix);
 */
// @lc code=end
