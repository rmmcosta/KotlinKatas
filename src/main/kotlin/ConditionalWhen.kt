fun main() {
    var result = getYourResult("y")
    println(result)
    result = getYourResult("YES")
    println(result)
    result = getYourResult("No")
    println(result)
    val animals = setOf(Dog(), Cat(), Animal())
    printCustomAnimals(animals, ::getTypeOfAnimal)
}

fun printCustomAnimals(animals: Set<Animal>, getStringAnimal: (a: Animal) -> String) {
    for (a in animals) {
        println(getStringAnimal(a))
    }
}

fun getYourResult(s: String): String = when (s) {
    "y", "yes", "Y", "Yes", "YES" -> "You said Yes. Oh yeah"
    "n", "no", "N", "No", "NO" -> "Oh my Good, you said no"
    else -> "You said nothing at all"
}

fun getTypeOfAnimal(animal: Animal): String = when (animal) {
    is Dog -> "Your are a Dog"
    is Cat -> "Say meow"
    else -> "Your animal"
}

open class Animal

class Dog : Animal()

class Cat : Animal()

