package com.app.rebuild.model;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private long id;
    private String username;
    private String identificationNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String accountNumber;
    private String phone;
    private List<PermissionModel> permissions;
    private String newSimplePassword;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<PermissionModel> getPermissions() {
        return permissions == null ? new ArrayList<>() : permissions;
    }

    public void setPermissions(List<PermissionModel> permissions) {
        this.permissions = permissions;
    }

    public String getNewSimplePassword() {
        return newSimplePassword;
    }

    public void setNewSimplePassword(String newSimplePassword) {
        this.newSimplePassword = newSimplePassword;
    }

}
