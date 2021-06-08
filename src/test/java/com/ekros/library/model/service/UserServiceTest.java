package com.ekros.library.model.service;

import com.ekros.library.model.dao.impl.OrderRepo;
import com.ekros.library.model.dao.impl.UserRepo;
import com.ekros.library.model.entity.Role;
import com.ekros.library.model.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private final UserService userService = new UserService();
    private UserRepo userRepo;

    @Before
    public void setUp() throws Exception {
        userRepo = mock(UserRepo.class);
        Whitebox.setInternalState(userService, "userRepo", userRepo);
    }

    private static User newUserInstance(String email){
        return new User("Test", "Test", email, "Test", new Date(1111111111L), "12345", Role.USER, false);
    }
    @Test
    public void addUser() throws SQLException {
        User user = newUserInstance("Test@mail.com");
        when(userRepo.insert(user)).thenReturn(user);
        Assert.assertEquals(user, userService.addUser(user));
    }

    @Test
    public void deleteUser() throws SQLException {
        int id = 1;
        when(userRepo.delete(id)).thenReturn(id);
        Assert.assertEquals(id, userService.deleteUser(id));
    }

    @Test
    public void updateUser() throws SQLException {
        User user = newUserInstance("Test@mail.com");
        when(userRepo.update(user)).thenReturn(user);
        Assert.assertEquals(user, userService.updateUser(user));
    }

    @Test
    public void getUserById() throws SQLException {
        User user = newUserInstance("Test@mail.com");
        when(userRepo.getUserById(1)).thenReturn(user);
        Assert.assertEquals(user, userService.getUserById(1));
    }

    @Test
    public void getUserByEmail() throws SQLException {
        User user = newUserInstance("Test@mail.com");
        when(userRepo.getUserByEmail(user.getEmail())).thenReturn(user);
        Assert.assertEquals(user, userService.getUserByEmail(user.getEmail()));
    }

    @Test
    public void getUsersPage() throws SQLException {
        User user = newUserInstance("Test@mail.com");
        when(userRepo.getUsersPage(0, 20)).thenReturn(Collections.singletonList(user));
        Assert.assertEquals(user, userService.getUsersPage(0).get(0));
    }

    @Test
    public void getSingleUserPage() throws SQLException {
        User user = newUserInstance("Test@mail.com");
        when(userRepo.getUserById(1)).thenReturn(user);
        when(userRepo.getUserById(2)).thenReturn(null);
        Assert.assertEquals(user, userService.getSingleUserPage(1).get(0));
        Assert.assertEquals(0, userService.getSingleUserPage(2).size());
    }

    @Test
    public void getCount() throws SQLException {
        when(userRepo.getCount()).thenReturn(5);
        Assert.assertEquals(5, userService.getCount());
    }
}
