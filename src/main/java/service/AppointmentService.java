
package service;

import dao.AppointmentDAO;
import model.Appointment;

import java.sql.SQLException;
import java.util.List;

public class AppointmentService {

    private final AppointmentDAO appointmentDAO = new AppointmentDAO();

    // Book Appointment
    public boolean bookAppointment(Appointment appointment)
            throws SQLException {

        if (appointment == null) {
            throw new IllegalArgumentException(
                    "Appointment details cannot be null.");
        }

        if (appointment.getPatient() == null
                || appointment.getDoctor() == null) {
            throw new IllegalArgumentException(
                    "Patient and doctor are required.");
        }

        appointment.setStatus("Scheduled");

        return appointmentDAO.addAppointment(appointment);
    }

    // Get All Appointments
    public List<Appointment> getAllAppointments()
            throws SQLException {
        return appointmentDAO.getAllAppointments();
    }

    // Get Appointment by ID
    public Appointment getAppointmentById(int appointmentId)
            throws SQLException {

        Appointment appointment =
                appointmentDAO.getAppointmentById(appointmentId);

        if (appointment == null) {
            throw new IllegalArgumentException(
                    "Appointment with ID " + appointmentId + " not found.");
        }

        return appointment;
    }

    // Cancel Appointment
    public boolean cancelAppointment(int appointmentId)
            throws SQLException {

        boolean cancelled =
                appointmentDAO.cancelAppointment(appointmentId);

        if (!cancelled) {
            throw new IllegalArgumentException(
                    "Appointment not found or already cancelled.");
        }

        return true;
    }
}
