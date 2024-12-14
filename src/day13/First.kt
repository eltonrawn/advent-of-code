package day13

import java.io.File

class First {

    fun extractXYForButton(input: String): Pair<Long, Long> {
        // Button A: X+94, Y+34
        val regex = """Button \w+: X([+-]?\d+), Y([+-]?\d+)""".toRegex()
        val matchResult = regex.matchEntire(input)!!

        val x = matchResult.groupValues[1].toLong()
        val y = matchResult.groupValues[2].toLong()
        return Pair(x, y)
    }

    fun extractXYForTotal(input: String): Pair<Long, Long> {
        // Prize: X=8400, Y=5400
        val regex = """Prize: X=([+-]?\d+), Y=([+-]?\d+)""".toRegex()
        val matchResult = regex.matchEntire(input)!!

        val x = matchResult.groupValues[1].toLong() + 10000000000000L
        val y = matchResult.groupValues[2].toLong() + 10000000000000L
        return Pair(x, y)
    }
    val dpLimit = 1000

    var dp = MutableList(dpLimit) {MutableList(dpLimit) {-1L} }
    var axy = Pair(0L, 0L)
    var bxy = Pair(0L, 0L)
    var totalxy = Pair(0L, 0L)
    var isPossible = false

    fun func(aTaken: Int, bTaken: Int): Long {
        val curx = (aTaken * axy.first) + (bTaken * bxy.first)
        val cury = (aTaken * axy.second) + (bTaken * bxy.second)
        if(aTaken >= dpLimit || bTaken >= dpLimit) {
            return 1000000000
        }
        if(curx > totalxy.first || cury > totalxy.second) {
            return 1000000000
        }
        if(curx == totalxy.first && cury == totalxy.second) {
            isPossible = true
            return (aTaken * 3) + bTaken + 0L
        }
        if(dp[aTaken][bTaken] != -1L) {
            return dp[aTaken][bTaken]
        }


        var ans = 0L
        ans = func(aTaken + 1, bTaken)
        ans = minOf(ans, func(aTaken, bTaken + 1))
        dp[aTaken][bTaken] = ans
        return ans
    }

    fun solve() {
        val fileName = "src/day13/test.txt"
        val lines = File(fileName).readLines().filter {it.isNotBlank()}
        var i = 0
        var ans = 0L
        while(i < lines.size) {
            var input = ""

            // Button A: X+94, Y+34
            input = lines[i]
            println(input)
            axy = extractXYForButton(input)
            println("${ axy.first } ${ axy.second }")
            i++

            // Button B: X+22, Y+67
            input = lines[i]
            println(input)
            bxy = extractXYForButton(input)
            println("${ bxy.first } ${ bxy.second }")
            i++

            // Prize: X=8400, Y=5400
            input = lines[i]
            println(input)
            totalxy = extractXYForTotal(input)
            println("${ totalxy.first } ${ totalxy.second }")
            i++

            isPossible = false
            dp = MutableList(dpLimit) {MutableList(dpLimit) {-1L} }
            val tmp = func(0, 0)
            if(isPossible) {
                ans += tmp
            }

        }
        println(ans)
    }
}

fun main() {
    First().solve()
}