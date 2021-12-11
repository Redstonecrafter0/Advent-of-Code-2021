fun main() {

    operator fun <T> List<List<T>>.get(x: Int, y: Int): T? = getOrNull(y)?.getOrNull(x)

    fun part1(input: List<String>): Int {
        val grid = input.map { it.toCharArray().map { s -> Octopus(s.toString().toInt()) } }
        var flashes = 0
        for (i in 1..100) {
            var changed = true
            for (y in grid) {
                for (x in y) {
                    x.level++
                }
            }
            while (changed) {
                changed = false
                for (y in grid.withIndex()) {
                    for (x in y.value.withIndex()) {
                        if (x.value.level > 9 && !x.value.flashed) {
                            x.value.flashed = true
                            for (ax in (x.index - 1)..(x.index + 1)) {
                                for (ay in (y.index - 1)..(y.index + 1)) {
                                    val r = grid[ax, ay]
                                    if (r != null && !r.flashed) {
                                        r.level++
                                        changed = true
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (y in grid) {
                for (x in y) {
                    if (x.flashed) {
                        x.level = 0
                        x.flashed = false
                        flashes++
                    }
                }
            }
        }
        return flashes
    }

    fun part2(input: List<String>): Int {
        val grid = input.map { it.toCharArray().map { s -> Octopus(s.toString().toInt()) } }
        var step = 0
        while (true) {
            step++
            var changed = true
            for (y in grid) {
                for (x in y) {
                    x.level++
                }
            }
            while (changed) {
                changed = false
                for (y in grid.withIndex()) {
                    for (x in y.value.withIndex()) {
                        if (x.value.level > 9 && !x.value.flashed) {
                            x.value.flashed = true
                            for (ax in (x.index - 1)..(x.index + 1)) {
                                for (ay in (y.index - 1)..(y.index + 1)) {
                                    val r = grid[ax, ay]
                                    if (r != null && !r.flashed) {
                                        r.level++
                                        changed = true
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (grid.all { it.all { o -> o.flashed } }) {
                break
            }
            for (y in grid) {
                for (x in y) {
                    if (x.flashed) {
                        x.level = 0
                        x.flashed = false
                    }
                }
            }
        }
        return step
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    check(part2(testInput) == 195)

    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}

private data class Octopus(var level: Int, var flashed: Boolean = false)
