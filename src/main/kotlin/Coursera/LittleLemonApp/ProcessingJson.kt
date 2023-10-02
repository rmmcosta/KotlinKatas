package Coursera.LittleLemonApp

import Coursera.LittleLemonApp.JSONExample.RESERVATIONS
import com.google.gson.Gson

object JSONExample {
    const val RESERVATIONS = """
            {
                "date":"2023/10/10",
                "reservations" : [
                   {
                        "tableFor": 2,
                        "time": "18:00",
                        "name": "John Doe"
                   },
                   {
                        "tableFor": 4,
                        "time": "18:00",
                        "name": "Jane Doe"
                   },
                   {
                        "tableFor": 3,
                        "time": "20:00",
                        "name": "Ricardo Costa"
                   }
                ]
            }
        """
}

data class Reservation(
    val tableFor: Int,
    val time: String,
    val name: String
)

data class AllReservations(
    val date: String,
    val reservations: List<Reservation>
)

fun main() {
    val gson = Gson()
    val allReservations: AllReservations = gson.fromJson(RESERVATIONS, AllReservations::class.java)
    allReservations.reservations.forEach {
        println(
            "Reservation for ${it.name} at ${it.time} (${allReservations.date}) for ${it.tableFor} people"
        )
    }

    println(gson.toJson(allReservations))

    val person: Person = gson.fromJson("{\"name\":\"John Doe\"}", Person::class.java)
//    val person1: Person = gson.fromJson("""{"name":"John Doe"}""")

    val randomClass = RandomClass(1, "2", 3.0)
    val (firstValue, secondValue, thirdValue) = randomClass
    println("$firstValue, $secondValue, $thirdValue")
    println("${randomClass.getFirstValue()}, ${randomClass.getSecondValue()}, ${randomClass.getThirdValue()}")

    countItem(
        listOf(
            1, 2, 3, 4, 5, 3, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5
        ), 3
    ).let(::println)
}

data class Person(val name: String)

class RandomClass<T, S, U>(private val value1: T, private val value2: S, private val value3: U) {
    fun getFirstValue(): T = value1
    fun getSecondValue(): S = value2
    fun getThirdValue(): U = value3
    operator fun component1(): T = value1
    operator fun component2(): S = value2
    operator fun component3(): U = value3
}

fun <T> countItem(list: List<T>, item: T): Int = list.count { it == item }