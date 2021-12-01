fun main() {

    fun <T, R> Iterable<T>.mapInContext(func: (T, T) -> R): Iterable<R> {
        val iter = iterator()
        val list = mutableListOf<R>()
        if (!iter.hasNext()) {
            return list
        }
        var current = iter.next()
        if (!iter.hasNext()) {
            return list
        }
        var next = iter.next()
        list.add(func(current, next))
        while (iter.hasNext()) {
            current = next
            next = iter.next()
            list.add(func(current, next))
        }
        return list
    }


    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }.mapInContext { s, s2 -> s2 > s }.count { it }
    }

    fun part2(input: List<String>): Int {
        fun Iterable<Int>.addTogether(length: Int): Iterable<Int> {
            val iter = iterator()
            val tmpList = mutableListOf<Int>()
            val resultList = mutableListOf<Int>()
            while (tmpList.size < length) {
                if (iter.hasNext()) {
                    tmpList.add(iter.next())
                } else {
                    return emptyList()
                }
            }
            resultList.add(tmpList.sum())
            while (iter.hasNext()) {
                tmpList.add(iter.next())
                tmpList.removeFirst()
                resultList.add(tmpList.sum())
            }
            return resultList
        }

        return input.map { it.toInt() }.addTogether(3).mapInContext { s, s2 -> s2 > s }.count { it }
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
