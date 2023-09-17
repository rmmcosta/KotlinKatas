package hyperskill

fun main() {
    val arr = IntArray(3) { it * 2 }
    val arr2 = intArrayOf(3, 4, 5, 6)
    val arr3 = floatArrayOf(1f)
    val arr4 = doubleArrayOf(1.0)
    println(arr.joinToString(", "))
    println(arr2.joinToString(", "))
    println(arr3.joinToString(", "))
    println(arr4.joinToString(", "))
}