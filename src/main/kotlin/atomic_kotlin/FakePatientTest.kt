package atomic_kotlin

import eq

class FakePatientRepository : PatientRepository {
    var fakePatientList: () -> List<Patient> = { listOf() }

    override fun savePatient(patient: Patient) {
        fakePatientList = { fakePatientList() + patient }
    }

    override fun getPatient(name: String): List<Patient> {
        return fakePatientList().filter { it.name == name }
    }

    override fun getPatient(id: Int): Patient? {
        return fakePatientList().find { it.id == id }
    }

    override fun getAllPatients(): List<Patient> {
        return fakePatientList()
    }

    override fun deletePatient(id: Int) {
        val newFakeList = fakePatientList().filter { it.id != id }
        fakePatientList = {
            newFakeList
        }
    }
}

interface MilkFrother {

    fun getMilk(): String

}

class CoffeeMachine(

    private val milkFrother: MilkFrother

) {

    fun makeCoffee(): String {

        return "Coffee with " + milkFrother.getMilk()

    }

}

class PremiumMilkFrother(

    private val engine: Engine,

    private val filter: Filter,

    private val frothingPrograms: List<FrothingProgram>

) : MilkFrother {

    override fun getMilk(): String {

        return "Very complex frothing logic"

    }

}

interface FrothingProgram {

}

interface Filter {

}

interface Engine {

}

class FakeMilkFrother : MilkFrother {

    var fakeGetMilk:() -> String = { "Hot Milk" }

    override fun getMilk(): String {

        return fakeGetMilk()

    }

}

fun main() {
    val fakePatientRepository = FakePatientRepository()
    val patientService = PatientService(fakePatientRepository)
    val p1 = Patient("Alice", 29)
    val p2 = Patient("Bob", 31)
    val p3 = Patient("Charlie", 33)
    fakePatientRepository.fakePatientList = {
        listOf(
            p1, p2, p3
        )
    }
    patientService.getPatient(p1.id)!!.name eq p1.name
    patientService.getPatient(p2.id)!!.age.toString() eq p2.age.toString()
    patientService.getAllPatients().size eq 3.toString()
    patientService.deletePatient(p2.id)
    patientService.getAllPatients().size eq 2.toString()

    val fakeMilkFrother = FakeMilkFrother()
    val coffeeMachine = CoffeeMachine(fakeMilkFrother)
    coffeeMachine.makeCoffee() eq "Coffee with Hot Milk"
    fakeMilkFrother.fakeGetMilk = { "Cold Milk" }
    coffeeMachine.makeCoffee() eq "Coffee with Cold Milk"
}