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
}