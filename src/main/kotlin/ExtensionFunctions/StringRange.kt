package ExtensionFunctions

import kotlin.text.StringBuilder

/*operator fun ClosedRange<String>.iterator() = object : Iterator<String> {
    private val next = StringBuilder(start)
    private val last = endInclusive

    override fun hasNext(): Boolean = next.toString() <= last && next.length <= last.length

    override fun next(): String {
        //the current one must be returned, but before that we must change the
        //string builder to accommodate the next iteration
        val result: String = next.toString()
        val lastChar = next.last()
        if (lastChar < Char.MAX_VALUE) {
            val nextChar = lastChar + 1
            val lastIndex = next.length - 1
            next.setCharAt(lastIndex, nextChar)
        } else {
            next.append(Char.MIN_VALUE)
        }
        return result
    }
}*/

/*operator fun ClosedRange<String>.iterator() = object : Iterator<String> {

    private val next: StringBuilder = StringBuilder(start)
    private val last: String = endInclusive

    override fun hasNext(): Boolean = next.toString() <= last && next.length <= last.length

    override fun next(): String {
        //the next is the one we want to return now
        val result: String = next.toString()
        //and we want to compute the next one to be returned
        for ((index, c) in result.withIndex()) {
            if (c < last[index]) {
                next.setCharAt(index, c + 1)
                break
            } else {
                if (index == result.lastIndex) {
                    next.append(Char.MIN_VALUE)
                }
            }
        }
        return result
    }
}*/

operator fun ClosedRange<String>.iterator() = object : Iterator<String> {
    private val next: StringBuilder = StringBuilder(start)
    private val last: String = endInclusive

    override fun hasNext(): Boolean = next.toString() <= last && next.length <= last.length

    override fun next(): String {
        val result = next.toString()
        incrementNext()
        return result
    }

    private fun incrementNext() {
        for (i in next.indices) {
            val currentChar = next[i]
            if (currentChar < (last.getOrNull(i) ?: Char.MIN_VALUE)) {
                next[i] = currentChar + 1
                break
            } else if (i == next.lastIndex) {
                next.append(Char.MIN_VALUE)
            }
        }
    }
}


fun main() {
    for (i in "hell".."help") {
        println(i)
    }

    for (i in "heal".."help") {
        println(i)
    }

    for (i in 'A'..'C') {
        println(i)
    }

    for (i in 1..10) {
        println(i)
    }

    for (i in "A".."Z") {
        println(i)
    }

    for (i in "ABC".."CBC") {
        println(i)
    }

}