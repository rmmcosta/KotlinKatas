package atomic_kotlin

interface PatientRepository {
    fun savePatient(patient: Patient)
    fun getPatient(name: String): List<Patient>
    fun getPatient(id: Int): Patient?
    fun getAllPatients(): List<Patient>
    fun deletePatient(id: Int)
}