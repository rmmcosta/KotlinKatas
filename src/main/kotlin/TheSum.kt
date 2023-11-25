fun sumTwoNumbers(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun decodeMessage(encodedMessage: String): String {
    // Write your code here
    val sb = StringBuilder(encodedMessage.length)
    encodedMessage.forEach {
        when {
            it.isVowel() -> sb.append(it)
            it.isLowerCaseLetter() -> sb.append(it.uppercase())
            else -> Unit
        }
    }
    println(sb.capacity())
    println(sb.length)
    return sb.toString()
}

private fun Char.isVowel(): Boolean = this in "aeiouAEIOU"

private fun Char.isLowerCaseLetter(): Boolean = this in 'a'..'z'

fun main() {
    val encodedMessage = "THe mAtRIX HAs YOu"
    println(decodeMessage(encodedMessage))
}
