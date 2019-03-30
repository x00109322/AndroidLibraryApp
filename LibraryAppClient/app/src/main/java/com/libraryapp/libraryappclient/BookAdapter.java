package com.libraryapp.libraryappclient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private ArrayList<Book> bookList;
    private OnItemClickListener bookListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        bookListener = listener;
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        public ImageView bookImageView;
        public TextView bookTextViewTitle;
        public TextView bookTextViewAuthor;

        public BookViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            bookImageView = itemView.findViewById(R.id.cardBookCover);
            bookTextViewTitle = itemView.findViewById(R.id.cardBookTitle);
            bookTextViewAuthor = itemView.findViewById(R.id.cardBookAuthor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public BookAdapter(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }


    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_item, viewGroup, false);
        BookViewHolder bookViewHolder = new BookViewHolder(v, bookListener);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(BookViewHolder bookViewHolder, int i) {
        Book currentBook = bookList.get(i);

        Picasso.get().load(currentBook.getBookCover()).into(bookViewHolder.bookImageView);
        bookViewHolder.bookTextViewTitle.setText(currentBook.getTitle());
        bookViewHolder.bookTextViewAuthor.setText(currentBook.getAuthor());


    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

}
