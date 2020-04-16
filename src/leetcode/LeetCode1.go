package main

func twoSum(nums []int, target int) []int {
	res := map[int]int{}
	for index, value := range nums {
		if v, ok := res[target-value]; ok {
			return []int{index, v}
		} else {
			res[value] = index
		}
	}
	return []int{}
}

func main() {

}
