package com.app.rebuild.model.helper;


import com.app.rebuild.domain.auth.Permission;
import com.app.rebuild.model.PermissionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PermissionModelHelper {

    public static PermissionModel toModel(Permission permission) {
        return new PermissionModel(permission.getId(), permission.getPermissionName(), permission.getDescription());
    }

    public static Permission toEntity(PermissionModel model) {
        return new Permission(model.getId(), model.getPermissionName(), model.getDescription());
    }

    public static List<Permission> toEntities(List<PermissionModel> permissionModels) {
        if (permissionModels == null) {
            return new ArrayList<>();
        }

        return permissionModels.stream().map(PermissionModelHelper::toEntity).collect(Collectors.toList());
    }

    public static List<PermissionModel> toModels(List<Permission> permissions) {
        if (permissions == null) {
            return new ArrayList<>();
        }

        return permissions.stream().map(PermissionModelHelper::toModel).collect(Collectors.toList());
    }
}
