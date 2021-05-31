package com.ekros.library.model.dao.mapper;

import com.ekros.library.model.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements Mapper<Book> {
    @Override
    public Book getFromResultSet(ResultSet res) throws SQLException {
        return new Book(
                res.getInt("id"),
                res.getString("name"),
                res.getString("author"),
                res.getString("edition"),
                res.getString("description"),
                res.getString("description_ru"),
                res.getInt("count"));
    }
}
