/*
 * @lc app=leetcode.cn id=391 lang=golang
 *
 * [391] 完美矩形
 */

package leetcode

import (
	"math"
)

// @lc code=start

type Corner struct {
	x, y int
}

func isRectangleCover(rectangles [][]int) bool {
	var expectedSize, minX, maxX, minY, maxY int = 0, math.MaxInt32, math.MinInt32, math.MaxInt32, math.MinInt32
	cornerMap := make(map[Corner]struct{}, 4*len(rectangles))

	for _, tangles := range rectangles {
		x, y, X, Y := tangles[0], tangles[1], tangles[2], tangles[3]
		expectedSize += (X - x) * (Y - y)

		cornerCheck(cornerMap, &Corner{x: x, y: y})
		cornerCheck(cornerMap, &Corner{x, Y})
		cornerCheck(cornerMap, &Corner{X, y})
		cornerCheck(cornerMap, &Corner{X, Y})
	}

	if len(cornerMap) != 4 {
		return false
	}

	for corner, _ := range cornerMap {
		maxX = max391(corner.x, maxX)
		minX = min391(corner.x, minX)
		maxY = max391(corner.y, maxY)
		minY = min391(corner.y, minY)
	}

	return (maxX-minX)*(maxY-minY) == expectedSize
}

func cornerCheck(cornerMap map[Corner]struct{}, corner *Corner) {
	if _, exist := cornerMap[*corner]; exist {
		delete(cornerMap, *corner)
	} else {
		cornerMap[*corner] = struct{}{}
	}
}

func min391(src, dst int) int {
	if src < dst {
		return src
	}
	return dst
}

func max391(src, dst int) int {
	if src < dst {
		return dst
	}
	return src
}

// @lc code=end
