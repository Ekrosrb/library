package com.ekros.library.model.service;
import com.ekros.library.model.dao.config.DBCPDataSource;
import com.ekros.library.model.dao.impl.OrderRepo;
import com.ekros.library.model.entity.Order;
import com.ekros.library.model.entity.OrderInfo;
import com.ekros.library.model.entity.Status;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private final OrderRepo subRepo;
    private final int page = Integer.parseInt(DBCPDataSource.prop.getProperty("page.size"));
    public OrderService(){
        subRepo = OrderRepo.getInstance();
    }

    public void addSub(Order sub) throws SQLException {
        subRepo.insert(sub);
    }

    public List<Order> getSubs(int from) throws SQLException {
        return subRepo.getOrders(from, from+page);
    }

    public List<Order> getUserSubs(int id, int from) throws SQLException {
        return subRepo.getUserOrders(id, from, from+page);
    }

    public void updateSub(Order sub) throws SQLException {
        subRepo.update(sub);
    }

    public void deleteSub(int id) throws SQLException {
        subRepo.delete(id);
    }

    public Order getSub(int id) throws SQLException {
        return subRepo.getOrder(id);
    }

    public OrderInfo getOrderInfo(int id) throws SQLException {
        return subRepo.getOrderInfo(id);
    }

    public int getUserOrdersCount(int id) throws SQLException {
        return subRepo.getUserOrdersCount(id);
    }


    public void updateStatus(int id, Status status) throws SQLException {
        int bookValue = 0;
        if(status == Status.ACCEPTED){
           bookValue = -1;
        }else if(status == Status.CLOSED || status == Status.REJECTED){
            bookValue = 1;
        }

        subRepo.updateStatus(id, status, bookValue);
    }


    public List<Order> getOrdersByStatus(Status status, int from) throws SQLException {
        return subRepo.getOrdersByStatus(status, from, from+page);
    }

    public int getStatusCount(Status status) throws SQLException {
        return subRepo.getStatusCount(status);
    }




}
