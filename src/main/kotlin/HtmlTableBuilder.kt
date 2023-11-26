class TableBuilder {
    private val rows = mutableListOf<TableRowBuilder>()

    fun row(block: TableRowBuilder.() -> Unit) {
        val rowBuilder = TableRowBuilder()
        rowBuilder.block()
        rows.add(rowBuilder)
    }
    fun build(): String = rows.joinToString(
        prefix = "<table>\n",
        postfix = "\n</table>",
        separator = "\n"
    ) { it.build() }
}

class TableRowBuilder {
    private val cells = mutableListOf<String>()

    fun cell(value: String) {
        cells.add(value)
    }

    fun build(): String = cells.joinToString(
        prefix = "    <tr>\n        <td>",
        postfix = "</td>\n    </tr>",
        separator = "</td>\n        <td>"
    ) { it }
}

fun table(block: TableBuilder.() -> Unit): String {
    val tableBuilder = TableBuilder()
    tableBuilder.block()
    return tableBuilder.build()
}


fun main() {
    val htmlTable = table {
        // the following rows will be applied to the table through the fun row() defined in TableBuilder
        row {
            // the following cells will be applied to the row through the fun cell() defined in TableRowBuilder
            cell("Name")
            cell("Age")
        }
        row {
            cell("John Doe")
            cell("25")
        }
        row {
            cell("Jane Smith")
            cell("30")
        }
    }

    println(htmlTable)
}