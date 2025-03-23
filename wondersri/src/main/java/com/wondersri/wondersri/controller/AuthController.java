package com.wondersri.wondersri.controller;
import com.wondersri.wondersri.dto.request.LoginRequestDTO;
import com.wondersri.wondersri.dto.request.RegisterRequestDTO;
import com.wondersri.wondersri.dto.response.LoginResponseDTO;
import com.wondersri.wondersri.dto.response.RegisterResponseDTO;
import com.wondersri.wondersri.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AdminService adminService; // Use interface

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        logger.info("Login request for username: {}", loginRequestDTO.getUsername());
        try {
            String token = adminService.authenticate(loginRequestDTO);
            logger.info("Generated token: {}", token);
            return ResponseEntity.ok(new LoginResponseDTO(token, "Login successful"));
        } catch (IllegalArgumentException e) {
            logger.warn("Login failed: {}", e.getMessage());
            return ResponseEntity.status(401).body(new LoginResponseDTO(null, "Invalid credentials"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        logger.info("Register request for username: {}", registerRequestDTO.getUsername());
        adminService.registerAdmin(registerRequestDTO);
        return ResponseEntity.ok(new RegisterResponseDTO("Admin registered successfully"));
    }
}