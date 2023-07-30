fun main() {
    changeColor()
    changeColor(blue = 255)
}

fun changeColor(red: Int = 0, green: Int = 0, blue: Int = 0) {
    println("Red=$red, Green=$green, Blue=$blue")
}
