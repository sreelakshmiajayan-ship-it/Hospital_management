package service;

import model.Patient;
import java.util.ArrayList;
import java.util.List;

public class PatientService {

    // In-memory storage (can be backed by your teammate's DAO later)
    private List<Patient> patientList = new ArrayList<>();

    /**
     * Registers a new patient after validation.
     */
    public void registerPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient details cannot be null.");
        }
        
        // Optional: check for duplicate ID if needed
        patientList.add(patient);
    }

    /**
     * Finds and returns a patient by their unique ID.
     */
    public Patient findPatientById(String patientId) {
        for (Patient patient : patientList) {
            // Adjust getPatientId() or getId() depending on your Person/Patient model definition
            if (String.valueOf(patient.getId()).equals(patientId)) {
                return patient;
            }
        }
        return null; // Return null if patient not found
    }

    /**
     * Retrieves all registered patients.
     */
    public List<Patient> getAllPatients() {
        return patientList;
    }
}