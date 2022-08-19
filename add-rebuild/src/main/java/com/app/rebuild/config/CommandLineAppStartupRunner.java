package com.app.rebuild.config;

import com.app.rebuild.domain.auth.User;
import com.app.rebuild.repo.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    private final List<String> initialUsers = Arrays.asList("admin", "user1", "user2", "user3");

    @Override
    public void run(String... args) throws Exception {

        for (String userName : initialUsers) {
            User user = userRepository.findByUsername(userName);
            if (user == null) {
                User u = new User();
                u.setUsername(userName);
                u.setPassword(new BCryptPasswordEncoder(12).encode(userName));
                userRepository.save(u);
            }
        }
    }
}