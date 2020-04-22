package main

func numIslands(grid [][]byte) int {
	height := len(grid)
	if height == 0 {
		return 0
	}
	width := len(grid[0])

	count := 0
	for i := 0; i < height; i++ {
		for j := 0; j < width; j++ {
			if grid[i][j] == '1' {
				count++
				numIslandsHelper(grid, i, j, width, height)
			}
		}
	}
	return count
}

func numIslandsHelper(grid [][]byte, i, j, width, height int) {
	grid[i][j] = '0'
	if i-1 >= 0 && grid[i-1][j] == '1' {
		numIslandsHelper(grid, i-1, j, width, height)
	}
	if i+i < height && grid[i+1][j] == '1' {
		numIslandsHelper(grid, i+1, j, width, height)
	}
	if j-1 >= 0 && grid[i][j-1] == '1' {
		numIslandsHelper(grid, i, j-1, width, height)
	}
	if j+1 < width && grid[i][j+1] == '1' {
		numIslandsHelper(grid, i, j+1, width, height)
	}
}
