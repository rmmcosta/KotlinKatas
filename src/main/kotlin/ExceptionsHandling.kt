fun main() {
    val input = readln()
    try {

        val list = input.split(",")
        if (list == listOf("")) {
            throw IllegalArgumentException()
        }
        val numbers = list.map { it.toInt() }
        val average = numbers.average()
        println(average)
        throw ArithmeticException()

    } catch (e: Exception) { // Write the code to handle each exception as recommended
        when(e) {
            is NumberFormatException -> println("Invalid input!")
            is IllegalArgumentException -> println("Cannot calculate average of an empty list!")
            else -> println("An error occurred!")
        }
    }
}
