package day14

import java.io.File
import java.io.PrintWriter

class Second {

    var initxy = MutableList(0) {Pair(0, 0)}
    var vel = MutableList(0) {Pair(0, 0)}
    var n = 0
    var m = 0
    var res = MutableList(0) {MutableList(0) {0} }

    fun moveRobots(tm: Int): Int {
        res = MutableList(n) {MutableList(m) {0} }
        for(i in initxy.indices) {
            val px = initxy[i].first
            val py = initxy[i].second
            val vx = vel[i].first
            val vy = vel[i].second
            val nx = (((px + (vx * tm)) % n) + n) % n
            val ny = (((py + (vy * tm)) % m) + m) % m
            res[nx][ny]++
        }
        return 0
    }

    fun printTree(writer: PrintWriter, tm: Int) {
        moveRobots(tm)

        for(i in 0 .. n - 1) {
            for(j in 0 .. m - 1) {
                if(res[i][j] > 0) {
                    writer.print('#')
                } else {
                    writer.print('.')
                }
            }
            writer.println()
        }
        writer.println()
        writer.println()
    }

    fun solve() {
        val fileName = "src/day14/input.txt"
        val lines = File(fileName).readLines()
        println(lines)
//        p=0,4 v=3,-3
        var regex = """p=([+-]?\d+),([+-]?\d+) v=([+-]?\d+),([+-]?\d+)""".toRegex()
        for(line in lines) {
            val matchResult = regex.matchEntire(line)!!
            val py = matchResult.groupValues[1].toInt()
            val px = matchResult.groupValues[2].toInt()
            initxy.add(Pair(px, py))
            n = maxOf(n, px + 1)
            m = maxOf(m, py + 1)

            val vy = matchResult.groupValues[3].toInt()
            val vx = matchResult.groupValues[4].toInt()
            vel.add(Pair(vx, vy))
        }

        println("$n, $m")

        val outfileName = "src/day14/out.txt"

        val writer = PrintWriter(outfileName)

        var timeLimit = 10000

        var mxCnt = 0
        var pos = 0
        for(tm in 0 .. timeLimit) {
            moveRobots(tm)

            var cnt = 0

            for(i in 0 .. n - 1) {
                cnt = 0
                for(j in 0 .. m - 1) {
                    if(res[i][j] > 0 && (j - 1 >= 0) && (res[i][j - 1] > 0)) {
                        cnt++
                    }
                    else {
                        if(mxCnt < cnt) {
                            mxCnt = cnt
                            pos = tm
                        }
                        cnt = 0
                    }
                }
                if(mxCnt < cnt) {
                    mxCnt = cnt
                    pos = tm
                }
            }

        }
        println("$mxCnt $pos")

        printTree(writer, pos)

        writer.close()

    }
}

fun main() {
    Second().solve()
}