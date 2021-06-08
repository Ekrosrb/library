package com.ekros.library.controller.commands.guest;

import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.AuthUser;
import com.ekros.library.model.entity.Role;
import com.ekros.library.model.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SiginCommandTest {

    private UserService userService;
    private HttpServletRequest request;
    private HttpSession session;
    private SiginCommand command;
    @Before
    public void setUp() throws Exception {
        Logger log = mock(Logger.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        userService = mock(UserService.class);
        command = new SiginCommand(userService);
        Whitebox.setInternalState(command, "log", log);
    }

    @Test
    public void executeTest() throws Exception {
        when(request.getParameter("firstName")).thenReturn("Test");
        when(request.getParameter("lastName")).thenReturn("Test");
        when(request.getParameter("email")).thenReturn(null);
        when(request.getParameter("password")).thenReturn("12345");
        when(request.getParameter("birthday")).thenReturn("2000-01-01");
        when(request.getParameter("phone")).thenReturn("12345");
        when(request.getParameter("role")).thenReturn("USER");
        Assert.assertEquals(Path.MAIN_PAGE, command.execute(request));
        when(request.getParameter("email")).thenReturn("mail@mail.com");
        when(session.getAttribute("auth")).thenReturn(new AuthUser(1, Role.USER));
        Assert.assertEquals(Path.MAIN_PAGE, command.execute(request));
        when(session.getAttribute("auth")).thenReturn(null);
        Assert.assertEquals(Path.REDIRECT_MAIN_PAGE, command.execute(request));
        when(userService.addUser(any())).thenThrow(new SQLException());
        Assert.assertEquals(Path.ERROR_PAGE, command.execute(request));

    }
}
