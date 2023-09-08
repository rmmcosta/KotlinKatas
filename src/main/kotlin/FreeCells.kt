class Cells {
    private val occupiedRows = mutableSetOf<Int>()
    private val occupiedColumns = mutableSetOf<Int>()
    private val ROWS = 5
    private val COLUMNS = 5
    fun occupyCell(row: Int, column: Int): Boolean {
        return if (notValidRowOrColumn(row, column)) {
            false
        } else {
            occupiedRows += row
            occupiedColumns += column
            true
        }
    }

    private fun notValidRowOrColumn(row: Int, column: Int) =
        row < 1 || row > ROWS || column < 1 || column > COLUMNS

    fun printFreeCells() {
        val freeRows = (1..ROWS).filter { !occupiedRows.contains(it) }
        freeRows.forEachIndexed { index, i -> if (index == freeRows.lastIndex) println(i) else print("$i ") }
        val freeColumns = (1..COLUMNS).filter { !occupiedColumns.contains(it) }
        freeColumns.forEachIndexed { index, i -> if (index == freeColumns.lastIndex) println(i) else print("$i ") }
    }
}

fun main() {
    val cells = Cells()
    for (i in 1..3) {
        val coordinates = readln()
        val (x, y) = coordinates.split(" ")
        val result = cells.occupyCell(x.toInt(), y.toInt())
        if (!result) {
            println("Something went wrong. Couldn't occupy cell $x, $y")
        }
    }
    cells.printFreeCells()
}