@file:Suppress("KotlinConstantConditions")

fun main(args: Array<String>) {
    val s = "5"
    val s2: Any = "5"
    val sint = s.toInt()
    val s2int = s2 as? Int
    println(sint is Int)
    println(s2int is Int)
    println(s as? Int)    // null
    println(s as Int?)    // exception
}