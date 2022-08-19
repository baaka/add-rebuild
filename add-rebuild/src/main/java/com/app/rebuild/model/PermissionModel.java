package com.app.rebuild.model;

import java.util.Objects;

public class PermissionModel {
    private long id;
    private String permissionName;
    private String description;

    public PermissionModel() {
    }

    public PermissionModel(long id, String permissionName, String description) {
        this.id = id;
        this.permissionName = permissionName;
        this.description=description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionModel that = (PermissionModel) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
