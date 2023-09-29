package Coursera.LittleLemonApp

import java.time.LocalDate

data class Sale(val year: Int, val car: Car)

data class Car(val make: String, val model: String, val price: Int)

object Sales {
    private val sales = listOf(
        Sale(2008, Car("Toyota", "Prius", 24000)),
        Sale(2011, Car("Toyota", "Corolla", 20000)),
        Sale(2010, Car("Toyota", "Camry", 32000)),
        Sale(2019, Car("Honda", "Accord", 30000)),
        Sale(2008, Car("Honda", "Civic", 22000)),
        Sale(2008, Car("Honda", "CRV", 26000)),
        Sale(2008, Car("Ford", "Focus", 20000)),
        Sale(2008, Car("Ford", "Fusion", 22000)),
        Sale(2018, Car("Ford", "Taurus", 32000)),
        Sale(2018, Car("Ford", "Explorer", 41000)),
        Sale(2018, Car("Ford", "Expedition", 52000)),
        Sale(2018, Car("BMW", "3 Series", 43000)),
        Sale(2008, Car("BMW", "5 Series", 54000)),
        Sale(2018, Car("BMW", "7 Series", 74000)),
        Sale(2008, Car("Mercedes", "C Class", 43000)),
        Sale(2018, Car("Mercedes", "E Class", 54000))
    )

    fun getCarModelSoldAfter(year: Int): List<String> =
        sales.filter { sale: Sale -> sale.year >= year }.map { sale -> "${sale.car.model} (${sale.car.make})" }
}

object AddressBook {
    private val employees = listOf(
        LLAPPEmployee("John Doe", LocalDate.of(2012, 1, 1)),
        LLAPPEmployee("Jane Doe", LocalDate.of(2013, 1, 1)),
        LLAPPEmployee("John Smith", LocalDate.of(2014, 1, 1)),
        LLAPPEmployee("Jane Smith", LocalDate.of(2015, 1, 1)),
        LLAPPEmployee("John Johnson", LocalDate.of(2016, 1, 1)),
        LLAPPEmployee("Jane Johnson", LocalDate.of(2017, 1, 1)),
        LLAPPEmployee("John Williams", LocalDate.of(2018, 1, 1)),
    )

    fun printWithNewEmployees(newEmployees: List<LLAPPEmployee>) {
        employees.map { it.copy(isVeteran = true) }
            .fold(newEmployees.toMutableList()) { acc, employee ->
                acc.apply {
                    add(employee)
                }
            }
            .forEach { println("${it.name} (veteran: ${it.isVeteran})") }
    }
}

data class LLAPPEmployee(val name: String, val startDate: LocalDate, val isVeteran: Boolean = false)

fun main() {
    Sales.getCarModelSoldAfter(2010).forEach(::println)
    AddressBook.printWithNewEmployees(listOf(
        LLAPPEmployee("John New", LocalDate.of(2012, 1, 1)),
        LLAPPEmployee("Jane New", LocalDate.of(2013, 1, 1)),
        LLAPPEmployee("Ricardo New", LocalDate.of(2014, 1, 1)),
    ))
}