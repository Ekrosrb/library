package com.ekros.library.model.dao.impl;

import com.ekros.library.model.dao.config.DBCPDataSource;
import com.ekros.library.model.dao.interfaces.IOrderRepo;
import com.ekros.library.model.dao.mapper.OrderInfoMapper;
import com.ekros.library.model.dao.mapper.OrderMapper;
import com.ekros.library.model.entity.Order;
import com.ekros.library.model.entity.OrderInfo;
import com.ekros.library.model.entity.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo implements IOrderRepo {

    private OrderRepo() {}

    private static final String SQL_INSERT_ORDER = "INSERT INTO orders(user_id, book_id, term, fine, status) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_ORDER = "DELETE FROM orders WHERE id = ?";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders SET user_id = ?, book_id = ?, term = ?, fine = ?, status = ? WHERE id = ?";
    private static final String SQL_SELECT_ORDER = "SELECT * FROM orders WHERE id = ?";
    private static final String SQL_SELECT_BY_STATUS = "SELECT * FROM orders WHERE status = ? LIMIT ?, ?";
    private static final String SQL_SELECT_ORDERS = "SELECT * FROM orders LIMIT ?, ?";
    private static final String SQL_COUNT = "SELECT COUNT(*) FROM orders";
    private static final String SQL_STATUS_COUNT = "SELECT COUNT(*) FROM orders WHERE status = ?";
    private static final String SQL_USER_ORDERS = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC LIMIT ?, ?";
    private static final String SQL_SELECT_ORDER_INFO = "SELECT orders.id, orders.user_id, books.name, users.first_name, users.email, users.phone, orders.term, orders.order_date, orders.fine, orders.status FROM orders, books, users WHERE orders.user_id = users.id AND orders.book_id = books.id AND orders.id = ?";
    private static final String SQL_USER_ORDERS_COUNT = "SELECT COUNT(*) FROM orders WHERE user_id = ?";
    private static final String SQL_UPDATE_STATUS = "UPDATE orders, books SET orders.status = ?, books.count = books.count + ? WHERE orders.book_id = books.id AND orders.id = ?";
    public static final String SQL_PAY_FINE = "UPDATE orders SET fine = 0, status = 6 WHERE id = ?";
    public static final String SQL_ADD_FINE = "UPDATE orders SET fine = ?, status = 3 WHERE id = ?";

    private static OrderRepo orderRepo;

    public static OrderRepo getInstance(){
        if(orderRepo == null){
            orderRepo = new OrderRepo();
        }
        return orderRepo;
    }

    @Override
    public Order insert(Order sub) throws SQLException {
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)){
            prepareUpdate(statement, sub);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    sub.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating sub failed, no ID obtained.");
                }
            }
        }
        return sub;
    }

    @Override
    public int delete(int id) throws SQLException {
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_DELETE_ORDER)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        return id;
    }

    @Override
    public Order update(Order sub) throws SQLException {
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_UPDATE_ORDER)){
            prepareUpdate(statement, sub);
            statement.setInt(6, sub.getId());
            statement.executeUpdate();
        }
        return sub;
    }

    private void prepareUpdate(PreparedStatement statement, Order sub) throws SQLException{
        statement.setInt(1, sub.getUserId());
        statement.setInt(2, sub.getBookId());
        statement.setDate(3, sub.getTerm());
        statement.setLong(4, sub.getFine());
        statement.setInt(5, sub.getStatus().ordinal());
    }

    @Override
    public Order getOrder(int id) throws SQLException {
        OrderMapper mapper = new OrderMapper();
        try(Connection conn = DBCPDataSource.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_SELECT_ORDER)){
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                return mapper.getFromResultSet(res);
            }
        }
        return null;
    }

    @Override
    public List<Order> getOrders(int from, int to) throws SQLException{
        OrderMapper mapper = new OrderMapper();
        List<Order> sub = new ArrayList<>();
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_ORDERS)){
            statement.setInt(1, from);
            statement.setInt(2, to);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                sub.add(mapper.getFromResultSet(res));
            }
        }
        return sub;
    }

    @Override
    public List<Order> getUserOrders(int id, int from, int to) throws SQLException {
        OrderMapper mapper = new OrderMapper();
        List<Order> sub = new ArrayList<>();
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_USER_ORDERS)){
            statement.setInt(1, id);
            statement.setInt(2, from);
            statement.setInt(3, to);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                sub.add(mapper.getFromResultSet(res));
            }
        }
        return sub;
    }

    @Override
    public List<Order> getOrdersByStatus(Status status, int from, int to) throws SQLException{
        OrderMapper mapper = new OrderMapper();
        List<Order> sub = new ArrayList<>();
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_BY_STATUS)){
            statement.setInt(1, status.ordinal());
            statement.setInt(2, from);
            statement.setInt(3, to);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                sub.add(mapper.getFromResultSet(res));
            }
        }
        return sub;
    }
    @Override
    public Status updateStatus(int id, Status status, int bookValue) throws SQLException {
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_UPDATE_STATUS)){
            statement.setInt(1, status.ordinal());
            statement.setInt(2, bookValue);
            statement.setInt(3, id);
            statement.executeUpdate();
        }
        return status;
    }

    @Override
    public int payFine(int id) throws SQLException {
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_PAY_FINE)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        return id;
    }

    @Override
    public int addFine(int id, int fine) throws SQLException {
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_ADD_FINE)){
            statement.setInt(1, fine);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
        return fine;
    }

    @Override
    public int getStatusCount(Status status) throws SQLException {
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_STATUS_COUNT)){
            statement.setInt(1, status.ordinal());
            ResultSet res = statement.executeQuery();
            int count = 0;
            if(res.next()){
                count = res.getInt(1);
            }
            return count;
        }
    }

    @Override
    public int getCount() throws SQLException{
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_COUNT)){
            ResultSet res = statement.executeQuery();
            int count = 0;
            if(res.next()){
                count = res.getInt(1);
            }
            return count;
        }
    }

    @Override
    public OrderInfo getOrderInfo(int id) throws SQLException {
        OrderInfoMapper mapper = new OrderInfoMapper();
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT_ORDER_INFO)) {
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                return mapper.getFromResultSet(res);
            }
        }
        return null;
    }

    @Override
    public int getUserOrdersCount(int id) throws SQLException {
        try(Connection conn = DBCPDataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(SQL_USER_ORDERS_COUNT)){
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            int count = 0;
            if(res.next()){
                count = res.getInt(1);
            }
            return count;
        }
    }
}
