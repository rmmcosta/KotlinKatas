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