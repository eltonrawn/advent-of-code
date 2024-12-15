package day15

import java.io.File

class First {

    val gridSB = MutableList(0) {StringBuilder()}
    val dir = StringBuilder()
    var n = 0
    var m = 0

    val ch = listOf('^', 'v', '<', '>')
    val dx = listOf(-1, 1, 0, 0)
    val dy = listOf(0, 0, -1, 1)

    fun moveBoxes(x: Int, y: Int, d: Int): Boolean {
        var nx = x + dx[d]
        var ny = y + dy[d]
        while(gridSB[nx][ny] != '#') {
//            println("foo1")
            if(gridSB[nx][ny] == '.') {
                break
            }
            nx = nx + dx[d]
            ny = ny + dy[d]
        }
        var fx = nx
        var fy = ny
        if(gridSB[fx][fy] == '#') {
            return false
        }

        nx = x + dx[d]
        ny = y + dy[d]

        while(gridSB[nx][ny] != '#') {
//            println("foo2")
            gridSB[nx][ny] = 'O'
            if(nx == fx && ny == fy) {
                break
            }
            nx = nx + dx[d]
            ny = ny + dy[d]

        }

        nx = x + dx[d]
        ny = y + dy[d]

        gridSB[nx][ny] = '@'
        gridSB[x][y] = '.'
        return true
    }

    tailrec fun func(x: Int, y: Int, d: Int) {
        if(d == dir.length) {return}
//        println("$x $y: $d")

        var nx = x
        var ny = y

        for(i in 0 .. 3) {
            if(dir[d] == ch[i]) {
                // move boxes
                val move = moveBoxes(x, y, i)

                if(move) {
                    nx = x + dx[i]
                    ny = y + dy[i]
                }
            }
        }
        func(nx, ny, d + 1)
    }

    fun printBox() {
        println("grid")
        for(line in gridSB) {
            println(line)
        }
    }

    fun solve() {
        val fileName = "src/day15/input.txt"
        val lines = File(fileName).readLines()

        var idx = 0
        while(lines[idx].isNotBlank()) {
            gridSB.add(StringBuilder(lines[idx]))
            idx++
        }

        printBox()

        for(i in idx + 1 ..< lines.size) {
            dir.append(lines[i])
        }
        println("direction")
        println(dir)
        println(dir.length)
        println("*******************")

        n = gridSB.size
        m = gridSB[0].length

        var fx = 0
        var fy = 0

        for(i in 0 .. n - 1) {
            for(j in 0 .. m - 1) {
                if(gridSB[i][j] == '@') {
                    fx = i
                    fy = j
                }
            }
        }
        func(fx, fy, 0)

        printBox()

        var ans = 0L

        for(i in 0 .. n - 1) {
            for(j in 0 .. m - 1) {
                if(gridSB[i][j] == 'O') {
                    ans += (i * 100L) + j
                }
            }
        }
        println(ans)

    }
}

fun main() {
    First().solve()
}