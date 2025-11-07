package com.fintrust.model;


import java.time.LocalDateTime;

public class Users {

    private int userId;                    // Primary key (user identifier)
    private String username;               // Login user name (unique)
    private String passwordHash;           // Hashed password (salted)
    private String email;                  // Email address (unique)
    private String mobile;                 // Mobile number
    private String fullName;               // Full name of user
    private Role role;                     // Role of the user (ADMIN, EMPLOYEE, CUSTOMER)
    private boolean isActive;              // Active flag (default true)
    private LocalDateTime createdAt;       // Time stamp of creation
    private LocalDateTime lastLogin;       // Time stamp of last login 

    // Enum for user roles
    public enum Role {
        ADMIN,
        EMPLOYEE,
        CUSTOMER
    }

    // Constructors
    public Users() {
    }

    public  Users(int userId, String username, String passwordHash, String email,
                String mobile, String fullName, Role role, boolean isActive,
                LocalDateTime createdAt, LocalDateTime lastLogin) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.mobile = mobile;
        this.fullName = fullName;
        this.role = role;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

 

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    // toString()
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role=" + role +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", lastLogin=" + lastLogin +
                '}';
    }
}
