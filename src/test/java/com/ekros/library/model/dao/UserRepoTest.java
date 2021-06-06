package com.ekros.library.model.dao;

import com.ekros.library.model.dao.config.DBCPDataSource;
import com.ekros.library.model.dao.impl.UserRepo;
import com.ekros.library.model.dao.interfaces.IUserRepo;
import com.ekros.library.model.entity.Role;
import com.ekros.library.model.entity.User;
import org.junit.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


public class UserRepoTest {

    private final IUserRepo repo;

    @BeforeClass
    public static void beforeClass() throws Exception {
        truncate();
    }

    public UserRepoTest(){
        repo = UserRepo.getInstance();
    }

    private static void truncate() throws SQLException {
        try(Connection conn = DBCPDataSource.getConnection()){
            conn.setAutoCommit(false);
            conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            conn.createStatement().executeUpdate("TRUNCATE TABLE users");
            conn.createStatement().executeUpdate("TRUNCATE TABLE books");
            conn.createStatement().executeUpdate("TRUNCATE TABLE orders");
            conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
            conn.commit();
            conn.setAutoCommit(true);
        }
    }

    private static User newUserInstance(String email){
        return new User("Test", "Test", email, "Test", new Date(1111111111L), "12345", Role.USER, false);
    }

    @Test
    public void insertUserTest() throws SQLException {
        User user = newUserInstance("Test@mail.com");
        int id = user.getId();
        repo.insert(user);
        Assert.assertNotEquals(id, user.getId());
    }

    @Test
    public void getUserByIdTest() throws SQLException {
        User user = repo.getUserById(1);
        Assert.assertNotNull(user);
        Assert.assertEquals(1, user.getId());
    }

    @Test
    public void getUserByEmail() throws SQLException {
        repo.insert(newUserInstance("Test1@mail.com"));
        User user = repo.getUserByEmail("Test1@mail.com");
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getEmail(), "Test1@mail.com");
    }

    @Test
    public void updateUserTest() throws SQLException {
        User user = newUserInstance("Test2@mail.com");
        repo.insert(user);
        user.setPhone("0000000000");
        user.setRole(Role.ADMIN);
        user.setBlock(true);
        user.setPassword("TestTest");
        user.setFirstName("Alex");
        user.setLastName("Alex");
        repo.update(user);
        user = repo.getUserByEmail("Test2@mail.com");
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getPhone(), "0000000000");
        Assert.assertEquals(user.getRole(), Role.ADMIN);
        Assert.assertTrue(user.isBlock());
        Assert.assertEquals(user.getPassword(), "TestTest");
        Assert.assertEquals(user.getFirstName(), "Alex");
        Assert.assertEquals(user.getLastName(), "Alex");
    }

    @Test
    public void deleteUserTest() throws SQLException {
        User user = newUserInstance("Test3@mail.com");
        repo.insert(user);
        repo.delete(user.getId());
        user = repo.getUserById(user.getId());
        Assert.assertNull(user);
    }

    @Test
    public void countAndPageTest() throws SQLException {
        int count = repo.getCount();
        List<User> users = repo.getUsersPage(0, 20);
        Assert.assertEquals(count, users.size());
    }

    @AfterClass
    public static void afterClass() throws Exception {
        truncate();
    }
}
