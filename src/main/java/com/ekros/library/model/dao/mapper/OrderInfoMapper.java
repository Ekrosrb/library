package com.ekros.library.model.dao.mapper;

import com.ekros.library.model.entity.OrderInfo;
import com.ekros.library.model.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderInfoMapper implements Mapper<OrderInfo>{
    @Override
    public OrderInfo getFromResultSet(ResultSet res) throws SQLException {
        return new OrderInfo(
                res.getInt(1),
                res.getInt(2),
                res.getString(3),
                res.getString(4),
                res.getString(5),
                res.getString(6),
                res.getDate(7),
                res.getDate(8),
                res.getLong(9),
                Status.values()[res.getInt(10)]);
    }
}
