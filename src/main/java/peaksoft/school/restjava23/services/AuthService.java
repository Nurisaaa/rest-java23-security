package peaksoft.school.restjava23.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.school.restjava23.dto.AuthResponse;
import peaksoft.school.restjava23.dto.LoginRequest;
import peaksoft.school.restjava23.dto.RegisterRequest;
import peaksoft.school.restjava23.entities.User;
import peaksoft.school.restjava23.exception.AlreadyExistsException;
import peaksoft.school.restjava23.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getEmail(),
                                loginRequest.getPassword()
                        )
                );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token);
    }

    public String register(RegisterRequest registerRequest) {
        if (userRepository.existsUserByEmail(registerRequest.getEmail())) {
            throw new AlreadyExistsException(
                    "User  already registered with email "+registerRequest.getEmail()
            );
        }

        User user = new User(
                        registerRequest.getEmail(),
                        passwordEncoder.encode(registerRequest.getPassword()),
                        "USER");

        userRepository.save(user);

        return "Registered successful:" + user.getEmail();
    }
}
