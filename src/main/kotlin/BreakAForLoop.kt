fun main() {
    // put your code here
    val text = readln()
    for(c in text) {
        if(c.isDigit()) {
            println(c)
            break
        }
    }
}