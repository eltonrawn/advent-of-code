package day15

import java.io.File

class Second {

    val gridSB = MutableList(0) {StringBuilder()}
    val dir = StringBuilder()
    var n = 0
    var m = 0

    val ch = listOf('^', 'v', '<', '>')
    val dx = listOf(-1, 1, 0, 0)
    val dy = listOf(0, 0, -1, 1)



    fun moveCol(x: Int, y: Int, d: Int, isMove: Boolean): Boolean {
        val nx = x + dx[d]

        if(gridSB[x][y] == '@') {
            var isPossible = false
            /**
             * []
             * .@
             * */
            if(gridSB[nx][y] == ']') {
                if(moveCol(nx, y - 1, d, isMove)) {
                    isPossible = true
                }
            }
            /**
             * []
             * @.
             * */
            else if(gridSB[nx][y] == '[') {
                if(moveCol(nx, y, d, isMove)) {
                    isPossible = true
                }
            }
            /**
             * .
             * @
             * */
            else if(gridSB[nx][y] == '.') {
                isPossible = true
            }

            if(isPossible) {
                if(isMove) {
                    // move
                    gridSB[nx][y] = '@'
                    gridSB[x][y] = '.'
                }

                return true
            }
        } else {
            var isPossible = false
            //[]
            /**
             * [][]
             * .[].
             * */
            if(gridSB[nx][y] == ']' && gridSB[nx][y + 1] == '[') {
                if(moveCol(nx, y - 1, d, isMove) && moveCol(nx, y + 1, d, isMove)) {
                    isPossible = true
                }
            }
            /**
             * []..
             * .[].
             * */
            /**
             * [].[
             * .[].
             * */

            // nx, y | nx, y + 1
            else if(gridSB[nx][y] == ']' && gridSB[nx][y + 1] == '.') {
                if(moveCol(nx, y - 1, d, isMove)) {
                    isPossible = true
                }
            }
            /**
             * ..[]
             * .[].
             * */
            /**
             * ].[]
             * .[].
             * */
            else if(gridSB[nx][y] == '.' && gridSB[nx][y + 1] == '[') {
                if(moveCol(nx, y + 1, d, isMove)) {
                    isPossible = true
                }
            }
            /**
             * .[].
             * .[].
             * */
            else if(gridSB[nx][y] == '[') {
                if(moveCol(nx, y, d, isMove)) {
                    isPossible = true
                }
            }
            /**
             * ]..[
             * .[].
             * */
            else if(gridSB[nx][y] == '.' && gridSB[nx][y + 1] == '.') {
                isPossible = true
            }

            if(isPossible) {
                if(isMove) {
                    // move
                    gridSB[nx][y] = '['
                    gridSB[nx][y + 1] = ']'

                    gridSB[x][y] = '.'
                    gridSB[x][y + 1] = '.'
                }
                return true
            }
        }
        return false
    }

    fun moveBoxes(x: Int, y: Int, d: Int): Boolean {
        if(d >= 2) { // moving left or right
            var nx = x + dx[d]
            var ny = y + dy[d]

            var firBracket = '['
            var secBracket = ']'

            if(dy[d] == -1) { // left
                firBracket = ']'
                secBracket = '['
            }

            while(gridSB[nx][ny] != '#') {
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

            if(!(nx == fx && ny == fy)) {
                nx = nx + dx[d]
                ny = ny + dy[d]
            }

            while(!(nx == fx && ny == fy)) {
                gridSB[nx][ny] = firBracket

                nx = nx + dx[d]
                ny = ny + dy[d]

                gridSB[nx][ny] = secBracket
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
        } else { // moving up or down
//            println("$y $y $x")
            val isPossible = moveCol(x, y, d, false)
            if(isPossible) {
                moveCol(x, y, d, true)
            }
            return isPossible
        }
    }

    tailrec fun func(x: Int, y: Int, d: Int) {
        printBox()
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
            val foo = lines[idx].map { ch ->
                var ss = StringBuilder()
                if(ch == '@') {
                    ss.append("@.")
                } else if(ch == 'O') {
                    ss.append("[]")
                } else {
                    ss.append(ch.toString()).append(ch.toString())
                }
                ss
            }
            val foo2 = StringBuilder()
            for(f in foo) {
                foo2.append(f)
            }
            gridSB.add(foo2)
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
                if(gridSB[i][j] == '[') {
                    ans += (i * 100L) + j
                }
            }
        }
        println(ans)

    }
}

fun main() {
    Second().solve()
}
