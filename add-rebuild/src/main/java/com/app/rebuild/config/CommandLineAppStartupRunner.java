package com.app.rebuild.config;

import com.app.rebuild.domain.Role;
import com.app.rebuild.domain.User;
import com.app.rebuild.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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