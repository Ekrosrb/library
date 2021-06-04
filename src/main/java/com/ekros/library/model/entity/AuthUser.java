package com.ekros.library.model.entity;

import java.io.Serializable;
/**
 * AuthUser - the essence of the authorized user, used for transmission in the session
 * @author ekros
 * @see Role
 * @see User
 * */
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
