package com.ekros.library.model.dao;

import com.ekros.library.model.dao.config.DBCPDataSource;
import com.ekros.library.model.dao.impl.UserRepo;
import com.ekros.library.model.dao.interfaces.IUserRepo;
import com.ekros.library.model.entity.Role;
import com.ekros.library.model.entity.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;


public class UserRepoTest {

    private final IUserRepo repo;

    @BeforeClass
    public static void beforeClass() throws Exception {
        try(Connection conn = DBCPDataSource.getConnection()){
            conn.setAutoCommit(false);
            conn.createStatement().executeUpdate("TRUNCATE TABLE users");
            conn.createStatement().executeUpdate("TRUNCATE TABLE books");
            conn.createStatement().executeUpdate("TRUNCATE TABLE orders");
            conn.commit();
            conn.setAutoCommit(true);
        }
    }

    public UserRepoTest(){
        repo = UserRepo.getInstance();
    }

    private static User newUserInstance(){
        return new User("Test", "Test", "Test@mail.com", "Test", new Date(1111111111L), "12345", Role.USER, false);

    }

    @Test
    public void insertUserTest() throws SQLException {
        User user = newUserInstance();
        repo.insert(user);
        Assert.assertEquals(1, user.getId());
    }
}
