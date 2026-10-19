
package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Hospital Management System - Admin Dashboard");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(25, 55, 85));
        header.setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("Hospital Management System");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Administrator Dashboard");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(Color.WHITE);

        JPanel headingPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        headingPanel.setOpaque(false);
        headingPanel.add(title);
        headingPanel.add(subtitle);

        header.add(headingPanel, BorderLayout.WEST);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFocusPainted(false);
        header.add(logoutButton, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Main content
        JPanel content = new JPanel(new BorderLayout(15, 15));
        content.setBackground(new Color(245, 247, 250));
        content.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel welcome = new JLabel("Welcome, Administrator!");
        welcome.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel description = new JLabel(
                "Manage hospital staff, patients, appointments and facilities.");
        description.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel welcomePanel = new JPanel(new GridLayout(2, 1, 0, 8));
        welcomePanel.setOpaque(false);
        welcomePanel.add(welcome);
        welcomePanel.add(description);

        content.add(welcomePanel, BorderLayout.NORTH);

        // Dashboard buttons
        JPanel menuPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        menuPanel.setOpaque(false);
        menuPanel.setBorder(new EmptyBorder(25, 0, 0, 0));

        addDashboardButton(menuPanel, "Register Doctor",
                "Add a new doctor to the hospital");
        addDashboardButton(menuPanel, "View Doctors",
                "View registered doctors");
        addDashboardButton(menuPanel, "View Patients",
                "View registered patients");
        addDashboardButton(menuPanel, "Manage Appointments",
                "View and manage appointments");
        addDashboardButton(menuPanel, "Manage Medicines",
                "Manage medicine inventory");
        addDashboardButton(menuPanel, "Manage Rooms",
                "Manage room availability");

        content.add(menuPanel, BorderLayout.CENTER);
        add(content, BorderLayout.CENTER);

        // Footer
        JLabel footer = new JLabel(
                "Hospital Management System | Admin Panel",
                SwingConstants.CENTER);
        footer.setBorder(new EmptyBorder(10, 10, 15, 10));
        footer.setForeground(Color.GRAY);
        add(footer, BorderLayout.SOUTH);

        // Button actions
        logoutButton.addActionListener(e -> logout());

        setVisible(true);
    }

    private void addDashboardButton(
            JPanel panel, String buttonText, String description) {

        JButton button = new JButton(
                "<html><center><b>" + buttonText
                + "</b><br><br><small>" + description
                + "</small></center></html>");

        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(25, 55, 85));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(e ->
                handleDashboardAction(buttonText));

        panel.add(button);
    }

    private void handleDashboardAction(String action) {

        switch (action) {
            case "Register Doctor":
                JOptionPane.showMessageDialog(
                        this,
                        "Doctor registration will be implemented next.");
                break;

            case "View Doctors":
                JOptionPane.showMessageDialog(
                        this,
                        "Doctor list will be implemented next.");
                break;

            case "View Patients":
                JOptionPane.showMessageDialog(
                        this,
                        "Patient list will be implemented next.");
                break;

            case "Manage Appointments":
                JOptionPane.showMessageDialog(
                        this,
                        "Appointment management will be implemented next.");
                break;

            case "Manage Medicines":
                JOptionPane.showMessageDialog(
                        this,
                        "Medicine management will be implemented next.");
                break;

            case "Manage Rooms":
                JOptionPane.showMessageDialog(
                        this,
                        "Room management will be implemented next.");
                break;

            default:
                JOptionPane.showMessageDialog(
                        this, "Unknown action.");
        }
    }

    private void logout() {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            new LoginFrame().setVisible(true);
        }
    }
}

