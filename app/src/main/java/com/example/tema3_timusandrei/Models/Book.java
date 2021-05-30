package com.example.tema3_timusandrei.Models;

public class Book {
    private String title;
    private String author;
    private String details;
    public Book() {
        title = "";
        author = "";
        details = "";
    }
    public Book(String title, String author, String details) {
        this.title = title;
        this.author = author;
        this.details = details;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() { return title; }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public String getDetails() {
        return details;
    }
}
