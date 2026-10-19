package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import model.Admission;
import model.Patient;
import model.Doctor;
import service.AdmissionService;
import service.PatientService;

public class AdmissionForm extends JFrame {
    private JTextField admissionIdField;
    private JTextField patientIdField;
    private JTextField doctorIdField;
    private JTextField doctorNameField;
    private JTextField roomNumberField;
    private JButton submitButton;
    private JButton backButton;

    // Services
    private AdmissionService admissionService = new AdmissionService();
    private PatientService patientService = new PatientService();

    public AdmissionForm() {
        setTitle("Hospital Management System - Admission Form");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header Panel (Matching AdminDashboard style)
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(25, 55, 85));
        header.setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("Hospital Management System");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Patient Admission Management");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(Color.WHITE);

        JPanel headingPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        headingPanel.setOpaque(false);
        headingPanel.add(title);
        headingPanel.add(subtitle);
        header.add(headingPanel, BorderLayout.WEST);

        backButton = new JButton("Close");
        backButton.setFocusPainted(false);
        header.add(backButton, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Main Content Panel
        JPanel content = new JPanel(new BorderLayout(15, 15));
        content.setBackground(new Color(245, 247, 250));
        content.setBorder(new EmptyBorder(30, 40, 30, 40));

        JLabel formTitle = new JLabel("Enter Admission Details");
        formTitle.setFont(new Font("Arial", Font.BOLD, 18));
        formTitle.setForeground(new Color(25, 55, 85));
        content.add(formTitle, BorderLayout.NORTH);

        // Form Fields Grid (5 rows)
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 15, 15));
        formPanel.setOpaque(false);
        formPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        formPanel.add(new JLabel("Admission ID:"));
        admissionIdField = new JTextField();
        formPanel.add(admissionIdField);

        formPanel.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        formPanel.add(patientIdField);

        formPanel.add(new JLabel("Doctor ID:"));
        doctorIdField = new JTextField();
        formPanel.add(doctorIdField);

        formPanel.add(new JLabel("Doctor Name:"));
        doctorNameField = new JTextField();
        formPanel.add(doctorNameField);

        formPanel.add(new JLabel("Room / Ward Number:"));
        roomNumberField = new JTextField();
        formPanel.add(roomNumberField);

        content.add(formPanel, BorderLayout.CENTER);

        // Submit Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        submitButton = new JButton("Admit Patient");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(25, 55, 85));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        buttonPanel.add(submitButton);

        content.add(buttonPanel, BorderLayout.SOUTH);
        add(content, BorderLayout.CENTER);

        // Actions
        backButton.addActionListener(e -> dispose());

        submitButton.addActionListener(e -> {
            try {
                String admissionId = admissionIdField.getText().trim();
                String patientId = patientIdField.getText().trim();
                String doctorId = doctorIdField.getText().trim();
                String doctorName = doctorNameField.getText().trim();
                String roomNumber = roomNumberField.getText().trim();

                if (admissionId.isEmpty() || patientId.isEmpty() || doctorId.isEmpty() || roomNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Fetch Patient safely using findPatientById with integer parsing
                Patient patient = null;
                try {
                    int patientIdInt = Integer.parseInt(patientId);
                    patient = patientService.findPatientById(patientIdInt);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Patient ID must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

if (patient == null) {
    JOptionPane.showMessageDialog(this, "Patient not found with ID: " + patientId, "Error", JOptionPane.ERROR_MESSAGE);
    return;
}

                // Create Doctor object (adjust constructor/setters if your Doctor model differs)
                Doctor doctor = new Doctor();
                // doctor.setDoctorId(doctorId);
                // doctor.setName(doctorName);

                // Create Admission object matching your backend model constructor
                Admission admission = new Admission(admissionId, patient, doctor, roomNumber);
                
                // Call service to save
                boolean success = admissionService.admitPatient(admission);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Patient admitted successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Admission failed to save.", "Database Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}