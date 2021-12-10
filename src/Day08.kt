fun main() {

    fun part1(input: List<String>): Int {
        return input.sumOf {
            it.split(" | ")[1].split(" ").count { s ->
                val d = s.length
                d == 2 || d == 3 || d == 4 || d == 7
            }
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            val sizes = it.split(" | ").map { s -> s.split(" ").map { d -> d.toSet() } }.flatten().groupBy { s -> s.size }

            val nums = Array(10) { setOf<Char>() }

            nums[1] = sizes[2]!!.first()
            nums[4] = sizes[4]!!.first()
            nums[7] = sizes[3]!!.first()
            nums[8] = sizes[7]!!.first()
            nums[6] = sizes[6]!!.first { s -> nums[8] == nums[1] + s }
            nums[5] = sizes[5]!!.first { s -> nums[8] != nums[6] + s }
            nums[2] = sizes[5]!!.first { s -> nums[8] == nums[5] + s }
            nums[9] = sizes[6]!!.first { s -> nums[8] != nums[4] + s }
            nums[0] = sizes[6]!!.first { s -> nums[8] == nums[5] + s }
            nums[3] = sizes[5]!!.first { s -> s !in nums }

            it.split(" | ")[1].split(" ").map { s -> nums.indexOf(s.toSet()) }.joinToString("").toInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
