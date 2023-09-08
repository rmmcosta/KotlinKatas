interface Animal1

class Dog1(val name: String) : Animal1 {
    fun retrieve(item: String) {
        println("Retrieving $item")
    }
}

class Cat1(private val name: String) : Animal1 {
    fun pet() {
        println("$name Mrr")
    }
}

fun play(animal: Animal1) {
    /*val dog: Dog1 =
        animal as Dog1 // Do that ONLY when you know an object is of the type you are casting to
    dog.retrieve("stick")*/
    if (animal is Dog1) {
        animal.retrieve("stick")
    } else {
        println("I do not know how to play with this animal")
    }
    when (animal) {
        is Dog1 -> animal.retrieve("ball")
        is Cat1 -> animal.pet()
    }
}

fun main() {
    println("Dog")
    play(Dog1("Rex")) // Retrieving stick
    println("Cat")
    play(Cat1("Garfield")) // ERROR! if we don't check in the play if the animal is a dog before retrieve
}