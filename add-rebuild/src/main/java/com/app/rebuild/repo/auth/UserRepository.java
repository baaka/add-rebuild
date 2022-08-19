package com.app.rebuild.repo.auth;

import com.app.rebuild.domain.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
