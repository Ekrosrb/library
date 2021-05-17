package com.ekros.library.dao;

import com.ekros.library.dao.interfaces.IUserRepo;
import com.ekros.library.model.User;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDaoTest {

    private IUserRepo repo;

    public UserDaoTest(){
        repo = UnitOfWork.getUserRepo();
    }

    @Test
    public void addRemoveUpdateTest() throws SQLException {
        User user = new User("test@email.com", "12345", Role.USER);
        repo.insert(user);
        assertEquals("12345" , repo.getUserByEmail(user.getEmail()).getPassword());
        user.setPassword("54321");
        repo.update(user);
        assertEquals("54321" , repo.getUserByEmail(user.getEmail()).getPassword());
        repo.delete(user);
        assertNull(repo.getUserByEmail(user.getEmail()));
    }

}
