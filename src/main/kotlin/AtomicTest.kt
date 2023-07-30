const val ERROR_TAG = "[Error]: "
private fun <L, R> test(
    actual: L, expected: R, checkEquals: Boolean = true, predicate: () -> Boolean
): String = if (!predicate()) "$actual ${if (checkEquals) "!=" else "=="} $expected"
else "predicate true"

/**
 * Compares the string representation
 * of this object with the string `rVal`.
 */
infix fun Any.eq(rVal: String): String = test(this, rVal) {
    toString().trim() == rVal.trimIndent()
}

infix fun Any.neq(rVal: String): String = test(this, rVal, false) {
    toString().trim() != rVal.trimIndent()
}

object trace {
    private val trc = mutableListOf<String>()
    operator fun invoke(obj: Any?) {
        trc += obj.toString()
    }

    /**
     * Compares trc contents to a multiline
     * `String` by ignoring white space.
     */
    infix fun eq(multiline: String) {
        val trace = trc.joinToString("\n")
        val expected = multiline.trimIndent()
            .replace("\n", " ")
        val testResult = test(trace, multiline) {
            trace.replace("\n", " ") == expected
        }
        println(testResult)
        trc.clear()
    }
}