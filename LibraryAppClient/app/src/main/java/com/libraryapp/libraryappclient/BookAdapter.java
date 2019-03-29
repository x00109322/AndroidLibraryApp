package com.libraryapp.libraryappclient;

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

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        public ImageView bookImageView;
        public TextView bookTextViewTitle;
        public TextView bookTextViewAuthor;

        public BookViewHolder(View itemView) {
            super(itemView);
            bookImageView = itemView.findViewById(R.id.cardBookCover);
            bookTextViewTitle = itemView.findViewById(R.id.cardBookTitle);
            bookTextViewAuthor = itemView.findViewById(R.id.cardBookAuthor);
        }
    }

    public BookAdapter(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }


    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_item,viewGroup,false);
        BookViewHolder bookViewHolder = new BookViewHolder(v);
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

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
