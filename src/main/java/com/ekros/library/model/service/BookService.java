package com.ekros.library.model.service;

import com.ekros.library.model.dao.impl.BookRepo;
import com.ekros.library.model.dao.interfaces.IBookRepo;
import com.ekros.library.model.entity.Book;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    private final IBookRepo bookRepo;

    public BookService(){
        bookRepo = BookRepo.getInstance();
    }

    public void addBook(Book book) throws SQLException {
        bookRepo.insert(book);
    }

    public void updateBook(Book book) throws SQLException{
        bookRepo.update(book);
    }

    public void deleteBook(int id) throws SQLException{
        bookRepo.delete(id);
    }

    public Book getBookById(int id) throws SQLException{
        return bookRepo.getBookById(id);
    }

    public List<Book> getBooksByContainName(String name, int from) throws SQLException{
        return bookRepo.getBooksByContainsName("%" + name + "%", from, from+20);
    }

    public int getCount(String name) throws SQLException {
        return bookRepo.getBooksCount("%" + name + "%");
    }

}
