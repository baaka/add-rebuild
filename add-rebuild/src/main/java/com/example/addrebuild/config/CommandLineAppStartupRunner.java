package com.example.addrebuild.config;

import com.example.addrebuild.domain.Role;
import com.example.addrebuild.domain.User;
import com.example.addrebuild.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserRepo userRepository;
    @Autowired
    EncriptionConfig encriptionConfig;

    @Override
    public void run(String...args) throws Exception {
        User user=userRepository.findByUsername("admin");

        if(user == null) {
            User admin = User.builder()
                    .username("admin")
                    .password(encriptionConfig.getPasswordEncoder().encode("admin"))
                    .roles(new HashSet<>(Arrays.asList(Role.ADMIN, Role.USER)))
                    .active(true)
                    .blocked(false)
                    .build();
            userRepository.save(admin);
        }
    }
}