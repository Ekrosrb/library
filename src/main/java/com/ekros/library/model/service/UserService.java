package com.ekros.library.model.service;

import com.ekros.library.model.dao.impl.UserRepo;
import com.ekros.library.model.dao.interfaces.IUserRepo;
import com.ekros.library.model.entity.User;

import java.sql.SQLException;

public class UserService {
    private final IUserRepo userRepo;

    public UserService() {
        this.userRepo = UserRepo.getInstance();
    }

    public void addUser(User user) throws SQLException {
        userRepo.insert(user);
    }

    public void deleteUser(User user) throws SQLException{
        userRepo.delete(user);
    }

    public void updateUser(User user) throws SQLException{
        userRepo.update(user);
    }

    public User getUserById(int id) throws SQLException{
        return userRepo.getUserById(id);
    }

    public User getUserByEmail(String email) throws SQLException{
        return userRepo.getUserByEmail(email);
    }
}
