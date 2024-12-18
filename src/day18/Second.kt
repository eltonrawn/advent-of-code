package day18

import java.io.File
import java.util.Queue
import java.util.LinkedList

class Second {
    var n = 0
    var m = 0
    var grid = MutableList(0) {MutableList(0) {0} }
    var dx = listOf(0, 0, 1, -1)
    var dy = listOf(1, -1, 0, 0)
    var mxCost = 1000000000

    fun isValid(x: Int, y: Int): Boolean {
        if(x in 0 .. n - 1 && y in 0 .. m - 1) {
            return true
        }
        return false
    }

    fun isReachable(): Boolean {
        var cost = MutableList(n) {MutableList(m) {mxCost} }
        var queue: Queue<List<Int>> = LinkedList()
        queue.add(listOf(0, 0))
        cost[0][0] = 0

        while(queue.isNotEmpty()) {
            val top = queue.peek()
            queue.poll()
            val x = top[0]
            val y = top[1]

            for(i in 0 .. 3) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if(!isValid(nx, ny) || grid[nx][ny] == 1) {
                    continue
                }
                if(cost[nx][ny] > cost[x][y] + 1) {
                    cost[nx][ny] = cost[x][y] + 1
                    queue.add(listOf(nx, ny))
                }
            }
        }

//        println("cost: ${cost[n - 1][m - 1]}")

        return (cost[n - 1][m - 1] != mxCost)
    }



    fun solve() {
        val fileName = "src/day18/input.txt"
        val lines = File(fileName).readLines()
        println(lines)
        for(line in lines) {
            var y = line.split(",")[0].toInt()
            var x = line.split(",")[1].toInt()
            n = maxOf(n, x + 1)
            m = maxOf(m, y + 1)
        }

        println(lines.size)

        println("$n $m")
        grid = MutableList(n) {MutableList(m) {0} }

        for(i in 0 .. lines.size - 1) {
            val line = lines[i]
            var y = line.split(",")[0].toInt()
            var x = line.split(",")[1].toInt()
            grid[x][y] = 1
            if(!isReachable()) {
                println("$y,$x")
                break
            }
        }

//        for(i in 0 .. n - 1) {
//            for(j in 0 .. m - 1) {
//                print("${grid[i][j]} ")
//            }
//            println()
//        }


    }
}

fun main() {
    Second().solve()
}