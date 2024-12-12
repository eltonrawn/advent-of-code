package day12

import java.io.File

class First {
    var n = 0
    var m = 0
    var vis = MutableList(0) {MutableList(0) {false} }
    var grid = List(0) {""}
    var dx = listOf(0, 0, 1, -1)
    var dy = listOf(1, -1, 0, 0)

    fun isInside(x: Int, y: Int): Boolean {
        if(x in 0 .. n - 1 && y in 0 .. m - 1) {
            return true
        }
        return false
    }

    var areaCnt = 0L
    var paramCnt = 0L

    fun func(x: Int, y: Int) {
        if(vis[x][y]) return
        areaCnt++
        vis[x][y] = true
        for(i in 0 .. 3) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if(isInside(nx, ny) && grid[x][y] == grid[nx][ny]) {
                func(nx, ny)
            }
            else {
                paramCnt++
            }
        }
    }

    fun solve() {
        val fileName = "src/day12/input.txt"
        grid = File(fileName).readLines()
        n = grid.size
        m = grid[0].length
        vis = MutableList(n) {MutableList(m) {false} }

        println(grid)

        var ans = 0L

        for(i in 0 .. n - 1) {
            for(j in 0 .. m - 1) {
                areaCnt = 0L
                paramCnt = 0L
                func(i, j)
                ans += areaCnt * paramCnt
            }
        }
        println(ans)

    }
}

fun main() {
    First().solve()
}