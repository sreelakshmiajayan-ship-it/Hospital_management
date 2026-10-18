package service;

public class AuthService {

    /**
     * Authenticates a user based on username, password, and selected role.
     * Note: This uses sample demo credentials suitable for an academic prototype.
     */
    public boolean authenticate(String username, String password, String role) {
        if (username == null || password == null || role == null) {
            return false;
        }

        // Basic demo role credentials validation
        switch (role.toLowerCase()) {
            case "admin":
                return username.equals("admin") && password.equals("admin123");
            
            case "doctor":
                return username.equals("doctor") && password.equals("doctor123");
            
            case "patient":
                return username.equals("patient") && password.equals("patient123");
            
            default:
                return false;
        }
    }
}