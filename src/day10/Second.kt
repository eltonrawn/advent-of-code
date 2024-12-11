package day10

import java.io.File

class Second {
    val dx = listOf(0, 0, 1, -1)
    val dy = listOf(1, -1, 0, 0)
    var n = 0
    var m = 0
    fun isValid(x: Int, y: Int): Boolean {
        if((x in 0 .. n - 1) && (y in 0 .. m - 1)) {
            return true
        }
        return false
    }
    fun dfs(x: Int, y: Int, grid: List<String>): Int {
        if(grid[x][y] == '9') {
            return 1
        }
        var ans = 0
        for(i in 0 .. 3) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if(isValid(nx, ny) && grid[nx][ny].toString().toInt() == grid[x][y].toString().toInt() + 1) {
                ans += dfs(nx, ny, grid)
            }
        }
        return ans
    }
    fun solve() {
        val fileName = "src/day10/input.txt"
        val grid = File(fileName).readLines()
        n = grid.size
        m = grid[0].length
        var ans = 0
        for(i in 0 .. n - 1) {
            for(j in 0 .. m - 1) {
                if(grid[i][j] == '0') {
                    ans += dfs(i, j, grid)
                }
            }
        }
        println(ans)
    }

}

fun main() {
    Second().solve()
}