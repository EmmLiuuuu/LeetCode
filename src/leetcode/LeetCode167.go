package main

func twoSum1(numbers []int, target int) []int {
	i := 0
	j := len(numbers) - 1
	for i < j {
		num := numbers[i] + numbers[j]
		if num > target {
			j--
		} else if num < target {
			i++
		} else {
			return []int{i, j}
		}
	}
	return []int{}
}

func main() {

}
