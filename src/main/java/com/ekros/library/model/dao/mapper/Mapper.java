package com.ekros.library.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
    T getFromResultSet(ResultSet res) throws SQLException;
}
