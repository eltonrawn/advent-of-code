package day19

import java.io.File

class Second {
    var patterns = List(0) {""}
    var make = ""
    var dp = MutableList(0) {-1L}

    fun func(pos: Int): Long {
        if(pos == make.length) {
            return 1
        }
        if(dp[pos] != -1L) {
            return dp[pos]
        }
        var ans = 0L
        for(pattern in patterns) {
            if(pos + pattern.length > make.length) {
                continue
            }
            var isPossible = true
            for(i in pattern.indices) {
                if(pattern[i] != make[pos + i]) {
                    isPossible = false
                    break
                }
            }
            if(isPossible) {
                ans += func(pos + pattern.length)
            }
        }
        dp[pos] = ans
        return ans
    }

    fun solve() {
        val fileName = "src/day19/input.txt"
        val lines = File(fileName).readLines().filter {it.isNotBlank()}
//        println(lines)
        for(line in lines) {
            println(line)
        }
        patterns = lines[0].split(", ")
        println(patterns)
        println("${patterns.size} patterns")
        println("${lines.size - 1} makes")
        var ans = 0L
        for(i in 1..< lines.size) {
            make = lines[i]
            dp = MutableList(make.length + 1) {-1L}
            ans += func(0)
//            if(func(0) > 0) {
////                println("possible -> $make")
//                ans++
//            } else {
//                println("not possible -> $make")
//            }
        }
        println(ans)

    }
}

fun main() {
    Second().solve()
}
// 166