package Coursera.LittleLemonApp

fun main() {
    val items =
        listOf(
            OrderItem("Pear", 8.0),
            OrderItem("Apple", 2.0),
            OrderItem("Orange", 4.0),
            //OrderItem("Banana", 4.0),
        )
    println(TaxCalculator.calculateOrderTaxAmount(items))//2.1

    (1..20).forEach { println("$it:" + it * 15.0 / 100) }
}

fun findSolution() {

}