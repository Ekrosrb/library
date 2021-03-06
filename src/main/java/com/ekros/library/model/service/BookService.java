package com.ekros.library.model.service;

import com.ekros.library.model.dao.config.DBCPDataSource;
import com.ekros.library.model.dao.impl.BookRepo;
import com.ekros.library.model.dao.interfaces.IBookRepo;
import com.ekros.library.model.entity.Book;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    private final IBookRepo bookRepo;
    private final int page = Integer.parseInt(DBCPDataSource.prop.getProperty("page.size"));

    public BookService(){
        bookRepo = BookRepo.getInstance();
    }

    public Book addBook(Book book) throws SQLException {
        return bookRepo.insert(book);
    }

    public Book updateBook(Book book) throws SQLException{
        return bookRepo.update(book);
    }

    public int deleteBook(int id) throws SQLException{
        return bookRepo.delete(id);
    }

    public Book getBookById(int id) throws SQLException{
        return bookRepo.getBookById(id);
    }

    public List<Book> getBooksByContainName(String name, String orderBy, int from) throws SQLException{
        return bookRepo.getBooksByContainsName("%" + name + "%", orderBy, from, from+page);
    }

    public int getCount(String name) throws SQLException {
        return bookRepo.getBooksCount("%" + name + "%");
    }

}
