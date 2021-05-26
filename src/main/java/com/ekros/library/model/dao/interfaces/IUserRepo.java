package com.ekros.library.model.dao.interfaces;

import com.ekros.library.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepo extends IRepo<User> {
    User getUserByEmail(String email) throws SQLException;
    User getUserById(int id) throws SQLException;
    List<User> getUsersPage(int from, int to) throws SQLException;

    int getCount() throws SQLException;
}
