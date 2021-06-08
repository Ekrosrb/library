package com.ekros.library.controller.commands.user;

import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.service.OrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;

public class PayFineCommandTest {
    private OrderService orderService;
    private HttpServletRequest request;
    PayFineCommand command;

    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        orderService = mock(OrderService.class);
        command = new PayFineCommand(orderService);
    }

    @Test
    public void executeTest() throws Exception {
        when(request.getParameter("id")).thenReturn(null);
        Assert.assertEquals(Path.ERROR_PAGE, command.execute(request));
        verify(request).setAttribute("message", "Incorrect id value!");
        when(request.getParameter("id")).thenReturn("1");
        Assert.assertEquals(Path.REDIRECT_PROFILE, command.execute(request));
        verify(orderService).payFine(1);
    }
}
