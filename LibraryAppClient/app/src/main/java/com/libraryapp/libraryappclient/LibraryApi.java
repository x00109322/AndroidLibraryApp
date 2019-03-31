package com.libraryapp.libraryappclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LibraryApi {

    @GET("books/all")
    Call<List<Book>> getBooks();

    @GET("books/genre/{genre}")
    Call<List<Book>> getBooksByGenre(@Path("genre") String genre);

    @GET("books/author/{author}")
    Call<List<Book>> getBooksByAuthor(@Path("author") String author);

    @GET("books/booksOrderedByRating")
    Call<List<Book>> getBooksOrderedByRating();

    @GET("books/booksOrderedByTitle")
    Call<List<Book>> getBooksOrderedByTitle();

    @GET("books/booksOrderedByAuthor")
    Call<List<Book>> getBooksOrderedByAuthor();

    @GET("books/booksOrderedByGenre")
    Call<List<Book>> getBooksOrderedByGenre();
}
