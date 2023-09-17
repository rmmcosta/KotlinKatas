package hyperskill

import kotlin.math.roundToInt

fun main1() {
    val factory = FactoryWithRoof(3, 2, 23000)
    print(factory.employeesPerFloor())
}

open class Facility(val floors: Byte) {
    fun addFloors(num: Byte): Int = floors + num
}

open class Factory(floors: Byte, val employees: Short, val roof: Byte) : Facility(floors) {
    fun buildRoof(): Int = super.addFloors(roof)
    open fun employeesPerFloor(): Int = employees / floors
}

open class FactoryWithRoof(floors: Byte, roof: Byte, employees: Short) : Factory(floors, employees, roof) {
    override fun employeesPerFloor(): Int = employees / (floors + super.buildRoof())
}

open class Device {
    open fun fullInfo() = println("Device full info")
}

open class InputDevice : Device() {
    //open fun fullInfo() = println("Input device full info")//compile error
}

fun main() {
    val leatherCase = LeatherCase(190, "brown")
    val softCase = SoftCase(120, "yellow")
    val woodCase = WoodCase(230, "orange")
    println(getCaseTax(leatherCase) + getCaseTax(softCase) + getCaseTax(woodCase))
    println(getCaseTax(leatherCase))
    println(getCaseTax(softCase))
    println(getCaseTax(woodCase))
}

open class Case(val cost: Int) {
    fun getFullInfo(): String = "$$cost cost" + getTax()

    open fun getTax(): Int = (cost * 0.25).roundToInt()
}

open class SoftCase(cost: Int, val color: String) : Case(cost) {
    override fun getTax(): Int = super.getTax() + 100
}

open class WoodCase(cost: Int, val color: String) : Case(cost)

open class LeatherCase(cost: Int, color: String) : SoftCase(cost, color) {
    override fun getTax(): Int = (cost * 0.35).roundToInt()
}

fun getCaseTax(case: Case): Int = case.getTax()
