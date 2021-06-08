package com.ekros.library.controller.commands.user;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

public class LogoutCommandTest {
    private HttpServletRequest request;
    private HttpSession session;
    private LogoutCommand command;
    @Before
    public void setUp() throws Exception {
        Logger log = mock(Logger.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        command = new LogoutCommand();

        Whitebox.setInternalState(command, "log", log);
    }


    @Test
    public void executeTest() throws ServletException {
        when(request.getSession(false)).thenReturn(session);
        command.execute(request);
        verify(session).setAttribute("auth", null);
    }

    @Test
    public void executeNullTest() throws ServletException {
        when(request.getSession(false)).thenReturn(null);
        command.execute(request);
        verify(session, never()).setAttribute("auth", null);
    }
}
