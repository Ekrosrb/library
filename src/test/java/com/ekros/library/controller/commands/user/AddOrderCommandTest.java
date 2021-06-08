package com.ekros.library.controller.commands.user;

import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.service.OrderService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class AddOrderCommandTest {
    private OrderService orderService;
    private HttpServletRequest request;
    private AddOrderCommand command;
    @Before
    public void setUp() throws Exception {
        Logger log = mock(Logger.class);
        request = mock(HttpServletRequest.class);
        orderService = mock(OrderService.class);
        command = new AddOrderCommand(orderService);

        Whitebox.setInternalState(command, "log", log);
    }

    @Test
    public void executeTest() throws Exception {
        when(request.getParameter("term")).thenReturn(null);
        Assert.assertThrows(IllegalArgumentException.class, () -> command.execute(request));
        when(request.getParameter("term")).thenReturn("2000-01-01");
        when(request.getParameter("userId")).thenReturn(null);
        Assert.assertEquals(Path.ERROR_PAGE, command.execute(request));
        when(request.getParameter("userId")).thenReturn("1");
        when(request.getParameter("bookId")).thenReturn("");
        Assert.assertEquals(Path.ERROR_PAGE, command.execute(request));
        when(request.getParameter("userId")).thenReturn("1");
        when(request.getParameter("bookId")).thenReturn("2");
        Assert.assertEquals(Path.REDIRECT_PROFILE, command.execute(request));
        when(orderService.addOrder(any())).thenThrow(new SQLException());
        Assert.assertEquals(Path.MAIN_PAGE, command.execute(request));
    }
}
