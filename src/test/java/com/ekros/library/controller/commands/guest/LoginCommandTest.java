package com.ekros.library.controller.commands.guest;

import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.AuthUser;
import com.ekros.library.model.entity.Role;
import com.ekros.library.model.entity.User;
import com.ekros.library.model.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {
    private UserService userService;
    private HttpServletRequest request;
    private HttpSession session;
    private LoginCommand command;
    @Before
    public void setUp() throws Exception {
        Logger log = mock(Logger.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        userService = mock(UserService.class);
        command = new LoginCommand(userService);

        Whitebox.setInternalState(command, "log", log);
    }


    @Test
    public void executeTest() throws SQLException {
        when(request.getParameter("email")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);
        Assert.assertEquals(Path.MAIN_PAGE, command.execute(request));
        when(request.getParameter("email")).thenReturn("mail@mail.com");
        when(request.getParameter("password")).thenReturn("12345");
        User user = new User();
        user.setPassword(DigestUtils.md5Hex("12345"));
        user.setBlock(true);
        when(userService.getUserByEmail(anyString())).thenReturn(null);
        Assert.assertEquals(Path.MAIN_PAGE, command.execute(request));
        when(userService.getUserByEmail(anyString())).thenReturn(user);
        Assert.assertEquals(Path.MAIN_PAGE, command.execute(request));
        user.setBlock(false);
        when(session.getAttribute("auth")).thenReturn(new AuthUser(1, Role.USER));
        Assert.assertEquals(Path.MAIN_PAGE, command.execute(request));
        when(session.getAttribute("auth")).thenReturn(null);
        Assert.assertEquals(Path.REDIRECT_MAIN_PAGE, command.execute(request));
        when(userService.getUserByEmail(anyString())).thenThrow(new SQLException());
        Assert.assertEquals(Path.ERROR_PAGE, command.execute(request));
    }
}
