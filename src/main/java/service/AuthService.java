
package service;

import dao.UserDAO;
import model.User;
import java.sql.SQLException;

public class AuthService {

    private final UserDAO userDAO = new UserDAO();

    public boolean authenticate(String username, String password, String role)
            throws SQLException {

        if (username == null || password == null || role == null ||
            username.trim().isEmpty() || password.isEmpty() ||
            role.trim().isEmpty()) {
            return false;
        }

        User user = userDAO.getUserByEmail(username.trim());

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password)
                && user.getRole().equalsIgnoreCase(role.trim());
    }
}