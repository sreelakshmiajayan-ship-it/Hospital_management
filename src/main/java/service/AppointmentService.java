
package service;

import exception.AppointmentConflictException;
import exception.RoomUnavailableException;
import dao.AppointmentDAO;
import model.Appointment;

import java.sql.SQLException;
import java.util.List;

public class AppointmentService {

    private final AppointmentDAO appointmentDAO = new AppointmentDAO();

    // Book Appointment
    public boolean bookAppointment(Appointment appointment)
            throws SQLException, AppointmentConflictException, RoomUnavailableException {

        if (appointment == null) {
            throw new IllegalArgumentException(
                    "Appointment details cannot be null.");
        }

        if (appointment.getPatient() == null
                || appointment.getDoctor() == null) {
            throw new IllegalArgumentException(
                    "Patient and doctor are required.");
        }
        List<Appointment> existingAppointments = appointmentDAO.getAllAppointments();
        
        for (Appointment existing : existingAppointments) {
            if ("Scheduled".equalsIgnoreCase(existing.getStatus())) {
                boolean sameDateTime = existing.getAppointmentDate().equals(appointment.getAppointmentDate()) && 
                                       existing.getAppointmentTime().equals(appointment.getAppointmentTime());

                if (sameDateTime) {
                    boolean doctorBusy = existing.getDoctor().getId() == appointment.getDoctor().getId();
                    boolean patientBusy = existing.getPatient().getId() == appointment.getPatient().getId();

                    if (doctorBusy || patientBusy) {
                        throw new AppointmentConflictException(
                            "Appointment Conflict: The selected doctor or patient already has an appointment at this date and time."
                        );
                    }
                    if (appointment.getRoom() != null && existing.getRoom() != null) {
                        if (existing.getRoom().getRoomNumber().equalsIgnoreCase(appointment.getRoom().getRoomNumber())) {
                            throw new RoomUnavailableException(
                                "Room Unavailable: Room " + appointment.getRoom().getRoomNumber() + " is already booked for this time slot."
                            );
                        }
                    }
                }
            }
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
