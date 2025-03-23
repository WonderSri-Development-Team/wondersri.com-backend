package com.wondersri.wondersri.config;

import com.wondersri.wondersri.entity.Admin;
import com.wondersri.wondersri.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadData( AdminRepository adminRepository) {
        return args -> {
            if (adminRepository.findByUsername("admin") == null) {
                Admin admin = new Admin();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("password123")); // Encode the password
                admin.setRole("ROLE_ADMIN");
                adminRepository.save(admin);
                System.out.println("Default admin created: username=admin, password=password123");
            }
        };
    }
}