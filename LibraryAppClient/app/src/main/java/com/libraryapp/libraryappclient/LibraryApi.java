package com.libraryapp.libraryappclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LibraryApi {

    @GET("api/books/all")
    Call<List<Book>> getBooks();
}
