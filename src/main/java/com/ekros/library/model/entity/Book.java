package com.ekros.library.model.entity;

import java.sql.Blob;

public class Book {
    private int id;
    private Blob img;
    private String name;
    private String author;
    private String edition;
    private String description;
    private String descriptionRu;
    private int count;

    public Book() {
    }

    public Book(int id, Blob img, String name, String author, String edition, String description, String descriptionRu, int count) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.description = description;
        this.descriptionRu = descriptionRu;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Blob getImg() {
        return img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
