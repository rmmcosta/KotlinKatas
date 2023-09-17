package hyperskill

interface Animal1 {
    val numberOfLimbs: Int
    fun move()
    fun communicate(): String
    interface Xpto {
        fun cenas() = println("cenas")
    }
}

// Do not change code below.

fun bark(): String {
    return "bark"
}

fun meow(): String {
    return "meow"
}

fun speak(): String {
    return "speak"
}

fun walk() {
    println("walk")
}

fun fly() {
    println("fly")
}

fun swim() {
    println("swim")
}

// Do not change code above.

class Dog(override val numberOfLimbs: Int) : Animal1 {
    override fun move() {
        walk()
    }

    override fun communicate(): String {
        return bark()
    }

    val xpto = object : Animal1.Xpto {
        override fun cenas() {
            println("cenas")
        }
    }
}

fun main() {
    val dog = Dog(4)
    dog.move()
    println(dog.communicate())
    dog.xpto.cenas()
}