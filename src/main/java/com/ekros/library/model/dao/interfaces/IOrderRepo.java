package com.ekros.library.model.dao.interfaces;

import com.ekros.library.model.entity.Order;
import com.ekros.library.model.entity.OrderInfo;
import com.ekros.library.model.entity.Status;

import java.sql.SQLException;
import java.util.List;

/**
 * IOrderRepo - database operations with orders table
 * @author ekros
 * @see Order - entity
 * @see OrderInfo - detail order info entity
 * */
public interface IOrderRepo extends IRepo<Order>{
    /**
     * Get record about order with transferred id
     * @param id - order id in db
     * @return the order record you are looking for
     * @throws SQLException for invalid data or connection error
     * */
    Order getOrder(int id) throws SQLException;
    /**
     * Get records about orders from range
     * @param from - range start
     * @param to - range end
     * @return list of orders
     * @throws SQLException for invalid data or connection error
     * */
    List<Order> getOrders(int from, int to) throws SQLException;
    /**
     * Get records about some user orders from range
     * @param from - range start
     * @param to - range end
     * @return list of user orders
     * @throws SQLException for invalid data or connection error
     * */
    List<Order> getUserOrders(int id, int from, int to) throws SQLException;
    /**
     * Get records about orders with a specific status from range
     * @param status - status of orders
     * @see Status
     * @param from - range start
     * @param to - range end
     * @return list of orders
     * @throws SQLException for invalid data or connection error
     * */
    List<Order> getOrdersByStatus(Status status, int from, int to) throws SQLException;
    /**
     * Get count of records about orders with a specific status from range
     * @param status - status of orders
     * @see Status
     * @return count of records
     * @throws SQLException for invalid data or connection error
     * */
    int getStatusCount(Status status) throws SQLException;
    /**
     * Get detail order info
     * @param id - order id in db
     * @return detail order info
     * @see OrderInfo
     * @throws SQLException for invalid data or connection error
     * */
    OrderInfo getOrderInfo(int id) throws SQLException;
    /**
     * Get count of records about all orders
     * @return count of records
     * @throws SQLException for invalid data or connection error
     * */
    int getCount() throws SQLException;
    /**
     * Get count of records about orders with specific user from range
     * @param id - user id
     * @see com.ekros.library.model.entity.User
     * @return count of user orders
     * @throws SQLException for invalid data or connection error
     * */
    int getUserOrdersCount(int id) throws SQLException;
    /**
     * Update order status and, if necessary, changes the number of books
     * @param id - order id in db
     * @param status - new status
     * @param bookValue - the value by which the number of books will change: count = count + bookValue
     * @see Status
     * @see com.ekros.library.model.entity.Book
     * @return new status
     * @throws SQLException for invalid data or connection error
     * */
    Status updateStatus(int id, Status status, int bookValue) throws SQLException;
    /**
     * Changes the fine value to 0 and the status to PAID when the fine is paid
     * @param id - order id in db
     * @see com.ekros.library.model.entity.User
     * @return id
     * @throws SQLException for invalid data or connection error
     * */
    int payFine(int id) throws SQLException;
    /**
     * Changes the fine value and the status to EXPIRED when the term is expired
     * @param id - order id in db
     * @see Status
     * @return new fine
     * @throws SQLException for invalid data or connection error
     * */
    int addFine(int id, int fine) throws SQLException;
}
