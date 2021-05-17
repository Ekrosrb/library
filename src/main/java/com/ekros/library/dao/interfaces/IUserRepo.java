package com.ekros.library.dao.interfaces;

import com.ekros.library.model.AuthUser;
import com.ekros.library.model.User;

import java.sql.SQLException;

public interface IUserRepo extends IRepo<User> {
    User getUserByEmail(String email) throws SQLException;
}
