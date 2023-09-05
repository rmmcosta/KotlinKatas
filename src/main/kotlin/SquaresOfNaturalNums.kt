import kotlin.math.sqrt

fun produceSquares(n: Int): List<Int> = (1..sqrt(n.toDouble()).toInt()).map { it * it }

fun main() {
    print("Please input an int value: ")
    val value = readln().toIntOrNull()
    val squares = produceSquares(value!!)
    squares.forEach { println(it) }
}