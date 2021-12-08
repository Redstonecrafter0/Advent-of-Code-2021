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
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
