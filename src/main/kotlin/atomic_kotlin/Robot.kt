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

    fun right(steps: Int) {
        var newX = (x + steps) % MAX_X
        if (newX < 0) {
            newX += MAX_X
        }
        x = newX
    }

    fun left(steps: Int) {
        var newX = (x - steps) % MAX_X
        if (newX < 0) {
            newX += MAX_X
        }
        x = newX
    }

    fun down(steps: Int) {
        var newY = (y + steps) % MAX_Y
        if (newY < 0) {
            newY += MAX_Y
        }
        y = newY
    }

    fun up(steps: Int) {
        var newY = (y - steps) % MAX_Y
        if (newY < 0) {
            newY += MAX_Y
        }
        y = newY
    }

    fun getLocation(): String = "($x, $y)"

    fun reset() {
        x = 0
        y = 0
    }
}