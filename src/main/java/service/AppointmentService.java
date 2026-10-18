package service;

import model.Appointment;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    // This can later be replaced or backed by your teammate's DAO class
    private List<Appointment> appointmentList = new ArrayList<>();

    /**
     * Books a new appointment after validating details.
     */
    public void bookAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment details cannot be null.");
        }
        
        // Set default status upon booking
        appointment.setStatus("Scheduled");
        appointmentList.add(appointment);
    }

    /**
     * Retrieves all appointments in the system.
     */
    public List<Appointment> getAllAppointments() {
        return appointmentList;
    }

    /**
     * Cancels an existing appointment by its ID.
     */
    public void cancelAppointment(String appointmentId) {
        boolean found = false;
        
        for (Appointment appointment : appointmentList) {
            if (String.valueOf(appointment.getAppointmentId()).equals(appointmentId)) {
                appointment.setStatus("Cancelled");
                found = true;
                break;
            }
        }
        
        if (!found) {
            throw new IllegalArgumentException("Appointment with ID " + appointmentId + " not found.");
        }
    }
}