package com.ekros.library.model.dao.impl;

import com.ekros.library.model.dao.config.DBCPDataSource;
import com.ekros.library.model.dao.interfaces.IUserRepo;
import com.ekros.library.model.dao.mapper.UserMapper;
import com.ekros.library.model.entity.User;

import java.sql.*;

public class UserRepo implements IUserRepo {

    private static final String SQL_ADD_USER = "INSERT INTO users(first_name, last_name, email, password, birthday, phone, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE email = ?";
    private static final String SQL_UPDATE_USER = "UPDATE users SET first_name = ?, last_name = ?, password = ?, birthday = ?, phone = ?, role = ? WHERE email = ?";
    private static final String SQL_SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";

    private static UserRepo userRepo;

    public static UserRepo getInstance(){
        if(userRepo == null){
            userRepo = new UserRepo();
        }
        return userRepo;
    }

    @Override
    public void insert(User user) throws SQLException{

        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_ADD_USER, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setDate(5, user.getBirthday());
            statement.setString(6, user.getPhone());
            statement.setInt(7, user.getRole().ordinal());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public void delete(User user) throws SQLException{
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_DELETE_USER)){
            statement.setString(1, user.getEmail());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(User user) throws SQLException{
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_UPDATE_USER)){
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.setDate(4, user.getBirthday());
            statement.setString(5, user.getPhone());
            statement.setInt(6, user.getRole().ordinal());
            statement.setString(7, user.getEmail());
            statement.executeUpdate();
        }
    }

    @Override
    public User getUserByEmail(String email) throws SQLException{
        User user = null;
        UserMapper mapper = new UserMapper();
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_USER_BY_EMAIL)){
            statement.setString(1, email);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                user = mapper.getFromResultSet(res);
            }
        }
        return user;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = null;
        UserMapper mapper = new UserMapper();
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_USER_BY_ID)){
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                user = mapper.getFromResultSet(res);
            }
        }
        return user;
    }
}
