package day11

import java.io.File

class Second {

    val limit = 75

    val dp = mutableMapOf<Pair<String, Int>, Long>()
    var cnt = 0

    fun dfs(numStr: String, level: Int): Long {
        if(level == limit) {
            return 1
        }
        if(dp[Pair(numStr, level)] != null) {
            cnt++
            return dp[Pair(numStr, level)]!!
        }
        val num = numStr.toLong()
        val numLen = numStr.length
        var ans = 0L
        if(num == 0L) {
            ans += dfs("1", level + 1)
        }
        else if(numLen % 2 == 0) {
            ans += dfs(numStr.substring(0, (numLen / 2)).toLong().toString(), level + 1)
            ans += dfs(numStr.substring((numLen / 2), numLen).toLong().toString(), level + 1)
        } else {
            ans += dfs((num * 2024L).toString(), level + 1)
        }
        dp[Pair(numStr, level)] = ans
        return ans
    }

    fun solve() {
        val fileName = "src/day11/input.txt"
        val line = File(fileName).readLines().first().split(" ")

        var ans = 0L
        for(num in line) {
            ans += dfs(num, 0)
        }
        println(ans)

    }
}

fun main() {
    Second().solve()
}
// 0 1 2 3
// 184927