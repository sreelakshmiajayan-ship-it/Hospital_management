package service;

import model.Doctor;
import java.util.ArrayList;
import java.util.List;

public class AdminService {

    // In-memory list (can be backed by your teammate's DAO later)
    private List<Doctor> doctorList = new ArrayList<>();

    /**
     * Adds a new doctor to the system.
     */
    public void addDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor details cannot be null.");
        }
        doctorList.add(doctor);
    }

    /**
     * Removes a doctor from the system by their ID.
     */
    public void removeDoctor(String doctorId) {
        // Using String.valueOf() to handle potential primitive int IDs safely
        boolean removed = doctorList.removeIf(doctor -> String.valueOf(doctor.getId()).equals(doctorId));
        
        if (!removed) {
            throw new IllegalArgumentException("Doctor with ID " + doctorId + " not found.");
        }
    }

    /**
     * Retrieves all doctors in the system.
     */
    public List<Doctor> getAllDoctors() {
        return doctorList;
    }
}