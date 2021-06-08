package com.ekros.library.controller.commands.user;

import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.AuthUser;
import com.ekros.library.model.entity.Role;
import com.ekros.library.model.entity.User;
import com.ekros.library.model.service.OrderService;
import com.ekros.library.model.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

public class ProfileCommandTest {
    private UserService userService;
    private OrderService orderService;
    private HttpSession session;
    private HttpServletRequest request;
    ProfileCommand command;

    @Before
    public void setUp() throws Exception {
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        userService = mock(UserService.class);
        orderService = mock(OrderService.class);
        command = new ProfileCommand(userService, orderService);
    }

    @Test
    public void executeTest() throws Exception {
        when(session.getAttribute("auth")).thenReturn(new AuthUser(1, Role.USER));
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("from")).thenReturn("");
        when(userService.getUserById(1)).thenReturn(new User());
        Assert.assertEquals(Path.PROFILE, command.execute(request));
        verify(orderService).getOrderInfo(1);
    }
}
