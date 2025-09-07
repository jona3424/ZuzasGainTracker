package com.zuzasgaintracker.demo.service;

import com.zuzasgaintracker.demo.dto.*;
import com.zuzasgaintracker.demo.entity.User;
import com.zuzasgaintracker.demo.repository.UserRepository;
import com.zuzasgaintracker.demo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwt;

    public AuthResponse register(RegisterRequest req) {
        if (users.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already registered.");
        }
        User u = new User();
        u.setEmail(req.getEmail().trim().toLowerCase());
        u.setPasswordHash(encoder.encode(req.getPassword()));
        u.setFirstName(req.getFirstName());
        u.setLastName(req.getLastName());
        users.save(u);

        return getAuthResponse(u);
    }

    public AuthResponse login(LoginRequest req) {
        Authentication auth = new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword());
        authManager.authenticate(auth);

        User u = users.findByEmail(req.getEmail().trim().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials."));

        return getAuthResponse(u);
    }

    private AuthResponse getAuthResponse(User u) {
        String access = jwt.generateAccessToken(u, java.util.Map.of("userId", u.getId().toString(), "email", u.getEmail()));
        String refresh = jwt.generateRefreshToken(u);

        return AuthResponse.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .tokenType("Bearer")
                .user(UserDto.builder()
                        .id(u.getId())
                        .email(u.getEmail())
                        .firstName(u.getFirstName())
                        .lastName(u.getLastName())
                        .build())
                .build();
    }

    public AuthResponse refresh(RefreshRequest req) {
        final String token = req.getRefreshToken();
        if (!jwt.isRefreshToken(token) || jwt.isExpired(token)) {
            throw new IllegalArgumentException("Invalid or expired refresh token.");
        }
        String email = jwt.extractUsername(token);
        User u = users.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User no longer exists."));

        return getAuthResponse(u);
    }
}
