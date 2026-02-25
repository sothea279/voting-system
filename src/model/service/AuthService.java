package model.service;

import dto.request.LoginRequest;
import dto.request.RegisterRequest;
import dto.response.LoginResponse;
import model.dao.UserDao;
import model.entities.User;
import utils.PasswordHasher;
import utils.ValidationUtil;

import java.util.UUID;

public class AuthService {
    private final TransactionManager tx = new TransactionManager();
    private final UserDao userDao = new UserDao();

    public LoginResponse register(RegisterRequest req) {
        ValidationUtil.requireNotBlank(req.getFullName(), "Full name");
        ValidationUtil.requireEmail(req.getEmail());
        ValidationUtil.requireMinLen(req.getPassword(), 6, "Password");

        return tx.inTransaction(conn -> {
            if (userDao.findByEmail(conn, req.getEmail()).isPresent()) {
                return new LoginResponse(false, "Email already exists", null);
            }

            String hash = PasswordHasher.hash(req.getPassword().toCharArray());

            User u = new User(UUID.randomUUID(), req.getFullName(), req.getEmail(), hash, "VOTER");
            userDao.insert(conn, u);

            return new LoginResponse(true, "Register success", u);
        });
    }

    public LoginResponse login(LoginRequest req) {
        ValidationUtil.requireEmail(req.getEmail());
        ValidationUtil.requireNotBlank(req.getPassword(), "Password");

        return tx.inTransaction(conn -> {
            var opt = userDao.findByEmail(conn, req.getEmail());
            if (opt.isEmpty()) return new LoginResponse(false, "Invalid email or password", null);

            User u = opt.get();
            boolean ok = PasswordHasher.verify(req.getPassword().toCharArray(), u.getPasswordHash());
            if (!ok) return new LoginResponse(false, "Invalid email or password", null);

            return new LoginResponse(true, "Login success", u);
        });
    }
}