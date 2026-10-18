
package service;

import dao.PatientDAO;
import model.Patient;
import java.sql.SQLException;
import java.util.List;

public class PatientService {

    private final PatientDAO patientDAO = new PatientDAO();

    /**
     * Registers a new patient.
     */
    public boolean registerPatient(Patient patient) throws SQLException {
        if (patient == null) {
            throw new IllegalArgumentException(
                "Patient details cannot be null."
            );
        }

        return patientDAO.addPatient(patient);
    }

    /**
     * Finds a patient by ID.
     */
    public Patient findPatientById(int patientId) throws SQLException {
        if (patientId <= 0) {
            throw new IllegalArgumentException(
                "Invalid patient ID."
            );
        }

        return patientDAO.getPatientById(patientId);
    }

    /**
     * Retrieves all registered patients.
     */
    public List<Patient> getAllPatients() throws SQLException {
        return patientDAO.getAllPatients();
    }
}
