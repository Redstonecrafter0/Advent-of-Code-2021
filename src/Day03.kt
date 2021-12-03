fun main() {

    fun parse(input: List<String>): List<List<Boolean>> = input.map { it.toCharArray().map { c -> c == '1' } }

    fun List<List<Boolean>>.mostCommon(): List<Boolean> {
        val h = this.size
        val w = this[0].size
        val mostCommonBits = mutableListOf<Boolean>()
        for (x in 0 until w) {
            val list = mutableListOf<Boolean>()
            for (y in 0 until h) {
                list += this[y][x]
            }
            val onBits = list.count { it }
            val offBits = list.size - onBits
            mostCommonBits += onBits >= offBits
        }
        return mostCommonBits
    }

    fun List<List<Boolean>>.leastCommon(): List<Boolean> = mostCommon().map { !it }

    fun part1(input: List<String>): Int {
        val mostCommon = parse(input).mostCommon()
        val gamma = Integer.parseInt(mostCommon.map { if (it) '1' else '0' }.toCharArray().concatToString(), 2)
        val epsilon = Integer.parseInt(mostCommon.map { if (it) '0' else '1' }.toCharArray().concatToString(), 2)
        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        val parsed = parse(input)
        val oxRating = run {
            var pos = 0
            var nums = parsed
            while (nums.size > 1 && pos < nums[0].size) {
                val mostCommon = nums.mostCommon()
                nums = nums.filter { it[pos] == mostCommon[pos] }
                pos++
            }
            Integer.parseInt(nums[0].map { if (it) '1' else '0' }.toCharArray().concatToString(), 2)
        }
        val scRating = run {
            var pos = 0
            var nums = parsed
            while (nums.size > 1 && pos < nums[0].size) {
                val leastCommon = nums.leastCommon()
                nums = nums.filter { it[pos] == leastCommon[pos] }
                pos++
            }
            Integer.parseInt(nums[0].map { if (it) '1' else '0' }.toCharArray().concatToString(), 2)
        }
        return oxRating * scRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
