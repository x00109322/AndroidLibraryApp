package com.libraryapp.libraryappclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class SingleBookPage extends AppCompatActivity {

    private Button checkOutButton;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_book_page);
        setTitle("Full Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        checkOutButton = findViewById(R.id.checkOutButton);
        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCheckOutToast(title);
            }
        });
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String genre = intent.getStringExtra("genre");
        String isbn = intent.getStringExtra("isbn");
        String cover = intent.getStringExtra("book_cover_url");
        double rating = intent.getDoubleExtra("rating",0.0);
        String description = intent.getStringExtra("description");
        int stockInt = intent.getIntExtra("stock",0);
        String stock = Integer.toString(stockInt);

        TextView textViewTitle = findViewById(R.id.BookTitle);
        textViewTitle.setText(title);

        TextView textViewAuthor = findViewById(R.id.Author);
        textViewAuthor.setText(author);

        TextView textViewGenre = findViewById(R.id.GenreValue);
        textViewGenre.setText(genre);

        TextView textViewISBN = findViewById(R.id.ISBNValue);
        textViewISBN.setText(isbn);

        TextView textViewDescription = findViewById(R.id.Description);
        textViewDescription.setText(description);
        textViewDescription.setMovementMethod(new ScrollingMovementMethod());

        ImageView imageViewBookCover = findViewById(R.id.imageView);
        Picasso.get().load(cover).into(imageViewBookCover);

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating((float)rating);

        TextView textViewStock = findViewById(R.id.stockValue);
        textViewStock.setText(stock);
    }

    public void showCheckOutToast(String title) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.check_out_toast_layout,(ViewGroup) findViewById(R.id.checkOutToastRoot));

        TextView toastText = layout.findViewById(R.id.checkOutToastText);
        toastText.setText("You checked out: "+title);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }
}
