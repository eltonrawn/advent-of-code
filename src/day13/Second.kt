package day13

import java.io.File

class Second {

    fun extractXYForButton(input: String): Pair<Long, Long> {
        val regex = """Button \w+: X([+-]?\d+), Y([+-]?\d+)""".toRegex()
        val matchResult = regex.matchEntire(input)!!

        val x = matchResult.groupValues[1].toLong()
        val y = matchResult.groupValues[2].toLong()
        return Pair(x, y)
    }

    fun extractXYForTotal(input: String): Pair<Long, Long> {
        val regex = """Prize: X=([+-]?\d+), Y=([+-]?\d+)""".toRegex()
        val matchResult = regex.matchEntire(input)!!

        val x = matchResult.groupValues[1].toLong() + 10000000000000L
        val y = matchResult.groupValues[2].toLong() + 10000000000000L
//        val x = matchResult.groupValues[1].toLong()
//        val y = matchResult.groupValues[2].toLong()
        return Pair(x, y)
    }

    var axy = Pair(0L, 0L)
    var bxy = Pair(0L, 0L)
    var totalxy = Pair(0L, 0L)


    fun shoo():Long {

//        val x1 = 94
//        val x2 = 22
//        val x3 = 8400
//
//        val y1 = 34
//        val y2 = 67
//        val y3 = 5400

        val x1 = axy.first
        val x2 = bxy.first
        val x3 = totalxy.first

        val y1 = axy.second
        val y2 = bxy.second
        val y3 = totalxy.second

        val fir = ((y3 * x2) - (y2 * x3))
        val sec = ((y1 * x2) - (y2 * x1))

        if(fir % sec != 0L) {
            return 0L
        }
        val a = fir / sec
        if((x3 - (x1 * a)) % x2 != 0L) {
            return 0L
        }

        val b = (x3 - (x1 * a)) / x2
//        println(a)
//        println(b)
        return a * 3L + b
    }



    fun solve() {
        val fileName = "src/day13/input.txt"
        val lines = File(fileName).readLines().filter {it.isNotBlank()}
        var i = 0
        var ans = 0L
        while(i < lines.size) {
            var input = ""

            // Button A: X+94, Y+34
            input = lines[i]
            axy = extractXYForButton(input)
            i++

            // Button B: X+22, Y+67
            input = lines[i]
            bxy = extractXYForButton(input)
            i++

            // Prize: X=8400, Y=5400
            input = lines[i]
            totalxy = extractXYForTotal(input)
            i++

            ans += shoo()

        }
        println(ans)
    }
}


fun main() {
    Second().solve()

}
/*
x1a + x2b = x3
y1a + y2b = y3

x1a + x2b = x3
b = ((x3 - x1a) / x2)

y1a + y2b = y3
y1a + (y2 * (x3 - x1a) / x2) = y3
y1a + ((y2 * (x3 - x1a)) / x2) = y3
(y1ax2 + (y2 * (x3 - x1a))) / x2 = y3
(y1ax2 + (y2 * (x3 - x1a))) = y3x2
(y1ax2 + y2x3 - y2x1a) = y3x2
(y1ax2 - y2x1a) = y3x2 - y2x3
a (y1x2 - y2x1) = y3x2 - y2x3
a = (y3x2 - y2x3) / (y1x2 - y2x1)


x1a + x2b = x3
x2b = x3 - x1a
b = (x3 - x1a) / x2
 */