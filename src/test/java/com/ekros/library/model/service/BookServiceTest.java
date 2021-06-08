package com.ekros.library.model.service;

import com.ekros.library.model.dao.impl.BookRepo;
import com.ekros.library.model.entity.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.Collections;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    private final BookService bookService = new BookService();
    private BookRepo bookRepo;
    @Before
    public void setUp() throws Exception {
        bookRepo = mock(BookRepo.class);
        Whitebox.setInternalState(bookService, "bookRepo", bookRepo);
    }

    private Book getBookInstance(){
        return new Book("Test", "Test", "Test", "Test", "Тест", 10);
    }

    @Test
    public void addBookTest() throws SQLException {
        Book book = getBookInstance();
        when(bookRepo.insert(book)).thenReturn(book);
        Assert.assertEquals(book, bookService.addBook(book));
    }

    @Test
    public void updateBookTest() throws SQLException {
        Book book = getBookInstance();
        when(bookRepo.update(book)).thenReturn(book);
        Assert.assertEquals(book, bookService.updateBook(book));
    }

    @Test
    public void deleteBookTest() throws SQLException {
        int id = 5;
        when(bookRepo.delete(id)).thenReturn(id);
        Assert.assertEquals(id, bookService.deleteBook(id));
    }

    @Test
    public void getBookByIdTest() throws SQLException {
        Book book = getBookInstance();
        int id = 5;
        when(bookRepo.getBookById(id)).thenReturn(book);
        Assert.assertEquals(book, bookService.getBookById(id));
    }

    @Test
    public void getBooksByContainNameTest() throws SQLException {
        Book book = getBookInstance();
        when(bookRepo.getBooksByContainsName(anyString(), anyString(), anyInt(), anyInt())).thenReturn(Collections.singletonList(book));
        Assert.assertEquals(bookService.getBooksByContainName("Test", "name", 0).get(0), book);
    }

    @Test
    public void getCountTest() throws SQLException {
        when(bookRepo.getBooksCount(anyString())).thenReturn(10);
        Assert.assertEquals(10, bookService.getCount("Test"));
    }
}
