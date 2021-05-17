package com.ekros.library.services;

import com.ekros.library.dao.interfaces.IUserRepo;
import com.ekros.library.dao.UnitOfWork;
import com.ekros.library.model.User;

import java.sql.SQLException;

public class UserService {

    private static final IUserRepo userRepo = UnitOfWork.getUserRepo();
    private static UserService userServ;

    private UserService(){
    }

    public static UserService getInstance(){
        if(userServ == null){
            userServ = new UserService();
        }
        return userServ;
    }

    public void addUser(User user) throws SQLException {
        user.setPassword(user.getPassword());
        userRepo.insert(user);
    }

    public User getUserByEmail(String email) throws SQLException {
        return userRepo.getUserByEmail(email);
    }

}
