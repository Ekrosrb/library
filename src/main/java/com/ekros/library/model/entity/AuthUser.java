package com.ekros.library.model.entity;

import java.io.Serializable;

public class AuthUser implements Serializable {
    int userId;
    Role role;

    public AuthUser(int userId, Role role) {
        this.userId = userId;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
