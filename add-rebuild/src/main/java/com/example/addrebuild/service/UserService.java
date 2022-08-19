package com.example.addrebuild.service;

import com.example.addrebuild.domain.User;
import com.example.addrebuild.model.AuthenticationRequest;
import com.example.addrebuild.model.UserRequestModel;
import com.example.addrebuild.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public List<User> load() {
        return userRepo.findAll();
    }


    public User getByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            user.setPassword("");
        }
        return user;
    }

    public User getById(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            user.setPassword("");
        }

        return user;
    }

    public User add(UserRequestModel user) {
        User savedUser = userRepo.save(User.builder()
                .username(user.username)
                .email(user.email)
                .password(passwordEncoder.encode(user.password))
                .nickname(user.nickname)
                .blocked(user.blocked)
                .active(user.active)
                .IDNumber(user.IDNumber)
                .build());

        return savedUser;
    }

    public User update(UserRequestModel user, Long id) {
        User currentUser = getCurrentUser();
        User u = userRepo.findById(id).get();

        if(u.getId() != currentUser.getId() && !currentUser.isAdmin()) {
            return null;
        }

        u.setUsername(user.username);
        u.setPassword(passwordEncoder.encode(user.password));
        u.setEmail(user.email);
        u.setNickname(user.nickname);
        u.setActive(user.active);
        u.setBlocked(user.blocked);
        u.setIDNumber(user.IDNumber);

        return userRepo.save(u);
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return getByUsername(authentication.getName());
        }
        return null;
    }

    public void login(AuthenticationRequest authRequest) {

        User user = userRepo.findByUsername(authRequest.username);

        if(user == null) {
            throw new RuntimeException( "USER_OR_PASSWORD_IS_INCORRECT");
        }
        if (!passwordEncoder.matches(authRequest.password, user.getPassword())) {
            throw new RuntimeException( "USER_OR_PASSWORD_IS_INCORRECT");
        }
        if (user.isBlocked()) {
            throw new RuntimeException( "USER_IS_BLOCKED");
        }

        authenticateUser(user);
    }

    private User authenticateUser(User user) {
        if (user != null) {
            Authentication auth = new UsernamePasswordAuthenticationToken(user, "", user.getRoles());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        return user;
    }
}
