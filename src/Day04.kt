import java.util.concurrent.CopyOnWriteArrayList

class FinishedException(val value: Int): Exception()

fun main() {

    fun parse(input: List<String>): Pair<MutableList<Int>, List<List<MutableList<Pair<Int, Boolean>>>>> {
        val numbers = input.first().split(",").map { it.toInt() }.toMutableList()
        val boards = input
            .subList(2, input.size)
            .joinToString("\n").replace("\n\r", "\n")
            .split("\n\n")
            .map {
                it.split("\n").map { r ->
                    r.split(" ").filter { i -> i != "" }.map { i -> i.toInt() to false }.toMutableList()
                }
            }
        return numbers to boards
    }

    fun result(board: List<MutableList<Pair<Int, Boolean>>>): Int = board.sumOf { innerRow -> innerRow.filter { !it.second }.sumOf { it.first } }

    fun part1(input: List<String>): Int {
        val (numbers, boards) = parse(input)
        var currentNumber = numbers.removeFirst()
        while (true) {
            for (board in boards) {
                for (x in board.indices) {
                    for (y in 0 until board[x].size) {
                        if (board[x][y].first == currentNumber) {
                            board[x][y] = board[x][y].first to true
                        }
                    }
                }
            }
            for (board in boards) {
                for (row in board) {
                    if (row.all { it.second }) return result(board) * currentNumber
                }
                loopY@for (y in 0 until board[0].size) {
                    for (x in board.indices) {
                        if (!board[x][y].second) {
                            continue@loopY
                        }
                    }
                    return result(board) * currentNumber
                }
            }

            currentNumber = numbers.removeFirst()
        }
    }

    fun CopyOnWriteArrayList<List<MutableList<Pair<Int, Boolean>>>>.removeCheck(board: List<MutableList<Pair<Int, Boolean>>>) {
        remove(board)
        if (isEmpty()) {
            throw FinishedException(result(board))
        }
    }

    fun part2(input: List<String>): Int {
        val (numbers, immutableBoards) = parse(input)
        val boards = CopyOnWriteArrayList(immutableBoards)
        var currentNumber = numbers.removeFirst()
        try {
            while (true) {
                for (board in boards) {
                    for (x in board.indices) {
                        for (y in 0 until board[x].size) {
                            if (board[x][y].first == currentNumber) {
                                board[x][y] = board[x][y].first to true
                            }
                        }
                    }
                }
                for (board in boards) {
                    for (row in board) {
                        if (row.all { it.second }) boards.removeCheck(board)
                    }
                    loopY@for (y in 0 until board[0].size) {
                        for (x in board.indices) {
                            if (!board[x][y].second) {
                                continue@loopY
                            }
                        }
                        boards.removeCheck(board)
                    }
                }

                currentNumber = numbers.removeFirst()
            }
        } catch (e: FinishedException) {
            return e.value * currentNumber
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
