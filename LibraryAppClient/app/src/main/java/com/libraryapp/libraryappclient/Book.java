package com.libraryapp.libraryappclient;

public class Book {

    private String isbn;
    private String title;
    private String author;
    private String genre;
    private String description;
    private String bookCover;
    private double rating;
    private int stock;

    public Book(String isbn) {
        this.isbn = isbn;
    }

    public String getBookCover() {
        return bookCover;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getGenre() {
        return genre;
    }
    public String getDescription() {
        return description;
    }
    public double getRating() {
        return rating;
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
