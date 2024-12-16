package day16

import java.io.File
import java.util.Queue
import java.util.LinkedList

class First {

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

        val queue: Queue<List<Int>> = LinkedList()
        val cost = MutableList(n) { MutableList(m) { MutableList(4) {1000000000} } }

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
//                print("$foo ")
            }
            println()
        }

        var ans = 1000000000

        for(i in 0 .. n - 1) {
            for(j in 0 .. m - 1) {
                if(grid[i][j] == 'E') {
                    for(k in 0 .. 3) {
                        ans = minOf(ans, cost[i][j][k])
                    }
                }
            }
        }

        println(ans)

    }
}

fun main() {
    First().solve()
}