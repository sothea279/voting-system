package controller;

import dto.request.LoginRequest;
import dto.request.RegisterRequest;
import dto.response.LoginResponse;
import model.service.AuthService;

public class AuthController {
    private final AuthService authService = new AuthService();

    public LoginResponse register(RegisterRequest req) {
        return authService.register(req);
    }

    public LoginResponse login(LoginRequest req) {
        return authService.login(req);
    }
}