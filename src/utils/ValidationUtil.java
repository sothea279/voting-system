package utils;

import model.entities.User;

import java.util.regex.Pattern;

public class ValidationUtil {
    private static final Pattern EMAIL = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    public static void requireNotBlank(String v, String field) {
        if (v == null || v.trim().isEmpty()) throw new IllegalArgumentException(field + " is required");
    }

    public static void requireMinLen(String v, int min, String field) {
        requireNotBlank(v, field);
        if (v.length() < min) throw new IllegalArgumentException(field + " must be at least " + min);
    }

    public static void requireEmail(String email) {
        requireNotBlank(email, "Email");
        if (!EMAIL.matcher(email).matches()) throw new IllegalArgumentException("Invalid email format");
    }

    public static void requireNotNull(Object v, String field) {
        if (v == null) throw new IllegalArgumentException(field + " is required");
    }

    public static void requireAdmin(User u) {
        if (u == null || !"ADMIN".equalsIgnoreCase(u.getRole())) {
            throw new SecurityException("Admin only");
        }
    }
}