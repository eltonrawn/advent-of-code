package day17

import java.io.File

class First {
    var a = 0
    var b = 0
    var c = 0
    var ins = List(0) {0}

    fun getCombo(operand: Int): Int {
        if(operand <= 3) {
            return operand
        }
        if(operand == 4) return a
        if(operand == 5) return b
        if(operand == 6) return c
        // should not reach here
        return 0
    }

    fun solve() {
        val fileName = "src/day17/test2.txt"
        val lines = File(fileName).readLines().filter {it.isNotBlank()}
        a = lines[0].split(": ")[1].toInt()
        b = lines[1].split(": ")[1].toInt()
        c = lines[2].split(": ")[1].toInt()
        ins = lines[3].split(": ")[1].split(",").map {it.toInt()}
        println(lines)
        println("$a $b $c")
        println(ins)
        var idx = 0
        var ans = MutableList(0) {0}
        while(idx < ins.size) {
            // opcode
            val opcode = ins[idx]
            idx++
            if(idx == ins.size) break
            // operand
            val operand = ins[idx]

            if(opcode == 0) {
                var p2 = 1
                for (i in 1 .. getCombo(operand)) {
                    p2 *= 2
                }
                a /= p2
            }
            else if (opcode == 1) {
                b = b xor operand
            }
            else if(opcode == 2) {
                b = getCombo(operand) % 8
            }
            else if(opcode == 3) {
                //b = getCombo(operand) % 8
                if(a != 0) {
                    idx = operand
                    continue
                }
            }
            else if(opcode == 4) {
                b = b xor c
            }
            else if(opcode == 5) {
                ans.add(getCombo(operand) % 8)
            }
            else if(opcode == 6) {
                var p2 = 1
                for (i in 1 .. getCombo(operand)) {
                    p2 *= 2
                }
                b = a / p2
            }
            else if(opcode == 7) {
                var p2 = 1
                for (i in 1 .. getCombo(operand)) {
                    p2 *= 2
                }
                c = a / p2
            }

            idx++
        }
        println(ans.joinToString(","))
    }
}

fun main() {
    First().solve()
}
// 0,3,5,4,3,0