package com.ekros.library.model.dao.interfaces;

import com.ekros.library.model.entity.User;

import java.sql.SQLException;
import java.util.List;
/**
 * IUserRepo - database operations with users table
 * @author ekros
 * @see User
 * */
public interface IUserRepo extends IRepo<User> {
    /**
     * Get record about user with transferred email
     * @param email - email of user
     * @return the user record you are looking for
     * @throws SQLException for invalid data or connection error
     * */
    User getUserByEmail(String email) throws SQLException;
    /**
     * Get record about user with transferred id
     * @param id - user id in db
     * @return the user record you are looking for
     * @throws SQLException for invalid data or connection error
     * */
    User getUserById(int id) throws SQLException;
    /**
     * Get records about users from range
     * @param from - range start
     * @param to - range end
     * @return list of users
     * @throws SQLException for invalid data or connection error
     * */
    List<User> getUsersPage(int from, int to) throws SQLException;
    /**
     * Get users count in db
     * @return count of users
     * @throws SQLException for invalid data or connection error
     * */
    int getCount() throws SQLException;
}
