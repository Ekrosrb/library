package com.ekros.library.model.entity;

import java.sql.Date;
import java.util.Objects;

/**
 * User - the entity of the registered user.
 * Contains all information entered by the user upon successful registration.
 * */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthday;
    private String phone;
    private Role role;
    private boolean block;

    public User() {
    }

    public User(int id, String firstName, String lastName, String email, String password, Date birthday, String phone, Role role, boolean block) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.phone = phone;
        this.role = role;
        this.block = block;
    }

    public User(String firstName, String lastName, String email, String password, Date birthday, String phone, Role role, boolean block) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.phone = phone;
        this.role = role;
        this.block = block;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return block == user.block && firstName.equals(user.firstName) && lastName.equals(user.lastName) && email.equals(user.email) && password.equals(user.password) && birthday.equals(user.birthday) && phone.equals(user.phone) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password, birthday, phone, role, block);
    }
}
