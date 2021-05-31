package com.ekros.library.model.dao.interfaces;

import com.ekros.library.model.entity.Order;
import com.ekros.library.model.entity.OrderInfo;

import java.sql.SQLException;
import java.util.List;

public interface IOrderRepo extends IRepo<Order>{
    Order getOrder(int id) throws SQLException;
    List<Order> getPendingOrders(int from, int to) throws SQLException;
    List<Order> getExpiredOrders(int from, int to) throws SQLException;
    List<Order> getOrders(int from, int to) throws SQLException;
    List<Order> getUserOrders(int id, int from, int to) throws SQLException;
    int getExpiredCount() throws SQLException;
    int getPendingCount() throws SQLException;
    int getCount() throws SQLException;
    OrderInfo getOrderInfo(int id) throws SQLException;
}
