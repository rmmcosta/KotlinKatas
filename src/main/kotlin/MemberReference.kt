data class MyPerson(val name: String, val age: Int)

fun sendEmail(person: MyPerson) {
    println("Sending email to $person")
}

fun main() {
    val persons = listOf(MyPerson("Ana", 32), MyPerson("Ricardo", 30))
    val action = ::sendEmail
    persons.forEach(action)
}