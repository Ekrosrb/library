package com.ekros.library.model.service;

import com.ekros.library.model.dao.impl.UserRepo;
import com.ekros.library.model.dao.interfaces.IUserRepo;
import com.ekros.library.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final IUserRepo userRepo;

    public UserService() {
        this.userRepo = UserRepo.getInstance();
    }

    public void addUser(User user) throws SQLException {
        userRepo.insert(user);
    }

    public void deleteUser(int id) throws SQLException{
        userRepo.delete(id);
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

    public List<User> getUsersPage(int from) throws SQLException {
        return userRepo.getUsersPage(from, from+20);
    }

    public int getCount() throws SQLException{
        return userRepo.getCount();
    }
}
