package com.app.rebuild.repo.auth;

import com.app.rebuild.domain.auth.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionsRepository extends JpaRepository<Permission, Long> {
}
