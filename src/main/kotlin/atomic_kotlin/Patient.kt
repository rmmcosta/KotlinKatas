package atomic_kotlin

import java.util.UUID

data class Patient(val name: String, val age: Int) {
    val id: Int = UUID.randomUUID().hashCode()
}
