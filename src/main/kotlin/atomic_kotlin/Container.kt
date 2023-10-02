package atomic_kotlin

interface Selector<T> {
    fun end(): Boolean
    fun current(): T
    fun next()

    fun forEach(action: (T) -> Unit) {
        while (!end()) {
            action(current())
            next()
        }
    }
}

class Container<T>(private val iterable: Iterable<T>) : Iterable<T> {

    override fun iterator() = iterable.iterator()

    fun selector() = object : Selector<T> {
        private val iterator = iterable.iterator()
        private var current: T? = null

        override fun end() = !iterator.hasNext()

        override fun current(): T {
            return current ?: throw IllegalStateException("No current item")
        }

        override fun next() {
            if (iterator.hasNext()) {
                current = iterator.next()
            }
        }
    }
}

enum class Example {
    A, B, C, D
}

inline fun <reified T: Enum<T>> iterator(): Iterator<T> = enumValues<T>().iterator()

fun main() {
    val iterator = iterator<Example>()

    iterator.forEach {
        println(it)
    }
}

