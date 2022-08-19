package com.app.rebuild.service;

import com.app.rebuild.domain.auth.Permission;
import com.app.rebuild.model.PermissionModel;
import com.app.rebuild.model.helper.PermissionModelHelper;
import com.app.rebuild.repo.auth.PermissionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionsService {

    private final PermissionsRepository permissionsRepository;

    public PermissionsService(PermissionsRepository permissionsRepository) {
        this.permissionsRepository = permissionsRepository;
    }

    public List<PermissionModel> loadPermissions() {
        return PermissionModelHelper.toModels(permissionsRepository.findAll());
    }

    public List<Permission> loadPermissionsByIds(List<Long> ids) {
        return permissionsRepository.findAllById(ids);
    }
}
