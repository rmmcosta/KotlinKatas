package hyperskill

open class Car(val model: String, val speed: Int)

class Bus(private val typeOfBus: String, model: String, speed: Int) : Car(model, speed) {
    fun printInfo() = println("Type of bus: $typeOfBus, model: $model, speed: $speed")
}

open class Animal
open class Mammal : Animal()
class Lion : Mammal()
open class Bird : Animal()
class Eagle : Bird()
open class Fish : Animal()
class Salmon : Fish()
class Carp : Fish()


/*Inheritance and primary constructor*/
open class Book(
    val title: String,
    val author: String = "Unknown",
    val genre: String = "Unknown",
    val isbn: Long = 0
)

class ExtBook(
    val publisher: String = "Unknown",
    title: String,
    genre: String = "Unknown",
    author: String = "Unknown",
    isbn: Long = 0
) : Book(title, author, genre, isbn)

class NoInfoBook(
    title: String,
    author: String = "Unknown"
) : Book(title, author)

class FictionBook(
    title: String,
    author: String = "Unknown",
    isbn: Long = 0
) : Book(title, author, genre = "fiction", isbn)

/*Inheritance and secondary constructor*/
open class Base(val beta: Int, val gamma: Int, var message: String = "") {
    constructor(beta: Int, message: String = "") : this(beta, 0, message)
}

class Derived(val alpha: Int, beta: Int, gamma: Int, message: String = "") : Base(beta, gamma, message) {
    constructor(alpha: Int, beta: Int, message: String = "") : this(alpha, beta, 0, message)
}

class Derived2 : Base {
    val alpha: Int

    constructor(alphaConstr: Int, beta: Int) : super(beta) {
        alpha = alphaConstr
    }

    constructor(alphaConstr: Int, beta: Int, gamma: Int) : super(beta, gamma) {
        alpha = alphaConstr
    }

    constructor(alphaConstr: Int, beta: Int, gamma: Int, message: String) : super(beta, gamma, message) {
        alpha = alphaConstr
    }

    constructor(alphaConstr: Int, beta: Int, message: String) : super(beta, message = message) {
        alpha = alphaConstr
    }
}

/*Inheritance and init*/
/*
In case a class has a primary constructor, some init blocks, and some secondary constructors, then the order of execution is the following:

The primary constructor, even if a secondary one is called. The primary one is called first through the keyword this;
All init blocks, sequentially in the order they appear;
The second constructor block, in case this constructor was called.
 */

open class Base3(val message: String, val email: String) {
    init { println("Base class init") }
    constructor(email: String) : this("No message", email) { println("Base class secondary") }
}

class Derived3(email: String) : Base3(email) {
    init { println("Derived class init") }
    constructor() : this("example.com") { println("Derived class secondary") }
}

fun main() {
    val bus = Bus("Personal", "N4", 130)
    bus.printInfo()
    Derived3()
}