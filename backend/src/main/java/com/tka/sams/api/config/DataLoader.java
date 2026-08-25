package com.tka.sams.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tka.sams.api.entity.User;
import com.tka.sams.api.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (!userRepository.existsById("admin")) {

            User admin = new User();

            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFirstName("System");
            admin.setLastName("Administrator");
            admin.setEmail("admin@test.com");
            admin.setRole("ADMIN");

            userRepository.save(admin);

            System.out.println("======================================");
            System.out.println("Default Admin Created Successfully");
            System.out.println("Username : admin");
            System.out.println("Password : admin123");
            System.out.println("======================================");
        } else {
            System.out.println("Admin already exists.");
        }
    }
}