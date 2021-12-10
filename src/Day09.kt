fun main() {

    operator fun List<List<Int>>.get(x: Int, y: Int): Int {
        return try {
            this[y][x]
        } catch (e: IndexOutOfBoundsException) {
            Int.MAX_VALUE
        }
    }

    fun part1(input: List<String>): Int {
        val table = input.map { it.toCharArray().map { c -> c.digitToInt() } }
        var result = 0
        for (y in table.indices) {
            for (x in table[y].indices) {
                val it = table[x, y]
                val top = table[x, y - 1]
                val bottom = table[x, y + 1]
                val left = table[x - 1, y]
                val right = table[x + 1, y]
                if (
                    it < top &&
                    it < bottom &&
                    it < left &&
                    it < right
                ) {
                    result += it + 1
                }
            }
        }
        return result
    }

    fun List<List<Int>>.basin(coords: Pair<Int, Int>, blacklist: MutableList<Pair<Int, Int>> = mutableListOf()): Int {
        if (coords in blacklist) {
            return 0
        }
        blacklist += coords
        val x = coords.first
        val y = coords.second
        val it = this[x, y]
        val top = this[x, y - 1] in (it + 1)..8
        val bottom = this[x, y + 1] in (it + 1)..8
        val left = this[x - 1, y] in (it + 1)..8
        val right = this[x + 1, y] in (it + 1)..8
        var result = 1
        if (top) {
            result += basin(x to y - 1, blacklist)
        }
        if (bottom) {
            result += basin(x to y + 1, blacklist)
        }
        if (left) {
            result += basin(x - 1 to y, blacklist)
        }
        if (right) {
            result += basin(x + 1 to y, blacklist)
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val table = input.map { it.toCharArray().map { c -> c.digitToInt() } }
        val lowPoints = mutableListOf<Pair<Int, Int>>()
        for (y in table.indices) {
            for (x in table[y].indices) {
                val it = table[x, y]
                val top = table[x, y - 1]
                val bottom = table[x, y + 1]
                val left = table[x - 1, y]
                val right = table[x + 1, y]
                if (
                    it < top &&
                    it < bottom &&
                    it < left &&
                    it < right
                ) {
                    lowPoints += x to y
                }
            }
        }
        return lowPoints.map { table.basin(it) }.sorted().reversed().subList(0, 3).let {
            var result = 1
            it.forEach { i -> result *= i }
            result
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part2(testInput) == 1134)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
