package com.libraryapp.libraryappclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private TextView textViewResult;
    private Spinner orderBySpinner;
    private LibraryApi libraryApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult =findViewById(R.id.text_view_result);

        orderBySpinner = findViewById(R.id.order_by_spinner);

        orderBySpinner.setOnItemSelectedListener(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://libraryappserver.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        libraryApi = retrofit.create(LibraryApi.class);

        getBooks();
    }

    private void getBooks() {
        Call<List<Book>> call = libraryApi.getBooks();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(!response.isSuccessful()) {
                    textViewResult.setText("Code: "+response.code());
                    return;
                }

                List<Book> books = response.body();

                for(Book book : books) {
                    String content = "";
                    content += "Title: " + book.getTitle() + "\n";
                    content += "Author: " + book.getAuthor() + "\n";
                    content += "Genre: " + book.getGenre() + "\n";
                    content += "Rating: " + book.getRating() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }


    public void getBooksByOrder(View v, String selectedOrder) {
        Call<List<Book>> call = libraryApi.getBooksOrderedByTitle();
        textViewResult = findViewById(R.id.text_view_result);

        switch (selectedOrder) {
            case "Author":
                call = libraryApi.getBooksOrderedByAuthor();
                break;
            case "Genre":
                call = libraryApi.getBooksOrderedByGenre();
                break;
            case "Rating":
                call = libraryApi.getBooksOrderedByRating();
                break;
        }

            call.enqueue(new Callback<List<Book>>() {

                @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(!response.isSuccessful()) {
                    textViewResult.setText("Code: "+response.code());
                    return;
                }

                List<Book> books = response.body();

                textViewResult.setText("");
                for(Book book : books) {
                    String content = "";
                    content += "Title: " + book.getTitle() + "\n";
                    content += "Author: " + book.getAuthor() + "\n";
                    content += "Genre: " + book.getGenre() + "\n";
                    content += "Rating: " + book.getRating() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choice = parent.getItemAtPosition(position).toString();
        getBooksByOrder(view,choice);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
