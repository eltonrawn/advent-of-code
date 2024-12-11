package day11

import java.io.File

class First {
    fun solve() {
        val fileName = "src/day11/input.txt"
        val line = File(fileName).readLines().first().split(" ").map { Pair(it, 0) }.toMutableList()
        var idx = 0
        val limit = 25
        while(idx < line.size) {
            if(line[idx].second == limit) break
            val numStr = line[idx].first
            val num = numStr.toLong()
            val numLen = numStr.length
            if(num == 0L) {
                line.add(Pair("1", line[idx].second + 1))
            }
            else if(numLen % 2 == 0) {
                line.add(Pair(numStr.substring(0, (numLen / 2)).toLong().toString(), line[idx].second + 1))
                line.add(Pair(numStr.substring((numLen / 2), numLen).toLong().toString(), line[idx].second + 1))
            } else {
                line.add(Pair((num * 2024L).toString(), line[idx].second + 1))
            }
            idx++
        }

        println("${line.count {it.second == limit}}")

    }
}

fun main() {
    First().solve()
}
// 0 1 2 3