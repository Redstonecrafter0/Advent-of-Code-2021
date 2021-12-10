fun main() {

    fun part1(input: List<String>): Int {
        val table = mapOf(
            ')' to 3,
            ']' to 57,
            '}' to 1197,
            '>' to 25137
        )
        return input.sumOf {
            val stack = mutableListOf<Char>()
            var result = 0
            for (i in it) {
                if (i.isOpen) {
                    stack.add(i)
                } else if (stack.last() == i.inverted) {
                    stack.removeLast()
                } else {
                    result = table[i]!!
                    break
                }
            }
            result
        }
    }

    fun part2(input: List<String>): Long {
        val table = mapOf(
            ')' to 1L,
            ']' to 2L,
            '}' to 3L,
            '>' to 4L
        )
        val result = input.map {
            val stack = mutableListOf<Char>()
            var result = 0L
            for (i in it) {
                if (i.isOpen) {
                    stack.add(i)
                } else if (stack.last() == i.inverted) {
                    stack.removeLast()
                } else {
                    result = -1L
                    break
                }
            }
            if (result == -1L) {
                0L
            } else {
                stack.reversed().map { c -> c.inverted }.forEach { c ->
                    result *= 5L
                    result += table[c]!!
                }
                result
            }
        }.sorted().filter { it != 0L }
        return result[result.size / 2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part2(testInput) == 288957L)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}

val Char.isOpen: Boolean
    get() = this == '(' || this == '[' || this == '{' || this == '<'

val Char.inverted: Char
    get() = when (this) {
        '(' -> ')'
        ')' -> '('
        '[' -> ']'
        ']' -> '['
        '{' -> '}'
        '}' -> '{'
        '<' -> '>'
        '>' -> '<'
        else -> ' '
    }
