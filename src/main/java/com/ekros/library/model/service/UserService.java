package com.ekros.library.model.service;

import com.ekros.library.model.dao.config.DBCPDataSource;
import com.ekros.library.model.dao.impl.UserRepo;
import com.ekros.library.model.dao.interfaces.IUserRepo;
import com.ekros.library.model.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserService {
    private final IUserRepo userRepo;
    private final int page = Integer.parseInt(DBCPDataSource.prop.getProperty("page.size"));
    public UserService() {
        this.userRepo = UserRepo.getInstance();
    }

    public User addUser(User user) throws SQLException {
        return userRepo.insert(user);
    }

    public int deleteUser(int id) throws SQLException{
        return userRepo.delete(id);
    }

    public User updateUser(User user) throws SQLException{
        return userRepo.update(user);
    }

    public User getUserById(int id) throws SQLException{
        return userRepo.getUserById(id);
    }

    public User getUserByEmail(String email) throws SQLException{
        return userRepo.getUserByEmail(email);
    }

    public List<User> getUsersPage(int from) throws SQLException {
        return userRepo.getUsersPage(from, from+page);
    }

    public List<User> getSingleUserPage(int id) throws SQLException {
        User user = userRepo.getUserById(id);
        if(user != null) {
            return Collections.singletonList(userRepo.getUserById(id));
        }
        return new ArrayList<>();
    }

    public int getCount() throws SQLException{
        return userRepo.getCount();
    }
}
