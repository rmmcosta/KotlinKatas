package hyperskill

open class Employee(val name: String, val age: Int, var yearsOfWork: Int = 0)

class Programmer(name: String, age: Int, yearsOfWork: Int) : Employee(name, age, yearsOfWork) {
    constructor(name: String, age: Int) : this(name = name, age = age, 0)
}

fun main() {
    Programmer("My Name", 30, 5)  // name, age, yearsOfWork
    Programmer("My Name", 30)     // name, age
}

