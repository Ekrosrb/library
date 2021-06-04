package com.ekros.library.model.service;
import com.ekros.library.model.dao.config.DBCPDataSource;
import com.ekros.library.model.dao.impl.OrderRepo;
import com.ekros.library.model.entity.Order;
import com.ekros.library.model.entity.OrderInfo;
import com.ekros.library.model.entity.Status;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private final OrderRepo orderRepo;
    private final int page = Integer.parseInt(DBCPDataSource.prop.getProperty("page.size"));
    public OrderService(){
        orderRepo = OrderRepo.getInstance();
    }

    public Order addOrder(Order sub) throws SQLException {
        return orderRepo.insert(sub);
    }

    public List<Order> getUserOrders(int id, int from) throws SQLException {
        return orderRepo.getUserOrders(id, from, from+page);
    }

    public OrderInfo getOrderInfo(int id) throws SQLException {
        return orderRepo.getOrderInfo(id);
    }

    public int getUserOrdersCount(int id) throws SQLException {
        return orderRepo.getUserOrdersCount(id);
    }


    public Status updateStatus(int id, Status status) throws SQLException {
        int bookValue = 0;
        if(status == Status.ACCEPTED){
           bookValue = -1;
        }else if(status == Status.CLOSED){
            bookValue = 1;
        }

       return orderRepo.updateStatus(id, status, bookValue);
    }

    public List<Order> getOrdersByStatus(Status status, int from) throws SQLException {
        return orderRepo.getOrdersByStatus(status, from, from+page);
    }

    public int getStatusCount(Status status) throws SQLException {
        return orderRepo.getStatusCount(status);
    }

    public int payFine(int id) throws SQLException {
        return orderRepo.payFine(id);
    }


    public int addFine(int id, int fine) throws SQLException {
        return orderRepo.addFine(id, fine);
    }
}
