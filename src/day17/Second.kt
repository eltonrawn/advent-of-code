package day17

import java.io.File

class Second {
    var a = 0L
    var b = 0L
    var c = 0L
    var ins = List(0) {0L}

    fun check(aYo: Long) {
        a = aYo
        var idx = 0
        var ans = MutableList(0) {0L}
        while(idx < ins.size) {
            // opcode
            val opcode = ins[idx]
            idx++
            if(idx == ins.size) break
            // operand
            val operand = ins[idx]

            if(opcode == 0L) {
                var p2 = 1
                for (i in 1 .. getCombo(operand)) {
                    p2 *= 2
                }
                a /= p2
            }
            else if (opcode == 1L) {
                b = b xor operand
            }
            else if(opcode == 2L) {
                b = getCombo(operand) % 8
            }
            else if(opcode == 3L) {
                //b = getCombo(operand) % 8
                if(a != 0L) {
                    idx = operand.toInt()
                    continue
                }
            }
            else if(opcode == 4L) {
                b = b xor c
            }
            else if(opcode == 5L) {
                ans.add(getCombo(operand) % 8)
            }
            else if(opcode == 6L) {
                var p2 = 1
                for (i in 1 .. getCombo(operand)) {
                    p2 *= 2
                }
                b = a / p2
            }
            else if(opcode == 7L) {
                var p2 = 1
                for (i in 1 .. getCombo(operand)) {
                    p2 *= 2
                }
                c = a / p2
            }

            idx++
        }
        println(ans)
    }

    fun getCombo(operand: Long): Long {
        if(operand <= 3) {
            return operand
        }
        if(operand == 4L) return a
        if(operand == 5L) return b
        if(operand == 6L) return c
        // should not reach here
        return 0
    }

    fun solve() {
        val fileName = "src/day17/input.txt"
        val lines = File(fileName).readLines().filter {it.isNotBlank()}
        a = lines[0].split(": ")[1].toLong()
        b = lines[1].split(": ")[1].toLong()
        c = lines[2].split(": ")[1].toLong()
        ins = lines[3].split(": ")[1].split(",").map {it.toLong()}
        println(lines)
        println("$a $b $c")
        println(ins)
        var idx = 0
//        var ans = MutableList(0) {0}

//        var ans = mutableListOf(2,4,1,5,7,5,0,3,1,6,4,3,5,5,3,0)

        var p2 = 1L
        for (i in 1 .. 17) {
            p2 *= 8L
            println("$i: $p2")
        }
        println("ins-size: ${ins.size}")
        var tmp = MutableList(0) {0L}

        // just need to run from 8 ** 8 to 8 ** 9 because
        // a gets devided by 8 in each loop until it is 0

        var mx = 0

        for(i in 281474976710656L downTo 35184372088832L) {
//            if(i % 8 != 2L) {
//                continue
//            }
            a = i

            tmp = MutableList(0) {0}
            var isPossible = true
            while(a != 0L) {
                b = a % 8
                b = b xor 5

                p2 = 1
                for (i in 1 .. b) {
                    p2 *= 2
                }
                c = a / p2

                p2 = 1
                for (i in 1 .. 3) {
                    p2 *= 2
                }
                a = a / p2

                b = b xor 6
                b = b xor c
//                println("a: $a")
//                print("${b % 8} ")
                tmp.add(b % 8)
                if(tmp.last() != ins[tmp.size - 1]) {
                    isPossible = false
                    break
                }
                if(tmp.size == 6) {
                    break
                }
            }
//            if(mx < tmp.size) {
//                mx = tmp.size
//                println("mx size: $mx $i ${i % 8}")
//                println(tmp)
//            }
//            break
            if(isPossible) {
                println(i)
                println(tmp)
//                check(i)
//                break
            }
        }

    }
}

fun main() {
    Second().solve()
}
// [2, 1, 7, 0, 1, 4, 5, 3]
/*
* 2 4 -> b = a % 8
* 1 5 -> b = b xor 5
* 7 5 -> c = a / p2 [p2 is 2^b]
* 0 3 -> a = a / p2 [p2 is 2^3]
* 1 6 -> b = b xor 6
* 4 3 -> b = b xor c
* 5 5 -> print(b % 8)
* 3 0 -> start from 0 again
*
* */
// 6,5,7,4,5,7,3,1,0

// 59573664



/*
5 ->  8 4 2 1
      0 1 0 1

0       0 0 0 -> 5
1       0 0 1 -> 4
2       0 1 0 -> 7
3       0 1 1 -> 6
4       1 0 0 -> 1
5       1 0 1 -> 0
6       1 1 0 -> 2
7       1 1 1 -> 2

6 ->  8 4 2 1
      0 1 1 0


[2, 4, 1, 5, 7, 5, 0, 3, 1, 6, 4, 3, 5, 5, 3, 0]
1: 8
2: 64
3: 512
4: 4096
5: 32768
6: 262144
7: 2097152
8: 16777216
9: 134217728
10: 1073741824
11: 8589934592
12: 68719476736
13: 549755813888
14: 4398046511104
15: 35184372088832
16: 281474976710656
17: 2251799813685248
ins-size: 16
mx size: 1 35184372088834 2
[1]
mx size: 2 35184372089218 2
[2, 2]
mx size: 5 35184372091290 2
[2, 4, 1, 5, 3]
mx size: 6 35184372181402 2
[2, 4, 1, 5, 7, 1]
mx size: 7 35184375376282 2
[2, 4, 1, 5, 7, 5, 1]
mx size: 9 35184384182682 2
[2, 4, 1, 5, 7, 5, 0, 3, 3]
mx size: 10 35184416754074 2
[2, 4, 1, 5, 7, 5, 0, 3, 1, 3]
mx size: 11 35188528155034 2
[2, 4, 1, 5, 7, 5, 0, 3, 1, 6, 0]
mx size: 13 35297261291930 2
[2, 4, 1, 5, 7, 5, 0, 3, 1, 6, 4, 3, 2]
mx size: 14 35506355734938 2
[2, 4, 1, 5, 7, 5, 0, 3, 1, 6, 4, 3, 5, 1]



 * */
