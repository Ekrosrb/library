package com.ekros.library.controller.commands.guest;

import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.service.BookService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

public class SearchBookCommandTest {
    private BookService bookService;
    private HttpServletRequest request;
    private HttpSession session;
    private SearchBookCommand command;
    @Before
    public void setUp() throws Exception {
        Logger log = mock(Logger.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        bookService = mock(BookService.class);
        command = new SearchBookCommand(bookService);

        Whitebox.setInternalState(command, "log", log);
    }


    @Test
    public void executeTest() throws Exception {
        when(request.getParameter("bookName")).thenReturn(null);
        when(request.getParameter("from")).thenReturn(null);
        when(request.getParameter("orderBy")).thenReturn(null);
        Assert.assertEquals(Path.INDEX_PAGE, command.execute(request));
        when(request.getParameter("bookName")).thenReturn("");
        when(request.getParameter("orderBy")).thenReturn("");
        Assert.assertEquals(Path.INDEX_PAGE, command.execute(request));
        when(request.getParameter("bookName")).thenReturn("Test");
        Assert.assertEquals(Path.INDEX_PAGE, command.execute(request));
        verify(request).setAttribute("message", "The title is too short!");
        when(request.getParameter("bookName")).thenReturn("Test1");
        when(request.getParameter("from")).thenReturn("");
        Assert.assertThrows(NumberFormatException.class , () -> command.execute(request));
    }
}
