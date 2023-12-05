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
    val myFile = File("src${separator}main${separator}resources${separator}MyCases.txt")
    myFile.appendText("\nThe Sign of the Four")
    val nestedFiles: MutableMap<String, Int> = mutableMapOf()
    val nestedDir = File("src${separator}main${separator}resources${separator}basedir")
    nestedDir.list()?.forEach { println(it) }
    nestedDir.walkBottomUp().forEach {
        if (it.isFile) {
            println("${it.name} is a file with extension ${it.extension} with absolute path ${it.absolutePath}")
            nestedFiles[it.absolutePath] = it.absolutePath.count { ch -> ch == '/' }
        }
    }
    //println(nestedFiles)
    val theNested = nestedFiles.maxBy { it.value }
    println("The nested file is ${theNested.key} - ${theNested.key.split('/').last()} with ${theNested.value} nested directories")
    /*
        nestedDir.walkTopDown().forEach {
            if (it.isFile) {
                val extension = it.extension
                if (nestedFiles.containsKey(extension)) {
                    nestedFiles[extension] = nestedFiles[extension]!! + 1
                } else {
                    nestedFiles[extension] = 1
                }
            }
        }
    */
}

//fun howNested(dir:String, )