import kotlin.Exception

fun main() {
    val result = try {
        4 / 0
    } catch (e: Exception) {
        -1
    }
    println(result)
}