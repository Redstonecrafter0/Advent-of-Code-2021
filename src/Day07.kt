import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        val data = input.map { it.split(",").map { s -> s.toInt() } }.flatten()
        val min = data.minOf { it }
        val max = data.maxOf { it }
        var lowestcost = Int.MAX_VALUE
        for (i in min..max) {
            val result = data.sumOf { abs(it - i) }
            if (result < lowestcost) {
                lowestcost = result
            }
        }
        return lowestcost
    }

    fun part2(input: List<String>): Int {
        val data = input.map { it.split(",").map { s -> s.toInt() } }.flatten()
        val min = data.minOf { it }
        val max = data.maxOf { it }
        var lowestcost = Int.MAX_VALUE
        for (i in min..max) {
            val result = data.sumOf {
                var dist = abs(it - i)
                var res = 0
                while (dist > 0) {
                    res += dist
                    dist--
                }
                res
            }
            if (result < lowestcost) {
                lowestcost = result
            }
        }
        return lowestcost
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
