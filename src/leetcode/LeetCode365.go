package main

import "fmt"

type operation struct {
	remainX, remainY int
}

//DFS
func canMeasureWater(x int, y int, z int) bool {
	stack := []operation{{0, 0}}
	set := map[operation]byte{}
	for len(stack) > 0 {
		ope := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		if ope.remainX == z || ope.remainY == z || ope.remainY+ope.remainX == z {
			return true
		}
		if _, ok := set[ope]; ok {
			continue
		}
		set[operation{ope.remainX, ope.remainY}] = 0
		stack = append(stack, operation{x, ope.remainY})
		stack = append(stack, operation{ope.remainX, y})
		stack = append(stack, operation{0, ope.remainY})
		stack = append(stack, operation{ope.remainX, 0})
		stack = append(stack, operation{ope.remainX - min3(ope.remainX, y-ope.remainY), ope.remainY + min3(ope.remainX, y-ope.remainY)})
		stack = append(stack, operation{ope.remainX + min3(ope.remainY, x-ope.remainX), ope.remainY - min3(ope.remainY, x-ope.remainX)})
	}
	return false
}

func min3(i, j int) int {
	if i < j {
		return i
	}
	return j
}

func canMeasureWater1(x int, y int, z int) bool {
	if x+y < z {
		return false
	}
	if x == 0 || y == 0 {
		return z == 0 || x+y == z
	}
	return z%gcd(x, y) == 0
}

func gcd(i, j int) int {
	if j == 0 {
		return i
	}
	return gcd(j, i%j)
}

func main() {
	fmt.Println(canMeasureWater1(3, 5, 4))
}
