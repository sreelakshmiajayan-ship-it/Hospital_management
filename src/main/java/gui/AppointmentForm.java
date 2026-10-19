package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import model.Appointment;
import model.Patient;
import model.Doctor;
import service.AppointmentService;
import service.PatientService;
import exception.AppointmentConflictException;
import exception.RoomUnavailableException;

public class AppointmentForm extends JFrame {
    private JTextField appointmentIdField;
    private JTextField patientIdField;
    private JTextField doctorIdField;
    private JTextField doctorNameField;
    private JTextField dateField;
    private JTextField timeField;
    private JButton submitButton;
    private JButton backButton;

    // Services
    private AppointmentService appointmentService = new AppointmentService();
    private PatientService patientService = new PatientService();

    public AppointmentForm() {
        setTitle("Hospital Management System - Appointment Booking");
        setSize(850, 650);
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

        JLabel subtitle = new JLabel("Appointment Management");
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

        JLabel formTitle = new JLabel("Enter Appointment Details");
        formTitle.setFont(new Font("Arial", Font.BOLD, 18));
        formTitle.setForeground(new Color(25, 55, 85));
        content.add(formTitle, BorderLayout.NORTH);

        // Form Fields Grid (6 rows)
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 15, 15));
        formPanel.setOpaque(false);
        formPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        formPanel.add(new JLabel("Appointment ID:"));
        appointmentIdField = new JTextField();
        formPanel.add(appointmentIdField);

        formPanel.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        formPanel.add(patientIdField);

        formPanel.add(new JLabel("Doctor ID:"));
        doctorIdField = new JTextField();
        formPanel.add(doctorIdField);

        formPanel.add(new JLabel("Doctor Name:"));
        doctorNameField = new JTextField();
        formPanel.add(doctorNameField);

        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        formPanel.add(dateField);

        formPanel.add(new JLabel("Time (HH:MM):"));
        timeField = new JTextField();
        formPanel.add(timeField);

        content.add(formPanel, BorderLayout.CENTER);

        // Submit Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        submitButton = new JButton("Book Appointment");
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
                String appointmentIdText = appointmentIdField.getText().trim();
                String patientIdText = patientIdField.getText().trim();
                String doctorIdText = doctorIdField.getText().trim();
                String doctorName = doctorNameField.getText().trim();
                String date = dateField.getText().trim();
                String time = timeField.getText().trim();

                if (appointmentIdText.isEmpty() || patientIdText.isEmpty() || doctorIdText.isEmpty() || date.isEmpty() || time.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int appointmentId = Integer.parseInt(appointmentIdText);
                int patientIdInt = Integer.parseInt(patientIdText);
                int doctorIdInt = Integer.parseInt(doctorIdText);

                // Fetch Patient safely using your PatientService
                Patient patient = patientService.findPatientById(patientIdInt);
                if (patient == null) {
                    JOptionPane.showMessageDialog(this, "Patient not found with ID: " + patientIdText, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create Doctor object (assuming your Doctor model has these setters or constructors)
                Doctor doctor = new Doctor();
                // doctor.setId(doctorIdInt);
                // doctor.setFirstname(doctorName);

                // Create Appointment using your multi-argument constructor
                Appointment appointment = new Appointment(appointmentId, patient, doctor, date, time, "Scheduled");

                // Call service to book (handles conflicts and room exceptions automatically)
                boolean success = appointmentService.bookAppointment(appointment);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Appointment booked successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to book appointment.", "Database Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Appointment ID, Patient ID, and Doctor ID must be valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (AppointmentConflictException | RoomUnavailableException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Scheduling Conflict", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}