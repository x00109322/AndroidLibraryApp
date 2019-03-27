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

    // @GET("books/title/{title}")
    // @GET("books/booksOrderedByRating")
    // @GET("books/booksOrderedByTitle")
    // @GET("books/booksOrderedByAuthor")
    // @GET("books/author/{author}")
    // @GET("books/rating/{rating}")
}
