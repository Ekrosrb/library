package com.ekros.library.model.dao;

import com.ekros.library.model.dao.config.DBCPDataSource;
import com.ekros.library.model.dao.impl.BookRepo;
import com.ekros.library.model.dao.interfaces.IBookRepo;
import com.ekros.library.model.entity.Book;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookRepoTest {

    private IBookRepo repo;

    public BookRepoTest() {
        this.repo = BookRepo.getInstance();
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        truncate();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        truncate();
    }

    private static void truncate() throws SQLException {
        try(Connection conn = DBCPDataSource.getConnection()){
            conn.setAutoCommit(false);
            conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            conn.createStatement().executeUpdate("TRUNCATE TABLE users");
            conn.createStatement().executeUpdate("TRUNCATE TABLE books");
            conn.createStatement().executeUpdate("TRUNCATE TABLE orders");
            conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
            conn.commit();
            conn.setAutoCommit(true);
        }
    }

    private static Book newBookInstance(String name){
        return new Book(name, "Test", "Test", "Test", "Тест", 10);
    }

    @Test
    public void insertBookTest() throws SQLException {
        Book book = newBookInstance("First");
        int id = book.getId();
        repo.insert(book);
        Assert.assertNotEquals(id, book.getId());
    }

    @Test
    public void getBookByIdTest() throws SQLException {
        Book book = newBookInstance("Second");
        repo.insert(book);
        book = repo.getBookById(book.getId());
        Assert.assertEquals(book.getName(), "Second");
    }

    @Test
    public void updateBookTest() throws SQLException {
        Book book = newBookInstance("Third");
        repo.insert(book);
        book.setName("Hello");
        book.setAuthor("TestAuthor");
        book.setEdition("TestEdition");
        book.setDescription("TestDescription");
        book.setDescriptionRu("ТестОписание");
        book.setCount(200);
        repo.update(book);
        book = repo.getBookById(book.getId());
        Assert.assertEquals(book.getName(), "Hello");
        Assert.assertEquals(book.getAuthor(), "TestAuthor");
        Assert.assertEquals(book.getEdition(), "TestEdition");
        Assert.assertEquals(book.getDescription(),"TestDescription");
        Assert.assertEquals(book.getDescriptionRu(), "ТестОписание");
    }

    @Test
    public void deleteBookTest() throws SQLException {
        Book book = newBookInstance("IV");
        repo.insert(book);
        repo.delete(book.getId());
        book = repo.getBookById(book.getId());
        Assert.assertNull(book);
    }

    @Test
    public void countAndBooksListTest() throws SQLException {
        Book book = newBookInstance("World");
        Book book2 = newBookInstance("World 2");
        Book book3 = newBookInstance("World 3");
        Book book4 = newBookInstance("World 4");
        repo.insert(book);
        repo.insert(book2);
        repo.insert(book3);
        repo.insert(book4);

        int count = repo.getBooksCount("%World%");
        List<Book> bookList = repo.getBooksByContainsName("%World%", "name", 0, 20);
        Assert.assertEquals(count, bookList.size());
        Assert.assertEquals(bookList.get(0).getName(), "World 4");
        Assert.assertEquals(bookList.get(1).getName(), "World 3");
        Assert.assertEquals(bookList.get(2).getName(), "World 2");
        Assert.assertEquals(bookList.get(3).getName(), "World");
    }
}
