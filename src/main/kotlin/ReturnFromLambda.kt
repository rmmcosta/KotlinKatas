fun duplicateNonZero(list: List<Int>): List<Int> {
    return list.flatMap {
        if (it == 0)
            listOf()
        else
            listOf(it, it)
    }
}

fun duplicateAll(list: List<Int>): List<Int> {
    return list.flatMap {
        listOf(it, it)
    }
}

fun duplicateNonZero2(list: List<Int>): List<Int> {
    return list.flatMap {
        if (it == 0) return listOf()
        listOf(it, it)
    }
}

fun main() {
    println(duplicateNonZero2(listOf(3, 0, 5)))//prints nothing
    println(duplicateNonZero2(listOf(3, 5)))//works ok because there are no zeroes, so it won't return a listOf()
    println(duplicateNonZero(listOf(1, 4, 3, 0, 5, 7)))
    println(duplicateAll(listOf(1, 4, 3, 0, 5, 7)))
}