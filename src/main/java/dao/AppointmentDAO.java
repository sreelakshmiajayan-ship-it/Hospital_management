
package dao;

import model.Appointment;
import model.Patient;
import model.Doctor;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public boolean addAppointment(Appointment appointment)
            throws SQLException {

        String sql = "INSERT INTO appointments "
                + "(patient_id, doctor_id, appointment_date, "
                + "appointment_time, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, appointment.getPatient().getId());
            ps.setInt(2, appointment.getDoctor().getId());
            ps.setDate(3, Date.valueOf(
                    LocalDate.parse(appointment.getAppointmentDate())));
            ps.setTime(4, Time.valueOf(
                    LocalTime.parse(appointment.getAppointmentTime())));
            ps.setString(5, appointment.getStatus());

            return ps.executeUpdate() > 0;
        }
    }

    public Appointment getAppointmentById(int id)
            throws SQLException {

        String sql = "SELECT * FROM appointments WHERE appointment_id = ?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapAppointment(rs);
                }
            }
        }

        return null;
    }

    public List<Appointment> getAllAppointments()
            throws SQLException {

        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                appointments.add(mapAppointment(rs));
            }
        }

        return appointments;
    }

    public boolean updateAppointment(Appointment appointment)
            throws SQLException {

        String sql = "UPDATE appointments SET patient_id = ?, "
                + "doctor_id = ?, appointment_date = ?, "
                + "appointment_time = ?, status = ? "
                + "WHERE appointment_id = ?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, appointment.getPatient().getId());
            ps.setInt(2, appointment.getDoctor().getId());
            ps.setDate(3, Date.valueOf(
                    LocalDate.parse(appointment.getAppointmentDate())));
            ps.setTime(4, Time.valueOf(
                    LocalTime.parse(appointment.getAppointmentTime())));
            ps.setString(5, appointment.getStatus());
            ps.setInt(6, appointment.getAppointmentId());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean cancelAppointment(int appointmentId)
            throws SQLException {

        String sql = "UPDATE appointments SET status = 'Cancelled' "
                + "WHERE appointment_id = ?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteAppointment(int appointmentId)
            throws SQLException {

        String sql = "DELETE FROM appointments WHERE appointment_id = ?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);
            return ps.executeUpdate() > 0;
        }
    }

    private Appointment mapAppointment(ResultSet rs)
            throws SQLException {

        PatientDAO patientDAO = new PatientDAO();
        DoctorDAO doctorDAO = new DoctorDAO();

        Patient patient =
                patientDAO.getPatientById(rs.getInt("patient_id"));

        Doctor doctor =
                doctorDAO.getDoctorById(rs.getInt("doctor_id"));

        return new Appointment(
                rs.getInt("appointment_id"),
                patient,
                doctor,
                rs.getDate("appointment_date").toString(),
                rs.getTime("appointment_time").toString(),
                rs.getString("status")
        );
    }
}
