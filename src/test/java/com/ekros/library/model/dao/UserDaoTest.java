package com.ekros.library.model.dao;

import com.ekros.library.model.dao.interfaces.IUserRepo;
import org.junit.Test;

import java.sql.SQLException;

public class UserDaoTest {

    private IUserRepo repo;

    public UserDaoTest(){
        repo = UnitOfWork.getUserRepo();
    }

    @Test
    public void addRemoveUpdateTest() throws SQLException {
//        User user = new User("test@email.com", "12345", Role.USER);
//        repo.insert(user);
//        assertEquals("12345" , repo.getUserByEmail(user.getEmail()).getPassword());
//        user.setPassword("54321");
//        repo.update(user);
//        assertEquals("54321" , repo.getUserByEmail(user.getEmail()).getPassword());
//        repo.delete(user);
//        assertNull(repo.getUserByEmail(user.getEmail()));
    }

}
