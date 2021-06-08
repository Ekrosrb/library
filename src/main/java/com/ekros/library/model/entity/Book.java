package com.ekros.library.model.entity;

import java.util.Objects;

/**
 * Book - entity of the book, for storing information with which the user can interact.
 * @author ekros
 * */
public class Book {
    private int id;
    private String name;
    private String author;
    private String edition;
    private String description;
    private String descriptionRu;
    private int count;

    public Book() {
    }

    public Book(String name, String author, String edition, String description, String descriptionRu, int count) {
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.description = description;
        this.descriptionRu = descriptionRu;
        this.count = count;
    }

    public Book(int id, String name, String author, String edition, String description, String descriptionRu, int count) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return count == book.count && name.equals(book.name) && author.equals(book.author) &&
                edition.equals(book.edition) && description.equals(book.description) &&
                descriptionRu.equals(book.descriptionRu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, edition, description, descriptionRu, count);
    }
}
