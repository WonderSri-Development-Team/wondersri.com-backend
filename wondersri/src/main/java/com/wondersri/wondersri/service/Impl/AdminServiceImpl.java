package com.wondersri.wondersri.service.Impl;

import com.wondersri.wondersri.entity.Admin;
import com.wondersri.wondersri.repo.AdminRepository;
import com.wondersri.wondersri.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Admin registerAdmin(Admin admin) {
        // Check if username already exists
        Optional<Admin> existingAdmin = adminRepository.findByUsername(admin.getUsername());
        if (existingAdmin.isPresent()) {
            throw new IllegalArgumentException("Username already exists: " + admin.getUsername());
        }

        // Encrypt the password before saving
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        // Set default role if not provided
        if (admin.getRole() == null) {
            admin.setRole("ROLE_ADMIN");
        }

        return adminRepository.save(admin);
    }

    @Override
    public Admin authenticate(String username, String password) {
        // Find admin by username
        Optional<Admin> adminOptional = adminRepository.findByUsername(username);
        if (adminOptional.isEmpty()) {
            return null; // Username not found
        }

        Admin admin = adminOptional.get();
        // Verify password
        if (passwordEncoder.matches(password, admin.getPassword())) {
            return admin; // Authentication successful
        }
        return null; // Password incorrect
    }
}