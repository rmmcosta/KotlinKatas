package atomic_kotlin

class PatientService(private val patientRepository: PatientRepository) {
    fun savePatient(patient: Patient) {
        patientRepository.savePatient(patient)
    }

    fun getPatient(name: String): List<Patient> {
        return patientRepository.getPatient(name)
    }

    fun getPatient(id: Int): Patient? {
        return patientRepository.getPatient(id)
    }

    fun getAllPatients(): List<Patient> {
        return patientRepository.getAllPatients()
    }

    fun deletePatient(id: Int) {
        patientRepository.deletePatient(id)
    }
}