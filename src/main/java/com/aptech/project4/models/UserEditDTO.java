package com.aptech.project4.models;

import java.util.List;

public class UserEditDTO {
    private String fullName;
    private String username;
    private String phone;
    private String password;
    private String email;
    private String address;
    private String role;
    private List<Long> slotIds; // Danh sách ID của các ca trực

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Long> getSlotIds() {
        return slotIds;
    }

    public void setSlotIds(List<Long> slotIds) {
        this.slotIds = slotIds;
    }
}
