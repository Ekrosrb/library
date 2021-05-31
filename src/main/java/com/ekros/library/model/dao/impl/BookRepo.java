package com.ekros.library.model.dao.impl;

import com.ekros.library.model.dao.config.DBCPDataSource;
import com.ekros.library.model.dao.interfaces.IBookRepo;
import com.ekros.library.model.dao.mapper.BookMapper;
import com.ekros.library.model.entity.Book;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepo implements IBookRepo {

    private BookRepo(){}

    private final String SQL_INSERT_BOOK = "INSERT INTO books(name, author, edition, description, description_ru, count) VALUES (?,?,?,?,?,?)";
    private final String SQL_DELETE_BOOK = "DELETE FROM books WHERE id = ?";
    private final String SQL_UPDATE_BOOK = "UPDATE books SET name = ?, author = ?, edition = ?, description = ?, description_ru = ?, count = ? WHERE id = ?";
    private final String SQL_SELECT_BOOK_BY_ID = "SELECT * FROM books WHERE id = ?";
    private final String SQL_SELECT_BOOKS_BY_CONTAINS_NAME = "SELECT * FROM books WHERE name LIKE ? LIMIT ?, ?";
    private final String SQL_GET_BOOKS_COUNT = "SELECT COUNT(*) FROM books WHERE name LIKE ?";

    private static BookRepo bookRepo;

    private final Logger log = Logger.getLogger(BookRepo.class);

    public static BookRepo getInstance(){
        if(bookRepo == null){
            bookRepo = new BookRepo();
        }
        return bookRepo;
    }

    @Override
    public void insert(Book book) throws SQLException {
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_INSERT_BOOK)) {
            prepareUpdate(statement, book);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_DELETE_BOOK)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Book book) throws SQLException {
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_UPDATE_BOOK)) {
            prepareUpdate(statement, book);
            statement.setInt(7, book.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public Book getBookById(int id) throws SQLException{
        Book book = null;
        BookMapper mapper = new BookMapper();
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BOOK_BY_ID)) {
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                book = mapper.getFromResultSet(res);
            }
        }
        return book;
    }

    @Override
    public List<Book> getBooksByContainsName(String name, int from, int to) throws SQLException{
        BookMapper mapper = new BookMapper();
        List<Book> books = new ArrayList<>();
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BOOKS_BY_CONTAINS_NAME)) {

            statement.setString(1, name);
            statement.setInt(2, from);
            statement.setInt(3, to);
            ResultSet res = statement.executeQuery();
            while (res.next()){
                books.add(mapper.getFromResultSet(res));
            }
        }
        return books;
    }

    @Override
    public int getBooksCount(String name) throws SQLException{
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_GET_BOOKS_COUNT)){
            statement.setString(1, name);
            ResultSet res = statement.executeQuery();
            int count = 0;
            if(res.next()) {
                count = res.getInt(1);
            }
            return count;
        }
    }

    private void prepareUpdate(PreparedStatement statement, Book book) throws SQLException{
        statement.setString(1, book.getName());
        statement.setString(2, book.getAuthor());
        statement.setString(3, book.getEdition());
        statement.setString(4, book.getDescription());
        statement.setString(5, book.getDescriptionRu());
        statement.setInt(6, book.getCount());
    }
}
