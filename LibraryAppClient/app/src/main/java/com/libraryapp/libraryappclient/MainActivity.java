package com.libraryapp.libraryappclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

    private LibraryApi libraryApi;
    private RecyclerView bookRecyclerView;
    private RecyclerView.Adapter bookAdapter;
    private RecyclerView.LayoutManager bookLayoutManager;
    private ArrayList<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://libraryappserver2019.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        libraryApi = retrofit.create(LibraryApi.class);

        // get list of all books
        Call<List<Book>> call = libraryApi.getBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(!response.isSuccessful()) {
                    return;
                }

                List<Book> booksToCast = response.body();
                books = (ArrayList<Book>)booksToCast;

                bookAdapter = new BookAdapter(books);

                bookRecyclerView.setLayoutManager(bookLayoutManager);
                bookRecyclerView.setAdapter(bookAdapter);
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                return;
            }
        });

        bookRecyclerView = findViewById(R.id.bookRecyclerView);
        bookRecyclerView.setHasFixedSize(true);
        bookLayoutManager = new LinearLayoutManager(this);


    }

}
