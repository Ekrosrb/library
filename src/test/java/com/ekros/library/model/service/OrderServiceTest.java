package com.ekros.library.model.service;

import com.ekros.library.model.dao.impl.OrderRepo;
import com.ekros.library.model.entity.Order;
import com.ekros.library.model.entity.OrderInfo;
import com.ekros.library.model.entity.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    private final OrderService orderService = new OrderService();
    private OrderRepo orderRepo;

    @Before
    public void setUp() throws Exception {
        orderRepo = mock(OrderRepo.class);
        Whitebox.setInternalState(orderService, "orderRepo", orderRepo);
    }

    private static Order newOrderInstance(int userId, int bookId, Status status){
        return new Order(userId, bookId, new Date(13513656L), new Date(13513656L), 0, status);
    }

    @Test
    public void addOrder() throws SQLException {
        Order order = newOrderInstance(1, 1, Status.PENDING);
        when(orderRepo.getOrderByBookAndUserId(anyInt(), anyInt())).thenReturn(Collections.singletonList(newOrderInstance(1, 1, Status.PENDING)));
        Assert.assertThrows(SQLException.class, () -> orderService.addOrder(order));
        when(orderRepo.getOrderByBookAndUserId(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        when(orderRepo.insert(order)).thenReturn(order);
        Assert.assertEquals(order, orderService.addOrder(order));
    }

    @Test
    public void getUserOrders() throws SQLException {
        Order order = newOrderInstance(1, 1, Status.PENDING);
        when(orderRepo.getUserOrders(anyInt(), anyInt(), anyInt())).thenReturn(Collections.singletonList(order));
        Assert.assertEquals(order, orderService.getUserOrders(1, 0).get(0));
    }

    @Test
    public void getOrderInfo() throws SQLException {
        OrderInfo info = new OrderInfo();
        when(orderRepo.getOrderInfo(anyInt())).thenReturn(info);
        Assert.assertEquals(info, orderService.getOrderInfo(1));
    }

    @Test
    public void getUserOrdersCount() throws SQLException {
        when(orderRepo.getUserOrdersCount(anyInt())).thenReturn(5);
        Assert.assertEquals(5, orderService.getUserOrdersCount(1));
    }

    @Test
    public void updateStatus() throws SQLException {
        when(orderRepo.updateStatus(1, Status.ON_USE, 0)).thenReturn(Status.ON_USE);
        when(orderRepo.updateStatus(1, Status.ACCEPTED, -1)).thenReturn(Status.ACCEPTED);
        when(orderRepo.updateStatus(1, Status.CLOSED, 1)).thenReturn(Status.CLOSED);
        Assert.assertEquals(Status.ON_USE, orderService.updateStatus(1, Status.ON_USE));
        Assert.assertEquals(Status.ACCEPTED, orderService.updateStatus(1, Status.ACCEPTED));
        Assert.assertEquals(Status.CLOSED, orderService.updateStatus(1, Status.CLOSED));
    }

    @Test
    public void getOrdersByStatus() throws SQLException {
        Order order = newOrderInstance(1, 1, Status.PENDING);
        when(orderRepo.getOrdersByStatus(Status.PENDING, 0, 20)).thenReturn(Collections.singletonList(order));
        Assert.assertEquals(order, orderService.getOrdersByStatus(Status.PENDING, 0).get(0));
    }

    @Test
    public void getStatusCount() throws SQLException {
        when(orderRepo.getStatusCount(any())).thenReturn(5);
        Assert.assertEquals(5, orderService.getStatusCount(Status.REJECTED));
    }

    @Test
    public void payFine() throws SQLException {
        when(orderRepo.payFine(1)).thenReturn(1);
        Assert.assertEquals(1, orderService.payFine(1));
    }

    @Test
    public void addFine() throws SQLException {
        int fine = 50;
        when(orderRepo.addFine(3, fine)).thenReturn(fine);
        Assert.assertEquals(fine, orderService.addFine(3, fine));
    }
}
