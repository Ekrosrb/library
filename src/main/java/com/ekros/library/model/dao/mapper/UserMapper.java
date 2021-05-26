package com.ekros.library.model.dao.mapper;

import com.ekros.library.model.entity.Role;
import com.ekros.library.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User>{
    @Override
    public User getFromResultSet(ResultSet res) throws SQLException {
        return new User(
                res.getInt("id"),
                res.getString("first_name"),
                res.getString("last_name"),
                res.getString("email"),
                res.getString("password"),
                res.getDate("birthday"),
                res.getString("phone"),
                Role.values()[res.getInt("role")],
                res.getBoolean("block")
        );
    }
}
