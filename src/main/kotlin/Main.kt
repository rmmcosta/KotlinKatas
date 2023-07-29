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