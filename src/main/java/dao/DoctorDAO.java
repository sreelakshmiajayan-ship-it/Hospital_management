
package dao;

import model.Doctor;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public boolean addDoctor(Doctor doctor) throws SQLException {
        String sql = "INSERT INTO doctors "
                + "(firstname, lastname, age, phonenumber, email, "
                + "specialization, qualification, consultation_fee) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, doctor.getFirstname());
            ps.setString(2, doctor.getLastname());
            ps.setInt(3, doctor.getAge());
            ps.setString(4, doctor.getPhonenumber());
            ps.setString(5, doctor.getEmail());
            ps.setString(6, doctor.getSpecialization());
            ps.setString(7, doctor.getQualification());
            ps.setDouble(8, doctor.getConsultationFee());

            return ps.executeUpdate() > 0;
        }
    }

    public Doctor getDoctorById(int id) throws SQLException {
        String sql = "SELECT * FROM doctors WHERE id = ?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Doctor(
                            rs.getInt("id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getInt("age"),
                            rs.getString("phonenumber"),
                            rs.getString("email"),
                            rs.getString("specialization"),
                            rs.getString("qualification"),
                            rs.getDouble("consultation_fee")
                    );
                }
            }
        }

        return null;
    }

    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Doctor doctor = new Doctor(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("age"),
                        rs.getString("phonenumber"),
                        rs.getString("email"),
                        rs.getString("specialization"),
                        rs.getString("qualification"),
                        rs.getDouble("consultation_fee")
                );

                doctors.add(doctor);
            }
        }

        return doctors;
    }

    public boolean updateDoctor(Doctor doctor) throws SQLException {
        String sql = "UPDATE doctors SET firstname = ?, lastname = ?, "
                + "age = ?, phonenumber = ?, email = ?, specialization = ?, "
                + "qualification = ?, consultation_fee = ? WHERE id = ?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, doctor.getFirstname());
            ps.setString(2, doctor.getLastname());
            ps.setInt(3, doctor.getAge());
            ps.setString(4, doctor.getPhonenumber());
            ps.setString(5, doctor.getEmail());
            ps.setString(6, doctor.getSpecialization());
            ps.setString(7, doctor.getQualification());
            ps.setDouble(8, doctor.getConsultationFee());
            ps.setInt(9, doctor.getId());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteDoctor(int id) throws SQLException {
        String sql = "DELETE FROM doctors WHERE id = ?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
