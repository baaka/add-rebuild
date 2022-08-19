package com.app.rebuild.domain.auth;

import javax.persistence.*;

@Entity
@Table(name = "APP_PERMISSIONS")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "PERMISSION_NAME")
    private String permissionName;

    @Column(name = "DESCRIPTION")
    private String description;

    public Permission() {
    }

    public Permission(long id, String permissionName, String description) {
        this.id = id;
        this.permissionName = permissionName;
        this.description = description;
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
}
