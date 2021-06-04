package com.ekros.library.model.dao.interfaces;

import com.ekros.library.model.entity.Book;

import java.sql.SQLException;
import java.util.List;
/**
 * IBookRepo - database operations with books table
 * @author ekros
 * @see Book
 * */
public interface IBookRepo extends IRepo<Book>{
    /**
     * Get book with specific id from db
     * @param id - book id in db
     * @return the searched for book
     * */
    Book getBookById(int id) throws SQLException;
    /**
     * Get books that contain part of the given title in the name, or the author.
     * Also sorts by the specified field in range
     * @param name - part in the name or the author
     * @param orderBy - the name of the field by which the data is sorted
     * @param from - range start
     * @param to -range end
     * @return sorted list of book
     * @see Book
     * */
    List<Book> getBooksByContainsName(String name, String orderBy, int from, int to) throws SQLException;
    /**
     * Get count of books that contain part of the given title in the name, or the author.
     * @param name - part in the name or the author
     * @return count of books
     * */
    int getBooksCount(String name) throws SQLException;
}
