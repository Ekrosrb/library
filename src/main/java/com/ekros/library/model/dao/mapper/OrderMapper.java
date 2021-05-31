package com.ekros.library.model.dao.mapper;

import com.ekros.library.model.entity.Status;
import com.ekros.library.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements Mapper<Order>{
    @Override
    public Order getFromResultSet(ResultSet res) throws SQLException {
        return new Order(
                res.getInt("id"),
                res.getInt("user_id"),
                res.getInt("book_id"),
                res.getDate("term"),
                res.getLong("fine"),
                Status.values()[res.getInt("status")]);
    }
}
