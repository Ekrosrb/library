package com.ekros.library.model.dao.interfaces;

import com.ekros.library.model.entity.Order;
import com.ekros.library.model.entity.OrderInfo;
import com.ekros.library.model.entity.Status;

import java.sql.SQLException;
import java.util.List;

public interface IOrderRepo extends IRepo<Order>{
    Order getOrder(int id) throws SQLException;
    List<Order> getOrders(int from, int to) throws SQLException;
    List<Order> getUserOrders(int id, int from, int to) throws SQLException;
    List<Order> getOrdersByStatus(Status status, int from, int to) throws SQLException;
    int getStatusCount(Status status) throws SQLException;
    OrderInfo getOrderInfo(int id) throws SQLException;
    int getCount() throws SQLException;
    int getUserOrdersCount(int id) throws SQLException;
    void updateStatus(int id, Status status, int bookValue) throws SQLException;
//    void acceptOrder(int id) throws SQLException;
//    void closeOrder(int id) throws SQLException;



}
