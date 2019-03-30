package com.libraryapp.libraryappclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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

public class MainActivity extends AppCompatActivity {

    private LibraryApi libraryApi;
    private RecyclerView bookRecyclerView;
    private BookAdapter bookAdapter;
    private RecyclerView.LayoutManager bookLayoutManager;
    private ArrayList<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createBookList();

    }

    public void createBookList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://libraryappserver2019.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        libraryApi = retrofit.create(LibraryApi.class);
        Call<List<Book>> call = libraryApi.getBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                List<Book> booksToCast = response.body();
                bookList = (ArrayList<Book>) booksToCast;
                buildRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                return;
            }
        });
    }

    public void buildRecyclerView() {
        bookRecyclerView = findViewById(R.id.bookRecyclerView);
        bookRecyclerView.setHasFixedSize(true);
        bookLayoutManager = new LinearLayoutManager(this);
        bookAdapter = new BookAdapter(bookList);

        bookRecyclerView.setLayoutManager(bookLayoutManager);
        bookRecyclerView.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //Intent intent = new Intent(this,SingleBookPage.class)
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.book_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                bookAdapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }
}

