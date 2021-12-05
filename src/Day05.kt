import kotlin.math.max
import kotlin.math.min

fun main() {

    fun parse(input: List<String>): List<Pair<Pair<Int, Int>, Pair<Int, Int>>> = input.map { l ->
        val (v1, v2) = l.split(" -> ").map { v ->
            val (x, y) = v.split(",").map { it.toInt() }
            x to y
        }
        if (v1.x < v2.x) {
            v1 to v2
        } else {
            v2 to v1
        }
    }

    infix fun Int.towards(i: Int): IntRange = min(this, i)..max(this, i)

    fun part1(input: List<String>): Int {
        val parsed = parse(input)
        val w = parsed.maxOf { listOf(it.first.x, it.second.x).maxOrNull() ?: 0 }
        val h = parsed.maxOf { listOf(it.first.y, it.second.y).maxOrNull() ?: 0 }
        val table = buildList<Array<Int>> {
            for (x in 0..h) {
                this += buildList {
                    for (y in 0..w) {
                        this += 0
                    }
                }.toTypedArray()
            }
        }.toTypedArray()
        for (vec in parsed) {
            if (vec.first.x == vec.second.x || vec.first.y == vec.second.y) {
                for (x in vec.first.x towards vec.second.x) {
                    for (y in vec.first.y towards vec.second.y) {
                        table[y][x]++
                    }
                }
            }
        }
        return table.sumOf { r -> r.count { it >= 2 } }
    }

    fun part2(input: List<String>): Int {
        val parsed = parse(input)
        val w = parsed.maxOf { listOf(it.first.x, it.second.x).maxOrNull() ?: 0 }
        val h = parsed.maxOf { listOf(it.first.y, it.second.y).maxOrNull() ?: 0 }
        val table = buildList<Array<Int>> {
            for (x in 0..h) {
                this += buildList {
                    for (y in 0..w) {
                        this += 0
                    }
                }.toTypedArray()
            }
        }.toTypedArray()
        for (vec in parsed) {
            if (vec.first.x == vec.second.x || vec.first.y == vec.second.y) {
                for (x in vec.first.x towards vec.second.x) {
                    for (y in vec.first.y towards vec.second.y) {
                        table[y][x]++
                    }
                }
            } else {
                val xMin = min(vec.first.x, vec.second.x)
                val xMax = max(vec.first.x, vec.second.x)
                val yMin = min(vec.first.y, vec.second.y)
                val yMax = max(vec.first.y, vec.second.y)
                val yInc = vec.first.y < vec.second.y
                for (i in 0..(xMax - xMin)) {
                    table[if (yInc) yMin + i else yMax - i][xMin + i]++
                }
            }
        }
        return table.sumOf { r -> r.count { it >= 2 } }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

private val Pair<Int, Int>.x: Int
    get() = first

private val Pair<Int, Int>.y: Int
    get() = second
