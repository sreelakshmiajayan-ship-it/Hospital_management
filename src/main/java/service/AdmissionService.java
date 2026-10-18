
package service;

import dao.AdmissionDAO;
import model.Admission;

import java.sql.SQLException;
import java.util.List;

public class AdmissionService {

    private final AdmissionDAO admissionDAO = new AdmissionDAO();

    // Admit Patient
    public boolean admitPatient(Admission admission)
            throws SQLException {

        if (admission == null) {
            throw new IllegalArgumentException(
                    "Admission details cannot be null.");
        }

        if (admission.getPatient() == null
                || admission.getDoctor() == null) {
            throw new IllegalArgumentException(
                    "Patient and doctor details are required.");
        }

        admission.setStatus("Active");

        return admissionDAO.addAdmission(admission);
    }

    // Discharge Patient
    public boolean dischargePatient(String admissionId)
            throws SQLException {

        if (admissionId == null || admissionId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Admission ID cannot be empty.");
        }

        boolean discharged =
                admissionDAO.dischargeAdmission(admissionId);

        if (!discharged) {
            throw new IllegalArgumentException(
                    "Admission not found or patient is already discharged.");
        }

        return true;
    }

    // Get All Admissions
    public List<Admission> getAllAdmissions() throws SQLException {
        return admissionDAO.getAllAdmissions();
    }

    // Get Admission by ID
    public Admission getAdmissionById(String admissionId)
            throws SQLException {

        if (admissionId == null || admissionId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Admission ID cannot be empty.");
        }

        return admissionDAO.getAdmissionById(admissionId);
    }
}
