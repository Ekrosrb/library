package com.ekros.library.model.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * OrderInfo - entity store detail information about order.
 * @author ekros
 * */
public class OrderInfo implements Serializable {
    private int id;
    private int userId;
    private String bookName;
    private String userName;
    private String email;
    private String phone;
    private Date term;
    private Date orderDate;
    private long fine;
    private Status status;

    public OrderInfo() {
    }

    public OrderInfo(int id, int userId, String bookName, String userName, String email, String phone, Date term, Date orderDate, long fine, Status status) {
        this.id = id;
        this.userId = userId;
        this.bookName = bookName;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.term = term;
        this.orderDate = orderDate;
        this.fine = fine;
        this.status = status;
    }

    public OrderInfo(int userId, String bookName, String userName, String email, String phone, Date term, Date orderDate, long fine, Status status) {
        this.userId = userId;
        this.bookName = bookName;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Status getStatus() {
        return status;
    }

    public long getFine() {
        return fine;
    }

    public void setFine(long fine) {
        this.fine = fine;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
