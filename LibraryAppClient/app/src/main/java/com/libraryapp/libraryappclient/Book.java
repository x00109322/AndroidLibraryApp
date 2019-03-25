package com.libraryapp.libraryappclient;

import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("ISBN")
    private String isbn;

    private String title;
    private String author;
    private String genre;
    private String description;
    private double rating;
    private int stock;

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
}
