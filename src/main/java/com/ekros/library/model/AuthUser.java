package com.ekros.library.model;

import com.ekros.library.dao.Role;

import java.io.Serializable;

public class AuthUser implements Serializable {

    private int userId;
    private String token;
    private Role role;

    public AuthUser() {
    }

    public AuthUser(int userId, String token, Role role) {
        this.userId = userId;
        this.token = token;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
