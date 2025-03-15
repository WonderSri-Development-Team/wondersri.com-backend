package com.wondersri.wondersri.service.Impl;

import com.wondersri.wondersri.config.JwtUtil;
import com.wondersri.wondersri.dto.request.LoginRequestDTO;
import com.wondersri.wondersri.dto.request.RegisterRequestDTO;
import com.wondersri.wondersri.entity.Admin;
import com.wondersri.wondersri.repo.AdminRepository;
import com.wondersri.wondersri.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public Admin registerAdmin(RegisterRequestDTO registerRequestDTO) {
        logger.info("Registering admin with username: {}", registerRequestDTO.getUsername());
        Optional<Admin> existingAdmin = adminRepository.findByUsername(registerRequestDTO.getUsername());
        if (existingAdmin.isPresent()) {
            logger.warn("Username already exists: {}", registerRequestDTO.getUsername());
            throw new IllegalArgumentException("Username already exists: " + registerRequestDTO.getUsername());
        }
        Admin admin = new Admin();
        admin.setUsername(registerRequestDTO.getUsername());
        admin.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        admin.setRole(registerRequestDTO.getRole() != null ? registerRequestDTO.getRole() : "ROLE_ADMIN");
        Admin savedAdmin = adminRepository.save(admin);
        logger.info("Admin registered successfully with ID: {}", savedAdmin.getId());
        return savedAdmin;
    }

    @Override
    public String authenticate(LoginRequestDTO loginRequestDTO) {
        logger.info("Attempting to authenticate user: {}", loginRequestDTO.getUsername());
        Optional<Admin> adminOptional = adminRepository.findByUsername(loginRequestDTO.getUsername());
        if (adminOptional.isEmpty()) {
            logger.warn("Username not found: {}", loginRequestDTO.getUsername());
            throw new IllegalArgumentException("Invalid credentials");
        }
        Admin admin = adminOptional.get();
        if (passwordEncoder.matches(loginRequestDTO.getPassword(), admin.getPassword())) {
            logger.info("Authentication successful for user: {}", loginRequestDTO.getUsername());
            return jwtUtil.generateToken(admin.getUsername(), admin.getRole());
        }
        logger.warn("Password mismatch for user: {}", loginRequestDTO.getUsername());
        throw new IllegalArgumentException("Invalid credentials");
    }
}