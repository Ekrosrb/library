package com.ekros.library.model.dao.impl;

import com.ekros.library.model.dao.config.DBCPDataSource;
import com.ekros.library.model.dao.interfaces.IUserRepo;
import com.ekros.library.model.dao.mapper.UserMapper;
import com.ekros.library.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepo implements IUserRepo {

    private UserRepo() {}

    private static final String SQL_ADD_USER = "INSERT INTO users(first_name, last_name, email, password, birthday, phone, role, block) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String SQL_UPDATE_USER = "UPDATE users SET first_name = ?, last_name = ?, password = ?, birthday = ?, phone = ?, role = ?, block = ? WHERE email = ?";
    private static final String SQL_SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SQL_SELECT_USERS_PAGE = "SELECT * FROM users ORDER BY first_name LIMIT ?, ?";
    private static final String SQL_USERS_COUNT = "SELECT COUNT(*) FROM users";

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
            statement.setBoolean(8, user.isBlock());
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
    public void delete(int id) throws SQLException{
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_DELETE_USER)){
            statement.setInt(1, id);
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
            statement.setBoolean(7, user.isBlock());
            statement.setString(8, user.getEmail());

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

    @Override
    public List<User> getUsersPage(int from, int to) throws SQLException {
        List<User> users = new ArrayList<>();
        UserMapper mapper = new UserMapper();
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_USERS_PAGE)){
            statement.setInt(1, from);
            statement.setInt(2, to);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                users.add(mapper.getFromResultSet(rs));
            }
        }
        return users;
    }

    @Override
    public int getCount() throws SQLException {
        try(Connection conn = DBCPDataSource.getConnection();
            Statement statement = conn.createStatement()){
            ResultSet res = statement.executeQuery(SQL_USERS_COUNT);
            int count = 0;
            if(res.next()){
                 count = res.getInt(1);
            }
            return count;
        }
    }
}
