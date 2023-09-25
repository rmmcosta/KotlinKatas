package atomic_kotlin

class PatientRepositoryXHis : PatientRepository {
    private val patients = mutableListOf<Patient>()

    override fun savePatient(patient: Patient) {
        patients.add(patient)
    }

    override fun getPatient(name: String): List<Patient> {
        return patients.filter { it.name == name }
    }

    override fun getPatient(id: Int): Patient? {
        return patients.find { it.id == id }
    }

    override fun getAllPatients(): List<Patient> {
        return patients.toList()
    }

    override fun deletePatient(id: Int) {
        patients.removeIf { it.id == id }
    }
}