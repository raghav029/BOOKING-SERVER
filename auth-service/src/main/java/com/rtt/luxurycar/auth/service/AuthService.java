package com.rtt.luxurycar.auth.service;

import com.rtt.luxurycar.auth.dto.AuthRequestDto;
import com.rtt.luxurycar.auth.dto.AuthResponseDto;
import com.rtt.luxurycar.auth.dto.SignupRequestDto;
import com.rtt.luxurycar.common.exception.BusinessException;
import com.rtt.luxurycar.common.security.JwtUtil;
import com.rtt.luxurycar.user.model.User;
import com.rtt.luxurycar.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void signup(SignupRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("EMAIL_EXISTS", "Email already registered");
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setCity(dto.getCity());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }

    public AuthResponseDto login(AuthRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new BusinessException("INVALID_CRED", "Invalid credentials"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {
            throw new BusinessException("INVALID_CRED", "Invalid credentials");
        }

        String token = jwtUtil.generateToken(
                user.getId().toString(),
                Map.of("role", user.getRole(), "email", user.getEmail())
        );
        return new AuthResponseDto(token);
    }
}
