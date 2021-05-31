package com.ekros.library.model.entity;

import java.sql.Date;

public class Order {
    private int id;
    private int userId;
    private int bookId;
    private Date term;
    private long fine;
    private Status status;

    public Order() {
    }

    public Order(int id, int userId, int bookId, Date term, long fine, Status status) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.term = term;
        this.fine = fine;
        this.status = status;
    }

    public Order(int userId, int bookId, Date term, long fine, Status status) {
        this.userId = userId;
        this.bookId = bookId;
        this.term = term;
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
}
