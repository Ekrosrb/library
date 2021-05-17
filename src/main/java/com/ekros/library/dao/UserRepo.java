package com.ekros.library.dao;

import com.ekros.library.dao.config.DBCPDataSource;
import com.ekros.library.dao.interfaces.IUserRepo;
import com.ekros.library.model.AuthUser;
import com.ekros.library.model.User;

import java.sql.*;

public class UserRepo implements IUserRepo {

    private static final String SQL_ADD_USER = "INSERT INTO users(email, password, role) VALUES (?, ?, ?)";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE email = ?";
    private static final String SQL_UPDATE_USER = "UPDATE users SET password = ?, role = ? WHERE email = ?";
    private static final String SQL_SELECT_USER = "SELECT * FROM users WHERE email = ?";

    @Override
    public void insert(User user) throws SQLException{

        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_ADD_USER, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().ordinal());
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
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getRole().ordinal());
            statement.executeUpdate();
        }
    }

    @Override
    public User getUserByEmail(String email) throws SQLException{
        User user;
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_USER)){
            statement.setString(1, email);
            user = getUserFromResult(statement);
        }
        return user;
    }

    private AuthUser getAuthUserFromResult(PreparedStatement statement) throws SQLException{
        AuthUser authUser = null;
        try(ResultSet rs = statement.executeQuery()) {
            if(rs.next()){
                authUser = new AuthUser(
                        rs.getInt("user_id"),
                        rs.getString("token"),
                        Role.values()[rs.getInt("role")]
                );
            }
        }
        return authUser;

    }

    private User getUserFromResult(PreparedStatement statement) throws SQLException{
        User user = null;
        try(ResultSet rs = statement.executeQuery()){
            if(rs.next()){
                user = new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        Role.values()[rs.getInt("role")]
                );
            }
        }
        return user;
    }
}
