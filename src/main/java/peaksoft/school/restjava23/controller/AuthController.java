package peaksoft.school.restjava23.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.school.restjava23.dto.AuthResponse;
import peaksoft.school.restjava23.dto.LoginRequest;
import peaksoft.school.restjava23.dto.RegisterRequest;
import peaksoft.school.restjava23.services.AuthService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    @PostMapping("/sign-in")
    public AuthResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/sign-up")
    public String register(@RequestBody RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }
}
