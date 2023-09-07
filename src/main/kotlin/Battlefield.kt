import kotlin.random.Random

data class Cell(val x: Int, val y: Int)

data class Ship(val id: Int) {
    private var positionX: Int = -1
    private var positionY: Int = -1

    fun land(battlefield: Battlefield): Boolean {
        val givenCell = battlefield.placeShip(this)
        return if (givenCell != null) {
            positionY = givenCell.y
            positionX = givenCell.x
            true
        } else {
            false
        }
    }

    fun liftOff() {
        positionY = -1
        positionX = -1
    }

    fun isLanded(): Boolean = positionY != -1 && positionX != -1
    fun isFlying(): Boolean = !isLanded()

    fun getPosition(): Cell = Cell(positionX, positionY)
}

class Battlefield(private val rows: Int = 5, private val columns: Int = 5) {
    private val field = mutableMapOf<Cell, Ship>()
    fun placeShip(ship: Ship): Cell? {
        val freeCells = getFreeCells()
        if (freeCells.isEmpty()) {
            return null
        }
        val futurePosition = if (freeCells.size == 1) 0 else Random.nextInt(0, freeCells.size - 1)
        val futureCell = freeCells[futurePosition]
        field += futureCell to ship
        return futureCell
    }

    fun countFreeCells() = getFreeCells().size

    private fun getFreeCells(): List<Cell> {
        return getCells().filter { !field.containsKey(it) }
    }

    private fun getCells(): List<Cell> {
        val cells = mutableListOf<Cell>()
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                cells += Cell(i, j)
            }
        }
        return cells
    }
}