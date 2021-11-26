/*
 * @lc app=leetcode.cn id=460 lang=golang
 *
 * [460] LFU 缓存
 */

package leetcode

import (
	"encoding/json"
	"fmt"
)

// @lc code=start

type Node struct {
	Prev  *Node
	Next  *Node
	Val   int
	Count int
	Index int
}

type LFUCache struct {
	capacity int
	cache    map[int]*Node
	head     *Node
	tail     *Node
	minHeap  []*Node
}

func Constructor(capacity int) LFUCache {
	return LFUCache{
		cache:    make(map[int]*Node, capacity),
		minHeap:  make([]*Node, capacity, capacity),
		capacity: capacity,
	}
}

func (this *LFUCache) Get(key int) int {
	defer func() {
		cacheBytes, _ := json.Marshal(this.cache)
		heapBytes, _ := json.Marshal(this.minHeap)
		fmt.Printf("Get, cache: %v, heap: %v\n", string(cacheBytes), string(heapBytes))
	}()
	if this.capacity == 0 {
		return -1
	}
	trick, exist := this.cache[key]
	if !exist {
		return -1
	}
	trick.Count++
	this.toHead(trick)
	this.swap(trick.Index, 0)
	this.shiftDown(0)
	return trick.Val
}

func (this *LFUCache) Put(key int, value int) {
	defer func() {
		cacheBytes, err := json.Marshal(this.cache)
		heapBytes, head_err := json.Marshal(this.minHeap)
		fmt.Printf("Put, cache: %v, heap: %v, cache err: %v, heap err: %v\n", string(cacheBytes), string(heapBytes), err, head_err)
	}()
	if this.capacity == 0 {
		return
	}
	if trick, exist := this.cache[key]; exist {
		trick.Val = value
		trick.Count++
		this.toHead(trick)
		this.swap(trick.Index, 0)
		this.shiftDown(0)
	} else {
		if len(this.cache) >= this.capacity {
			this.popHeap()
			delete(this.cache, key)
		}
		newNode := &Node{
			Val:   value,
			Count: 1,
			Prev:  nil,
			Index: len(this.cache),
		}
		this.cache[key] = newNode
		newNode.Next = this.head
		if this.head != nil {
			this.head.Prev = newNode
		}
		this.head = newNode
		fmt.Printf("add heap, val: %v, cache len: %d, heap len: %d, head: %v\n", value, len(this.cache), len(this.minHeap), *this.head)
		this.addHeap(newNode)
	}
}

func (this *LFUCache) toHead(trick *Node) {
	if trick.Prev != nil {
		trick.Prev.Next = trick.Next
		if trick.Next != nil {
			trick.Next.Prev = trick.Prev
		}
		trick.Prev = nil
		trick.Next = this.head
		this.head = trick
	}
}

func (this *LFUCache) shiftUp(cur int) {
	parent := (cur - 1) / 2
	for cur > 0 {
		if this.minHeap[cur].Count < this.minHeap[parent].Count {
			this.swap(cur, parent)
			cur = parent
			parent = (cur - 1) / 2
		} else {
			break
		}
	}
}

func (this *LFUCache) shiftDown(cur int) {
	fmt.Printf("shift down, cur: %d\n", cur)
	child := 2*cur + 1
	n := len(this.cache)
	for child < n {
		if child+1 < n && this.minHeap[child+1].Count < this.minHeap[child].Count {
			child++
		}
		if this.minHeap[child].Count < this.minHeap[cur].Count {
			this.swap(cur, child)
			cur = child
			child = 2*cur + 1
		} else {
			break
		}
	}
}

func (this *LFUCache) swap(src, dst int) {
	this.minHeap[src], this.minHeap[dst] = this.minHeap[dst], this.minHeap[src]
	this.minHeap[src].Index, this.minHeap[dst].Index = this.minHeap[dst].Index, this.minHeap[src].Index
}

func (this *LFUCache) popHeap() {
	minTop := this.minHeap[0]
	var eldestNode *Node
	if (1 < len(this.cache) && this.minHeap[1].Count == minTop.Index) ||
		(2 < len(this.cache) && this.minHeap[2].Count == minTop.Index) {
		node := minTop
		for node != nil {
			if minTop.Count == node.Count {
				eldestNode = node
			}
			node = node.Next
		}
	}
	if eldestNode == nil {
		this.minHeap[0], this.minHeap[len(this.cache)-1] = this.minHeap[len(this.cache)-1], nil
		this.minHeap[0].Index = 0
	} else {
		if eldestNode.Prev != nil {
			eldestNode.Prev.Next = eldestNode.Next
			if eldestNode.Next != nil {
				eldestNode.Next.Prev = eldestNode.Prev
			}
			eldestNode.Prev = nil
		}
		this.swap(0, eldestNode.Index)
		this.swap(0, len(this.cache)-1)
		this.minHeap[len(this.cache)-1] = nil
	}
	this.shiftDown(0)
}

func (this *LFUCache) addHeap(node *Node) {
	this.minHeap[len(this.cache)-1] = node
	this.shiftUp(len(this.cache) - 1)
}

func main() {
	x := Constructor(2)
	x.Put(1, 1)
	x.Put(2, 2)
	x.Get(1)
	x.Put(3, 3)
	x.Get(2)
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */
// @lc code=end
