class Person3(val name: String, val age: Int) {
    fun isOlder(ageLimit: Int): Boolean = age > ageLimit
    fun getAgePredicate() = ::isOlder
}

const val OLD_AGE_REFERENCE: Int = 75

fun main() {
    val zeMaria = Person3("Zé Maria", 78)
    val agePredicate = Person3::isOlder//regular non-bound reference. can be called in any object instance of the class
    println(agePredicate(zeMaria, OLD_AGE_REFERENCE))//we need to pass the object and the age limit
    val alice = Person3("Alice", 25)
    val aliceAgePredicate = alice::isOlder//bound reference. can only be called for the alice object
    println(aliceAgePredicate(OLD_AGE_REFERENCE))//we just need to pass the age limit
}