package com.ekros.library.model.service;
import com.ekros.library.model.dao.config.DBCPDataSource;
import com.ekros.library.model.dao.impl.OrderRepo;
import com.ekros.library.model.entity.Order;
import com.ekros.library.model.entity.OrderInfo;

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

    public List<Order> getPendingSubs(int from) throws SQLException {
        return subRepo.getPendingOrders(from, from+page);
    }


    public List<Order> getUserSubs(int id, int from) throws SQLException {
        return subRepo.getUserOrders(id, from, from+page);
    }

    public List<Order> getExpiredOrders(int from) throws SQLException{
        return subRepo.getExpiredOrders(from, from+page);
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




}
