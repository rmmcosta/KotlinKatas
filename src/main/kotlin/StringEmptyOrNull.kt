fun main(args: Array<String>) {
    val s1: String? = null
    val s2: String? = ""
    s1.isEmptyOrNull() eq true
    s2.isEmptyOrNull() eq true

    val s3 = "   "
    s3.isEmptyOrNull() eq false
}

@Suppress("ReplaceSizeZeroCheckWithIsEmpty")
private fun String?.isEmptyOrNull(): Boolean {
    return this==null || this.length == 0
}

private infix fun Boolean.eq(other: Boolean) {
    println(this==other)
}