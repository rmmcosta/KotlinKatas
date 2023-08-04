fun isValidIdentifier(s: String): Boolean {
    fun isValidChar(s: String): Boolean {
        for (c in s) {
            val result = when {
                c.isLetterOrDigit()->true
                c == '_' -> true
                else -> false
            }
            if (!result) {
                return false
            }
        }
        return true
    }
    if (s.isEmpty()) {
        return false
    }
    val firstCharIsLetterOrUnderscore = when {
        s.first().isLetter() -> true
        s.first() == '_' -> true
        else -> false
    }
    if (!firstCharIsLetterOrUnderscore)
        return false
    return isValidChar(s)
}

fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}