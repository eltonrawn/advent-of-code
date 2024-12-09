package day9

import java.io.File

class Second {
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
        for(i in 0 ..< str.length) {
            val ch = str[i]
            if(ch == '.') print('.')
            else print(foo[i])
        }
        println()
        println(str.length)

        var ri = str.length - 1
        while(ri >= 0) {
            if(str[ri] == '.') {
                ri--
                continue
            }
            val initRi = ri
            while(ri >= 0 && foo[ri] == foo[initRi]) {
                ri--
            }
            ri++
            // ri .... initRi


            var le = 0
            while(le < ri) {
                if(str[le] != '.') {
                    le++
                    continue
                }
                val initLe = le
                while(le < ri && str[le] == '.') {
                    le++
                }
                le--
                println("ri le: $ri $le")
                // initLe .... le
                if(initRi - ri <= le - initLe) {
                    // move
                    for(i in 0 .. (initRi - ri)) {
                        foo[initLe + i] = foo[ri + i]
                        str[initLe + i] = 'X'
                        str[ri + i] = '.'
                    }
                    break
                } else {
                    le++
                    continue
                }
            }
            ri--
        }

        println(str)
        for(i in 0 ..< str.length) {
            val ch = str[i]
            if(ch == '.') print('.')
            else print(foo[i])
        }
        println()


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
    Second().solve()
}

/*
* 233313312 1414131402
* 00...111...2...333.44
*
*
* */