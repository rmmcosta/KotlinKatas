import java_vs_kotlin.Weather
import java.awt.Color

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val weather = Weather("Unknown", Color.BLACK)
    println(weather)
    weather.updateWeather(25)
    println(weather)

    val cenas: Array<String?> = arrayOfNulls(5)

    println("hello ${cenas.getOrNull(0)}")

    println("calculate foo: ${calculateFoo()}, ${calculateFoo()}")

    //ifs are expressions
    val ifResult = if (true) "cenas" else "coiso"
    println(ifResult)
}

fun calculateFoo(): String {
    println("calculating foo...")
    return "Foo"
}

fun xpto() {
    fun convertNumbersToNumString(text: String): String = text.split(" ").map {
        when (it) {
            "0" -> "zero"
            "1" -> "one"
            "2" -> "two"
            "3" -> "three"
            "4" -> "four"
            "5" -> "five"
            "6" -> "six"
            "7" -> "seven"
            "8" -> "eight"
            "9" -> "nine"
            else -> it
        }
    }.toList().fold("Text:") { acc, curr -> "$acc $curr" }

    println(convertNumbersToNumString("Lorem 3 ipsum 4 dolor 7 sit 8 amet 0"))
//Text: Lorem three ipsum four dolor seven sit eight amet zero
}