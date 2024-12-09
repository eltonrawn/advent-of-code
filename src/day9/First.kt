package day9

import java.io.File

class First {
    fun solve() {
        val fileName = "src/day9/input.txt"
        val line = File(fileName).readLines().first()
        val foo = MutableList(0) {0}
//        println(line)
        val str = StringBuilder()
        for(i in 0 ..< line.length) {
            val repeat = line[i].toString().toInt()
//            println("$i $repeat")
            if(i % 2 == 0) {
                val id = i / 2
                for(j in 1 .. repeat) {
                    str.append('X')
                    foo.add(id)
                }
            } else {
                for(j in 1 .. repeat) {
                    str.append('.')
                    foo.add(-1)
                }
            }
        }
        println(str)
        println(str.length)
        var le = 0
        var ri = str.length - 1
        while(le < ri) {
            while(le < str.length && str[le] != '.') {
                le++
            }
            while(ri >= 0 && str[ri] == '.') {
                ri--
            }
            if(!(le < ri)) break
            str[le] = 'X'
            str[ri] = '.'
            foo[le] = foo[ri]
            foo[ri] = -1
        }

        println(str)
        var ans = 0L
        for(i in 0 ..< str.length) {
            val ch = str[i]
            if(ch == '.') continue
            ans += foo[i] * i.toLong()
        }
        println(ans)
    }
}

fun main() {
    First().solve()
}

/*
* 233313312 1414131402
00...111...2...333.44
*
* 1898082704
* 92092395920
* 6461289671426
*
* */