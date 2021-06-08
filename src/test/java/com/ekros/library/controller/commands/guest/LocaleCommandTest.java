package com.ekros.library.controller.commands.guest;

import com.ekros.library.controller.commands.Path;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocaleCommandTest {
    private HttpServletRequest request;
    private HttpSession session;
    private LocaleCommand command;
    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        command = new LocaleCommand();
    }

    @Test
    public void executeTest() throws Exception {
        when(request.getParameter("locale")).thenReturn(null);
        Assert.assertEquals(Path.ERROR_PAGE, command.execute(request));
        when(request.getParameter("locale")).thenReturn("");
        Assert.assertEquals(Path.ERROR_PAGE, command.execute(request));
        when(request.getParameter("locale")).thenReturn("en");
        Assert.assertEquals(Path.REDIRECT_MAIN_PAGE, command.execute(request));
    }
}
