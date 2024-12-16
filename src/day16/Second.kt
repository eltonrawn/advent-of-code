package day16

import java.io.File
import java.util.Queue
import java.util.LinkedList

class Second {

    // up, right, down, left
    val dx = listOf(-1, 0, 1, 0)
    val dy = listOf(0, 1, 0, -1)
    var n = 0
    var m = 0

    fun solve() {
        val fileName = "src/day16/input.txt"
        val grid = File(fileName).readLines()
        for(line in grid) {
            println(line)
        }
        n = grid.size
        m = grid[0].length

        var queue: Queue<List<Int>> = LinkedList()
        var cost = MutableList(n) { MutableList(m) { MutableList(4) {1000000000} } }

        for(i in 0 .. n - 1) {
            for(j in 0 .. m - 1) {
                if(grid[i][j] == 'S') {
                    queue.add(listOf(i, j, 1))
                    cost[i][j][1] = 0
                }
            }
        }

        while(queue.isNotEmpty()) {
            val top = queue.peek()
            queue.poll()
            val x = top[0]
            val y = top[1]
            val d = top[2]

            // go in the same direction
            var nx = x + dx[d]
            var ny = y + dy[d]
            var nd = d

            if(grid[nx][ny] != '#' && cost[nx][ny][nd] > cost[x][y][d] + 1) {
                cost[nx][ny][nd] = cost[x][y][d] + 1
                queue.add(listOf(nx, ny, nd))
            }

            // change direction clockwise
            nx = x
            ny = y
            nd = (d + 1) % 4
            if(cost[nx][ny][nd] > cost[x][y][d] + 1000) {
                cost[nx][ny][nd] = cost[x][y][d] + 1000
                queue.add(listOf(nx, ny, nd))
            }

            // change direction counter-clockwise
            nx = x
            ny = y
            nd = ((d - 1) + 4) % 4
            if(cost[nx][ny][nd] > cost[x][y][d] + 1000) {
                cost[nx][ny][nd] = cost[x][y][d] + 1000
                queue.add(listOf(nx, ny, nd))
            }
        }

        for(i in 0 .. n - 1) {
            for(j in 0 .. m - 1) {
                var foo = 1000000000
                for(k in 0 .. 3) {
                    foo = minOf(foo, cost[i][j][k])
                }
                if(foo == 1000000000) {
                    print(".")
                } else {
                    print("X")
                }
            }
            println()
        }

        // doing a backtrack
        queue = LinkedList()
        var covered = MutableList(n) { MutableList(m) { MutableList(4) {false} } }

        var ans = 1000000000

        for(i in 0 .. n - 1) {
            for(j in 0 .. m - 1) {
                if(grid[i][j] == 'E') {
                    for(k in 0 .. 3) {
                        ans = minOf(ans, cost[i][j][k])
                    }

                    for(k in 0 .. 3) {
                        if(cost[i][j][k] == ans) {
                            queue.add(listOf(i, j, k))
                            covered[i][j][k] = true
                        }
                    }
                }
            }
        }

        println(ans)

        while(queue.isNotEmpty()) {
            val top = queue.peek()
            queue.poll()
            val x = top[0]
            val y = top[1]
            val d = top[2]

            // coming from the same direction
            var nx = x - dx[d]
            var ny = y - dy[d]
            var nd = d

            if(grid[nx][ny] != '#' && cost[nx][ny][nd] + 1 == cost[x][y][d]) {
                covered[nx][ny][nd] = true
                queue.add(listOf(nx, ny, nd))
            }

            // change direction clockwise
            nx = x
            ny = y
            nd = (d + 1) % 4
            if(cost[nx][ny][nd] + 1000 == cost[x][y][d]) {
                covered[nx][ny][nd] = true
                queue.add(listOf(nx, ny, nd))
            }

            // change direction counter-clockwise
            nx = x
            ny = y
            nd = ((d - 1) + 4) % 4
            if(cost[nx][ny][nd] + 1000 == cost[x][y][d]) {
                covered[nx][ny][nd] = true
                queue.add(listOf(nx, ny, nd))
            }

        }

        var ret = 0
        for(i in 0 .. n - 1) {
            for(j in 0 .. m - 1) {
                var isTaken = false
                for(k in 0 .. 3) {
                    if(covered[i][j][k]) isTaken = true
                }
                if(isTaken) {
                    ret++
                }
            }
        }
        println(ret)




    }
}

fun main() {
    Second().solve()
}