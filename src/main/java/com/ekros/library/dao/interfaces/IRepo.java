package com.ekros.library.dao.interfaces;

import java.sql.SQLException;

public interface IRepo<T> {
    void insert(T t) throws SQLException;
    void delete(T t) throws SQLException;
    void update(T t) throws SQLException;
}
