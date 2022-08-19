package com.app.rebuild.config;

import com.app.rebuild.domain.auth.User;
import com.app.rebuild.repo.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = userRepository.findByUsername("admin");

        if (user == null) {
            User u = new User();
            u.setUsername("admin");
            u.setPassword(new BCryptPasswordEncoder(12).encode("admin"));
            userRepository.save(u);
        }
    }
}