package com.ekros.library.controller.commands.user;

import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.User;
import com.ekros.library.model.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;

public class UpdateUserCommandTest {
    private UserService userService;
    private HttpServletRequest request;
    private UpdateUserCommand command;

    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        userService = mock(UserService.class);
        command = new UpdateUserCommand(userService);
    }

    @Test
    public void executeTest() throws Exception {
        when(request.getParameter("id")).thenReturn(null);
        Assert.assertEquals(Path.ERROR_PAGE, command.execute(request));
        when(userService.getUserById(anyInt())).thenReturn(new User());
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("firstName")).thenReturn("Test");
        when(request.getParameter("lastName")).thenReturn("Test");
        when(request.getParameter("password")).thenReturn("12345");
        when(request.getParameter("phone")).thenReturn("12345");
        when(request.getParameter("role")).thenReturn("USER");
        when(request.getParameter("block")).thenReturn("false");
        Assert.assertEquals(Path.REDIRECT_ADMIN_PAGE, command.execute(request));
        verify(userService).updateUser(any());
    }
}
