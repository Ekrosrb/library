package com.ekros.library.model.dao.interfaces;

import com.ekros.library.model.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookRepo extends IRepo<Book>{
    Book getBookById(int id) throws SQLException;
    List<Book> getBooksByContainsName(String name, String orderBy, int from, int to) throws SQLException;
    int getBooksCount(String name) throws SQLException;

}
