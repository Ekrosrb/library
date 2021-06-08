package com.ekros.library.model.entity;

import java.sql.Date;
import java.util.Objects;

/**
 * Order - entity of order, which users leave when they want to borrow a book.
 * @author ekros
 * @see Status
 * */
public class Order {
    private int id;
    private int userId;
    private int bookId;
    private Date term;
    private Date orderDate;
    private long fine;
    private Status status;

    public Order() {
    }

    public Order(int id, int userId, int bookId, Date term, Date orderDate, long fine, Status status) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.term = term;
        this.orderDate = orderDate;
        this.fine = fine;
        this.status = status;
    }

    public Order(int userId, int bookId, Date term, Date orderDate, long fine, Status status) {
        this.userId = userId;
        this.bookId = bookId;
        this.term = term;
        this.orderDate = orderDate;
        this.fine = fine;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public long getFine() {
        return fine;
    }

    public void setFine(long fine) {
        this.fine = fine;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return userId == order.userId && bookId == order.bookId && fine == order.fine && term.equals(order.term) && orderDate.equals(order.orderDate) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, bookId, term, orderDate, fine, status);
    }
}
