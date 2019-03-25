package com.libraryapp.libraryappclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LibraryApi {

    @GET("books")
    Call<List<Book>> getBooks();
}
