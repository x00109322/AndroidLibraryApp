package com.libraryapp.libraryappclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SingleBookPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_book_page);
        setTitle("Full Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
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
}
