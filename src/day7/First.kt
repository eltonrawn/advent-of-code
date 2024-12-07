package day7

import java.io.File

class First {

    fun func(acc: Long, pos: Int, nums: List<Long>, target: Long): Boolean {
        if(pos == nums.size) {
            if(acc == target) return true
            return false
        }
        if(acc > target) return false
        var ans = false
        // sum
        ans = func(acc + nums[pos], pos + 1, nums, target)
        // multiply
        ans = maxOf(ans, func(acc * nums[pos], pos + 1, nums, target))
        return ans
    }

    fun solve() {
        val fileName = "src/day7/input.txt" // Replace with your file name
        val lines = File(fileName).readLines()
        var ans = 0L
        lines.forEach {line ->
//            println(line)
            val (targetS, numsString) = line.split(": ")
            val target = targetS.toLong()
            val nums = numsString.split(" ").map {it.toLong()}
//            println(target)
//            for(num in nums) {
//                print("$num ")
//            }
//            println()
            if(func(nums[0],1, nums, target)){
                ans += target
            }
        }
        println(ans)
    }
}

fun main() {
    First().solve()
}