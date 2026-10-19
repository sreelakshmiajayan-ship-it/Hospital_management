package gui;

import javax.swing.*;
import java.awt.*;

public class PrescriptionForm extends JFrame {
    private JTextField txtPatientId, txtDoctorId, txtDiagnosis, txtMedicines, txtDosage;
    private JButton btnSubmit, btnClear;

    public PrescriptionForm() {
        setTitle("Prescription Entry Form");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel(" Patient ID:"));
        txtPatientId = new JTextField();
        add(txtPatientId);

        add(new JLabel(" Doctor ID:"));
        txtDoctorId = new JTextField();
        add(txtDoctorId);

        add(new JLabel(" Diagnosis:"));
        txtDiagnosis = new JTextField();
        add(txtDiagnosis);

        add(new JLabel(" Medicines:"));
        txtMedicines = new JTextField();
        add(txtMedicines);

        add(new JLabel(" Dosage/Instructions:"));
        txtDosage = new JTextField();
        add(txtDosage);

        btnSubmit = new JButton("Submit");
        btnClear = new JButton("Clear");
        add(btnSubmit);
        add(btnClear);

        btnSubmit.addActionListener(e -> JOptionPane.showMessageDialog(null, "Prescription Saved (UI Ready for DB)"));
        btnClear.addActionListener(e -> {
            txtPatientId.setText(""); txtDoctorId.setText(""); txtDiagnosis.setText(""); txtMedicines.setText(""); txtDosage.setText("");
        });
    }
}