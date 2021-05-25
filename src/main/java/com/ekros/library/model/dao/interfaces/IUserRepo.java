package com.ekros.library.model.dao.interfaces;

import com.ekros.library.model.entity.User;

import java.sql.SQLException;

public interface IUserRepo extends IRepo<User> {
    User getUserByEmail(String email) throws SQLException;
    User getUserById(int id) throws SQLException;

}
