
package dao;

import model.Patient;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public boolean addPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patients "
                + "(firstname, lastname, age, phonenumber, email, "
                + "op_number, disease, blood_group, address) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, patient.getFirstname());
            ps.setString(2, patient.getLastname());
            ps.setInt(3, patient.getAge());
            ps.setString(4, patient.getPhonenumber());
            ps.setString(5, patient.getEmail());
            ps.setString(6, patient.getOpNumber());
            ps.setString(7, patient.getDisease());
            ps.setString(8, patient.getBloodGroup());
            ps.setString(9, patient.getAddress());

            return ps.executeUpdate() > 0;
        }
    }

    public Patient getPatientById(int id) throws SQLException {
        String sql = "SELECT * FROM patients WHERE id = ?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Patient(
                            rs.getInt("id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getInt("age"),
                            rs.getString("phonenumber"),
                            rs.getString("email"),
                            rs.getString("op_number"),
                            rs.getString("disease"),
                            rs.getString("blood_group"),
                            rs.getString("address")
                    );
                }
            }
        }

        return null;
    }

    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("age"),
                        rs.getString("phonenumber"),
                        rs.getString("email"),
                        rs.getString("op_number"),
                        rs.getString("disease"),
                        rs.getString("blood_group"),
                        rs.getString("address")
                );

                patients.add(patient);
            }
        }

        return patients;
    }

    public boolean updatePatient(Patient patient) throws SQLException {
        String sql = "UPDATE patients SET firstname = ?, lastname = ?, "
                + "age = ?, phonenumber = ?, email = ?, op_number = ?, "
                + "disease = ?, blood_group = ?, address = ? WHERE id = ?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, patient.getFirstname());
            ps.setString(2, patient.getLastname());
            ps.setInt(3, patient.getAge());
            ps.setString(4, patient.getPhonenumber());
            ps.setString(5, patient.getEmail());
            ps.setString(6, patient.getOpNumber());
            ps.setString(7, patient.getDisease());
            ps.setString(8, patient.getBloodGroup());
            ps.setString(9, patient.getAddress());
            ps.setInt(10, patient.getId());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean deletePatient(int id) throws SQLException {
        String sql = "DELETE FROM patients WHERE id = ?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
