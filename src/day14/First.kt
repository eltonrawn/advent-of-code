package day14

import java.io.File

class First {

    var initxy = MutableList(0) {Pair(0, 0)}
    var vel = MutableList(0) {Pair(0, 0)}
    var n = 0
    var m = 0

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


        var cnt = MutableList(4) {0}
        for(i in initxy.indices) {
            println("${initxy[i]} ${vel[i]}")
            val px = initxy[i].first
            val py = initxy[i].second
            val vx = vel[i].first
            val vy = vel[i].second
            val nx = (((px + (vx * 100)) % n) + n) % n
            val ny = (((py + (vy * 100)) % m) + m) % m


            if(nx < (n / 2) && ny < (m / 2)) {
                cnt[0]++
            }
            else if(nx < (n / 2) && ny > (m / 2)) {
                cnt[1]++
            }
            else if(nx > (n / 2) && ny < (m / 2)) {
                cnt[2]++
            }
            else if(nx > (n / 2) && ny > (m / 2)) {
                cnt[3]++
            }

        }
        var ans = 1L
        for(i in 0 .. 3) {
            ans *= cnt[i].toLong()
        }
        println("$n, $m")
        println(ans)

    }
}

fun main() {
    First().solve()
}