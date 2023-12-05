import java.io.File
import java.io.File.separator

fun main() {
    countWords(File("src${separator}main${separator}resources${separator}text.txt"))
    readLinesExample(File("src${separator}main${separator}resources${separator}words_with_numbers.txt"))
    appendTextToFile(File("src${separator}main${separator}resources${separator}MyCases.txt"), "\nThe Sign of the Four")
    findTheMostNestedFile(File("src${separator}main${separator}resources${separator}basedir"))
    val emptySubDirs = getEmptySubDirs(File("src${separator}main${separator}resources${separator}basedir2"))
    printDirsNames("Empty subdirectories", emptySubDirs)
}

private fun countWords(file: File) {
    val text = file.readText()
    val countWords = text.split(' ').count { it.isNotEmpty() }
    println("Number of words: $countWords")
}

private fun readLinesExample(file: File) {
    val lines = file.readLines()
    val linesWithNumbers = lines.filter { it.contains(Regex("\\d")) }
    println("Lines with numbers: $linesWithNumbers")
    println("Lines with numbers: ${linesWithNumbers.size}")
}

private fun appendTextToFile(file: File, s: String) {
    file.appendText(s)
}

private fun findTheMostNestedFile(file: File) {
    val nestedFiles: MutableMap<String, Int> = mutableMapOf()
    file.list()?.forEach { println(it) }
    file.walkBottomUp().forEach {
        if (it.isFile) {
            println("${it.name} is a file with extension ${it.extension} with absolute path ${it.absolutePath}")
            nestedFiles[it.absolutePath] = it.absolutePath.count { ch -> ch == '/' }
        }
    }
    //println(nestedFiles)
    val theNested = nestedFiles.maxBy { it.value }
    println(
        "The nested file is ${theNested.key} - ${
            theNested.key.split('/').last()
        } with ${theNested.value} nested directories"
    )
}

private fun getEmptySubDirs(dir: File): List<File> {
    return dir.walkTopDown().filter { it.isDirectory && it.listFiles()?.isEmpty() == true }.toList()
}

private fun printDirsNames(initialMsg:String, dirs: List<File>) {
    print("$initialMsg: ")
    dirs.forEach { print(it.name.split('/').last() + " ") }
}