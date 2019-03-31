package com.libraryapp.libraryappclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String genre = intent.getStringExtra("genre");
        String isbn = intent.getStringExtra("isbn");
        String cover = intent.getStringExtra("book_cover_url");
        double rating = intent.getDoubleExtra("rating",0.0);
        String description = intent.getStringExtra("description");
        int stock = intent.getIntExtra("stock",0);


        TextView textViewTitle = findViewById(R.id.BookTitle);
        textViewTitle.setText(title);

        TextView textViewAuthor = findViewById(R.id.Author);
        textViewAuthor.setText(author);

        TextView textViewGenre = findViewById(R.id.Genre);
        textViewGenre.setText("Genre: "+ genre);

        TextView textViewISBN = findViewById(R.id.ISBN);
        textViewISBN.setText("ISBN: " + isbn);

        TextView textViewDescription = findViewById(R.id.Description);
        textViewDescription.setText(description);

        ImageView imageViewBookCover = findViewById(R.id.imageView);
        Picasso.get().load(cover).into(imageViewBookCover);

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating((float)rating);

        TextView textViewStock = findViewById(R.id.stock);
        textViewStock.setText("Stock: " + stock);
    }
}
