import java.io.File
import java.io.File.separator

fun main() {
    val file = File("src${separator}main${separator}resources${separator}text.txt")
    val text = file.readText()
    val countWords = text.split(' ').count { it.isNotEmpty() }
    println("Number of words: $countWords")
    val file2 = File("src${separator}main${separator}resources${separator}words_with_numbers.txt")
    val lines = file2.readLines()
    val linesWithNumbers = lines.filter { it.contains(Regex("\\d")) }
    println("Lines with numbers: $linesWithNumbers")
    println("Lines with numbers: ${linesWithNumbers.size}")
}