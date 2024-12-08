package day8

import java.io.File

class Second {
    var n = 0
    var m = 0

    fun isValid(x: Int, y: Int): Boolean {
        if(x < 0 || x >= n || y < 0 || y >= m) {
            return false
        }
        return true
    }


    fun solve() {
        val fileName = "src/day8/input.txt" // Replace with your file name
        val lines = File(fileName).readLines().map {StringBuilder(it)}
        n = lines.size
        m = lines[0].length
//        val mp = mutableMapOf<Char, List<Pair<Int, Int>>>()

        val ara = MutableList(300) {MutableList(0) {Pair(0, 0)} }

        for(i in 0 .. n - 1) {
            for(j in 0 .. m - 1) {
                val ch = lines[i][j]
                if(ch != '.') {
                    ara[ch.code].add(Pair(i, j))
                }
            }
        }

        val set = mutableSetOf<Pair<Int, Int>>()

        for(p in ara.indices) {
            for(i in 0 ..< ara[p].size) {
                for(j in i + 1 ..< ara[p].size) {
                    //
                    var x = ara[p][i]
                    var y = ara[p][j]
                    while(isValid(x.first, x.second)) {
                        set.add(x)
                        var nx = Pair((x.first - (y.first - x.first)), (x.second - (y.second - x.second)))
                        y = x
                        x = nx

                    }
//                    set.add(Pair(
//                        ara[p][i].first - (ara[p][j].first - ara[p][i].first),
//                        ara[p][i].second - (ara[p][j].second - ara[p][i].second)
//                    ))

                    x = ara[p][i]
                    y = ara[p][j]
                    while(isValid(y.first, y.second)) {
                        set.add(y)
                        var ny = Pair((y.first - (x.first - y.first)), (y.second - (x.second - y.second)))
                        x = y
                        y = ny
                    }
//                    set.add(Pair(
//                        ara[p][j].first - (ara[p][i].first - ara[p][j].first),
//                        ara[p][j].second - (ara[p][i].second - ara[p][j].second)
//                    ))
                }

            }
        }
        var ans = 0
        for(pr in set) {
            if(isValid(pr.first, pr.second)) {
                ans++
                if(lines[pr.first][pr.second] == '.') {
                    lines[pr.first][pr.second] = '#'
                }
            }
        }
        for(line in lines) {
            println(line)
        }
        println(ans)
    }
}

fun main() {
    Second().solve()
}

/*
............
........0...
.....0....#.
.......0....
....0.......
.#.....A.....
............
.......#....
........A...
.........A..
..........#.
............
*/