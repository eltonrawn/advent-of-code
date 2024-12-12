package day12

import java.io.File

class Second {
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
    var set = mutableListOf<List<Int>>()

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
                if(x != nx) {
                    set.add(listOf(dx[i], dy[i], nx, ny))
                } else {
                    set.add(listOf(dx[i], dy[i], ny, nx))
                }
                // we just need to check last param if there's a breakage, then add + 1
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
                val paramCnt = 0L
                set = mutableListOf<List<Int>>()
                func(i, j)

                set.sortWith(compareBy<List<Int>> {it[0]}.thenBy{it[1]}.thenBy{it[2]}.thenBy{it[3]})

                var le = 0
                var ret = 0L
                while(le < set.size) {
                    var ri = le
                    var last = -100
                    while(ri < set.size && set[le][0] == set[ri][0] && set[le][1] == set[ri][1] && set[le][2] == set[ri][2]) {
                        if(last + 1 != set[ri][3]) {
                            ret++
                        }
                        last = set[ri][3]
                        ri++
                    }
                    le = ri
                }

                ans += areaCnt * ret

                if(areaCnt != 0L) {
                    println("${grid[i][j]}, $areaCnt, $ret")
                    println(set)
                }
            }
        }
        println(ans)

    }
}

fun main() {
    Second().solve()
}

/*
*            (x - 1, y)
* (x, y - 1)            (x, y + 1)
*            (x + 1, y)
* */