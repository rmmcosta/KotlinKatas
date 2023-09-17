package atomic_kotlin

class Robot {
    companion object {
        const val MAX_X = 100
        const val MIN_X = 0
        const val MAX_Y = 100
        const val MIN_Y = 0
    }

    private var x = 0
    private var y = 0

    private fun getLoopedCoordinates(coordinate: Int, max: Int): Int {
        val newCoordinate = coordinate % max
        return if (newCoordinate < 0) {
            newCoordinate + max
        } else {
            newCoordinate
        }
    }

    fun right(steps: Int) {
        x = getLoopedCoordinates(x + steps, MAX_X)
    }

    fun left(steps: Int) {
        x = getLoopedCoordinates(x - steps, MAX_X)
    }

    fun down(steps: Int) {
        y = getLoopedCoordinates(y + steps, MAX_Y)
    }

    fun up(steps: Int) {
        y = getLoopedCoordinates(y - steps, MAX_Y)
    }

    fun getLocation(): String = "($x, $y)"

    fun reset() {
        x = 0
        y = 0
    }
}