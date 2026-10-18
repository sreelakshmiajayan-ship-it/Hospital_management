package service;

import model.Admission;
import java.util.ArrayList;
import java.util.List;

public class AdmissionService {

    // In-memory list (will be replaced or backed by the DAO later)
    private List<Admission> admissionList = new ArrayList<>();

    /**
     * Admits a patient to a room.
     */
    public void admitPatient(Admission admission) {
        if (admission == null) {
            throw new IllegalArgumentException("Admission details cannot be null.");
        }
        admission.setStatus("Admitted");
        admissionList.add(admission);
    }

    /**
     * Discharges a patient by their admission or patient ID.
     */
    public void dischargePatient(String admissionId) {
        boolean found = false;

        for (Admission admission : admissionList) {
            // Using String.valueOf() just in case the ID is stored as a primitive int in the model
            if (String.valueOf(admission.getAdmissionId()).equals(admissionId)) {
                admission.setStatus("Discharged");
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Admission record with ID " + admissionId + " not found.");
        }
    }

    /**
     * Retrieves all active and past admission records.
     */
    public List<Admission> getAllAdmissions() {
        return admissionList;
    }
}