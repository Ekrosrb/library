package com.ekros.library.model.dao;

import com.ekros.library.model.dao.config.DBCPDataSource;
import com.ekros.library.model.dao.impl.BookRepo;
import com.ekros.library.model.dao.impl.OrderRepo;
import com.ekros.library.model.dao.impl.UserRepo;
import com.ekros.library.model.dao.interfaces.IBookRepo;
import com.ekros.library.model.dao.interfaces.IOrderRepo;
import com.ekros.library.model.dao.interfaces.IUserRepo;
import com.ekros.library.model.entity.*;
import org.junit.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class OrderRepoTest {

    private static IUserRepo userRepo;
    private static IBookRepo bookRepo;
    private IOrderRepo repo;

    public OrderRepoTest() {
        repo = OrderRepo.getInstance();
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        truncate();
        userRepo = UserRepo.getInstance();
        bookRepo = BookRepo.getInstance();
        userRepo.insert(new User("Test", "Test", "Test@mail.com", "Test", new Date(13513656L), "12345", Role.USER, false));
        userRepo.insert(new User("Test2", "Test2", "Test2@mail.com", "Test2", new Date(13513656L), "12345", Role.USER, false));
        bookRepo.insert(new Book("Book", "Author", "Edition", "Desc", "descRu", 10));
        bookRepo.insert(new Book("Book2", "Author2", "Edition2", "Desc2", "descRu2", 10));
    }

    @AfterClass
    public static void afterClass() throws Exception {
        truncate();
    }

    private static void truncate() throws SQLException {
        try(Connection conn = DBCPDataSource.getConnection()){
            conn.setAutoCommit(false);
            conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            conn.createStatement().executeUpdate("TRUNCATE TABLE users");
            conn.createStatement().executeUpdate("TRUNCATE TABLE books");
            conn.createStatement().executeUpdate("TRUNCATE TABLE orders");
            conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
            conn.commit();
            conn.setAutoCommit(true);
        }
    }

    @After
    public void tearDown() throws Exception {
        try(Connection conn = DBCPDataSource.getConnection()){
            conn.setAutoCommit(false);
            conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            conn.createStatement().executeUpdate("TRUNCATE TABLE orders");
            conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
            conn.commit();
            conn.setAutoCommit(true);
        }
    }

    private static Order newOrderInstance(int userId, int bookId, Status status){
        return new Order(userId, bookId, new Date(13513656L), new Date(13513656L), 0, status);
    }

    @Test
    public void insertOrderTest() throws SQLException {
        Order order = newOrderInstance(1, 1, Status.PENDING);
        repo.insert(order);
        Assert.assertEquals(order.getId(), 1);
    }

    @Test
    public void getOrderByIdTest() throws SQLException {
        Order order = newOrderInstance(1, 1, Status.PENDING);
        repo.insert(order);
        order = repo.getOrder(order.getId());
        Assert.assertEquals(order.getId(), 1);
    }

    @Test
    public void getOrderByUserIdAndBookIdTest() throws SQLException {
        Order order = newOrderInstance(1, 1, Status.PENDING);
        repo.insert(order);
        List<Order> orders = repo.getOrderByBookAndUserId(1, 1);
        Assert.assertEquals(orders.size(), 1);
        Assert.assertEquals(orders.get(0).getUserId(), order.getUserId());
        Assert.assertEquals(orders.get(0).getBookId(), order.getBookId());
    }

    @Test
    public void updateOrderTest() throws SQLException {
        Order order = newOrderInstance(1, 1, Status.PENDING);
        repo.insert(order);
        order.setFine(20);
        order.setStatus(Status.ON_USE);
        repo.update(order);
        order = repo.getOrder(order.getId());
        Assert.assertEquals(order.getFine(), 20);
        Assert.assertEquals(order.getStatus(), Status.ON_USE);
    }

    @Test
    public void addFineTest() throws SQLException {
        Order order = newOrderInstance(1, 1, Status.PENDING);
        repo.insert(order);
        repo.addFine(order.getId(), 50);
        order = repo.getOrder(order.getId());
        Assert.assertEquals(order.getFine(), 50);
        Assert.assertEquals(order.getStatus(), Status.EXPIRED);
    }

    @Test
    public void payFineTest() throws SQLException {
        Order order = newOrderInstance(1, 1, Status.PENDING);
        repo.insert(order);
        repo.payFine(order.getId());
        order = repo.getOrder(order.getId());
        Assert.assertEquals(order.getFine(), 0);
        Assert.assertEquals(order.getStatus(), Status.PAID);
    }

    @Test
    public void deleteOrderTest() throws SQLException{
        Order order = newOrderInstance(1, 1, Status.PENDING);
        repo.insert(order);
        repo.delete(1);
        Assert.assertNull(repo.getOrder(1));

    }

    @Test
    public void updateStatusTest() throws SQLException {
        Order order = newOrderInstance(1, 1, Status.PENDING);
        repo.insert(order);
        repo.updateStatus(order.getId(), Status.ACCEPTED, -1);
        order = repo.getOrder(order.getId());
        Assert.assertEquals(order.getStatus(), Status.ACCEPTED);
        Assert.assertEquals(bookRepo.getBookById(1).getCount(), 9);
    }

    @Test
    public void orderInfoTest() throws SQLException {
        Order order = newOrderInstance(1, 1, Status.PENDING);
        repo.insert(order);
        OrderInfo orderInfo = repo.getOrderInfo(order.getId());
        Assert.assertNotNull(orderInfo);
        Assert.assertEquals(orderInfo.getUserId(), 1);
        Assert.assertEquals(orderInfo.getId(), 1);
        Assert.assertEquals(orderInfo.getUserName(), "Test");
        Assert.assertEquals(orderInfo.getPhone(), "12345");
        Assert.assertEquals(orderInfo.getBookName(), "Book");
        Assert.assertEquals(orderInfo.getEmail(), "Test@mail.com");
    }

    @Test
    public void countAndGetOrdersTest() throws SQLException {
        Order order = newOrderInstance(1, 1, Status.PENDING);
        Order order2 = newOrderInstance(2, 2, Status.PENDING);
        Order order3 = newOrderInstance(1, 2, Status.CLOSED);
        repo.insert(order);
        repo.insert(order2);
        repo.insert(order3);
        int count = repo.getCount();
        int statusCount = repo.getStatusCount(Status.PENDING);
        int userCount = repo.getUserOrdersCount(1);
        List<Order> orderList = repo.getOrders(0, 20);
        List<Order> statusOrder = repo.getOrdersByStatus(Status.PENDING, 0, 20);
        List<Order> userOrders = repo.getUserOrders(1, 0, 20);
        Assert.assertEquals(count, orderList.size());
        Assert.assertEquals(statusOrder.size(), statusCount);
        Assert.assertEquals(statusOrder.get(0).getStatus(), Status.PENDING);
        Assert.assertEquals(statusOrder.get(1).getStatus(), Status.PENDING);
        Assert.assertEquals(userOrders.size(), userCount);
        Assert.assertEquals(userOrders.get(0).getUserId(), 1);
        Assert.assertEquals(userOrders.get(1).getUserId(), 1);
    }

}
