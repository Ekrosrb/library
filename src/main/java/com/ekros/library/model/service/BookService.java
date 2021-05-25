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

    public void deleteBook(Book book) throws SQLException{
        bookRepo.delete(book);
    }

    public Book getBookById(int id) throws SQLException{
        return bookRepo.getBookById(id);
    }

    public List<Book> getBooksByContainName(String name) throws SQLException{
        name = "%" + name + "%";
        return bookRepo.getBooksByContainsName(name);
    }

}
