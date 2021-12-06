fun main() {

    fun part1(input: List<String>): Int {
        var fishes = input.map { it.split(",").map { s -> s.toInt() } }.flatten().toMutableList()
        for (i in 1..80) {
            fishes = fishes.map { it - 1 }.toMutableList()
            fishes.filter { it < 0 }.forEach { _ -> fishes.add(8) }
            fishes = fishes.map { if (it < 0) 6 else it }.toMutableList()
        }
        return fishes.size
    }

    fun part2(input: List<String>): Long {
        val fishes = mutableMapOf<Int, Long>(
            -1 to 0,
            0 to 0,
            1 to 0,
            2 to 0,
            3 to 0,
            4 to 0,
            5 to 0,
            6 to 0,
            7 to 0,
            8 to 0
        )
        input.map { it.split(",").map { s -> s.toInt() } }.flatten().forEach {
            fishes[it] = fishes[it]!! + 1
        }
        for (i in 1..256) {
            for (j in 0..8) {
                fishes[j - 1] = fishes[j]!!
            }
            fishes[6] = fishes[6]!! + fishes[-1]!!
            fishes[8] = fishes[-1]!!
            fishes[-1] = 0
        }
        return fishes.values.sumOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part2(testInput) == 26984457539)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
