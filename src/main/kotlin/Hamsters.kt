data class Hamster(val name: String)

class Cage(private val capacity: Int) {
    private val hamsters = mutableListOf<Hamster>()

    val isFull: Boolean
        get() = hamsters.size == capacity

    val isEmpty: Boolean
        get() = hamsters.isEmpty()

    val placesAvailable: Int
        get() = capacity - hamsters.size

    fun put(hamster: Hamster): Boolean =
        when {
            isFull -> false
            hamsters.contains(hamster) -> false
            else -> addHamster(hamster)
        }

    private fun addHamster(hamster: Hamster): Boolean {
        hamsters += hamster
        return true
    }

    fun take(): Hamster? {
        if (isEmpty) {
            return null
        }
        val hamsterToReturn = hamsters[0]
        hamsters -= hamsterToReturn
        return hamsterToReturn

    }
}

fun main() {
    val hamster1 = Hamster("José")
    val hamster2 = Hamster("Maria")
    val hamster3 = Hamster("João")
    val hamster4 = Hamster("Carlos")
    val hamster5 = Hamster("Ricardo")
    val cage1 = Cage(2)
    cage1.isFull eq false
    cage1.isEmpty eq true
    cage1.placesAvailable eq 2
    cage1.put(hamster1) eq true
    cage1.placesAvailable eq 1
    cage1.isFull eq false
    cage1.isEmpty eq false
    cage1.put(hamster2) eq true
    cage1.isFull eq true
    cage1.placesAvailable eq 0
    cage1.put(hamster3) eq false
    cage1.take()?.name?.eq(hamster1.name)
    cage1.isFull eq false
    cage1.put(hamster2) eq false
}

private infix fun Int.eq(otherInt: Int) {
    println(this == otherInt)
}
