fun main() {

    fun parse(input: List<String>): List<Pair<String, Int>> = input.map {
        val d = it.split(" ")
        d[0] to d[1].toInt()
    }

    fun part1(input: List<String>): Int {
        var depth = 0
        var hori = 0
        parse(input).forEach {
            when (it.first) {
                "forward" -> hori += it.second
                "down" -> depth += it.second
                "up" -> depth -= it.second
            }
        }
        return depth * hori
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var hori = 0
        var aim = 0
        parse(input).forEach {
            when (it.first) {
                "forward" -> {
                    hori += it.second
                    depth += aim * it.second
                }
                "down" -> aim += it.second
                "up" -> aim -= it.second
            }
        }
        return depth * hori
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
