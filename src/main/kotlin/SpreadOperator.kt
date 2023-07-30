fun main() {
    println(sum(1, 2, 3, 4, 5))
    val arr = arrayOf(20, 30, 50)
    println(sum(*arr.toIntArray()))
}

fun sum(vararg numbers: Int): Int = numbers.sum()
