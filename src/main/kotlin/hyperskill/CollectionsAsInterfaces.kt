package hyperskill

fun findRepeatedNames(heroes: Collection<String>) = heroes.filter { it.contains("Jon Snow") }

fun uniteBookLists(readBooks: MutableCollection<String>, unreadBooks: Collection<String>): Boolean =
    readBooks.addAll(unreadBooks)

fun main() {
    val heroes = listOf("Jon Snow", "Littlefinger", "Robert I Baratheon", "Jon Snow")

    println(findRepeatedNames(heroes))

    val readBooks = mutableSetOf("The Sound and the Fury", "The Wild Palms")
    val unreadBooks = setOf("Light in August", "As I Lay Dying")
    uniteBookLists(readBooks, unreadBooks)

    val readBooks1 = mutableListOf("The Sound and the Fury", "The Wild Palms")
    val unreadBooks1 = listOf("Light in August", "As I Lay Dying")
    uniteBookLists(readBooks1, unreadBooks1)

    val oldList = "12 34 54 45 345 65 43245 56 34 54".split(" ").toMutableList()
    val oldSet = oldList.toMutableSet()
    val addedList = "100 101 102 103".split(" ").toList()

    addListToCollection(oldList, addedList)
    addListToCollection(oldSet, addedList)

    println(oldList.joinToString(" "))
    println(oldSet.joinToString(" "))

    val nameList =
        "Gabrielle John Mary Brianna Jordan James Caroline Elizabeth Gilbert Morgan".split(" ").toMutableList()
    val nameSet = nameList.toMutableSet()
    val name = "Belinda"

    println(checkElements(nameList, name))
    println(checkElements(nameSet, name))

    val originalList = "8 11 24 65 7890 3565 234 43 67 343 6767 65 3565 1234".split(" ").map { it }.toMutableList()
    val originalSet = originalList.toMutableSet()

    println(addElements(originalList))
    println(addElements(originalSet))

    val foodList = "pancakes eggs waffles sausages eggs bacon vegetables eggs muffins crumpets toast eggs".split(" ")
    val foodSet = foodList.toSet()
    val word = "eggs"

    println(dropElements(foodList, word))
    println(dropElements(foodSet, word))
}

fun dropElements(originalList: Collection<String>, word: String): List<String> =
    originalList.filter { !it.contains(word) }

fun addElements(originalList: Collection<String>): Collection<String> = originalList.reversed()

fun checkElements(nameList: MutableCollection<String>, name: String): Boolean = name in nameList

fun addListToCollection(oldList: MutableCollection<String>, addedList: List<String>) {
    oldList += addedList
}