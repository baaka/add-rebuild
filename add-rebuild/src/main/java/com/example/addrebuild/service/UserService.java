package com.example.addrebuild.service;

import com.example.addrebuild.domain.User;
import com.example.addrebuild.model.UserRequestModel;
import com.example.addrebuild.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public List<User> load(int page, int limit) {
        return userRepo.findAll();
    }


    public User getByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if(user!=null) {
            user.setPassword("");
        }
        return user;
    }

    public User getById(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if(user!=null) {
            user.setPassword("");
        }

        return user;
    }

    public User add(UserRequestModel user)  {

        User savedUser = userRepo.save(User.builder()
                .username(user.username)
                .email(user.email)
                .password(passwordEncoder.encode(user.password))
                .nickname(user.nickname)
                .blocked(user.blocked)
                .active(user.active)
                .build());

        return savedUser;
    }

    public User update(UserRequestModel user, Long id) {
        User u = userRepo.findById(id).get();

        u.setUsername(user.username);
        u.setPassword(passwordEncoder.encode(user.password));
        u.setEmail(user.email);
        u.setNickname(user.nickname);
        u.setActive(user.active);
        u.setBlocked(user.blocked);

        return userRepo.save(u);
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getByUsername(username);
    }
}
