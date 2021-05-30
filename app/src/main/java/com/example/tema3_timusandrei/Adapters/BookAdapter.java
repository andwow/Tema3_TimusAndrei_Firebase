package com.example.tema3_timusandrei.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema3_timusandrei.Interfaces.OnBookItemClick;
import com.example.tema3_timusandrei.Models.Book;
import com.example.tema3_timusandrei.R;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Book> bookList;
    OnBookItemClick onBookItemClick;

    public BookAdapter(ArrayList<Book> entertainmentList, OnBookItemClick onMovieItemClick) {
        this.bookList = entertainmentList;
        this.onBookItemClick = onMovieItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.book, parent, false);
        BookViewHolder bookViewHolder = new BookViewHolder(view);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Book movie = (Book) bookList.get(position);
        ((BookViewHolder) holder).bind(movie);

    }

    @Override
    public int getItemCount() {
        return this.bookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView author;
        private View view;

        BookViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.book_title);
            author = view.findViewById(R.id.book_author);
            this.view = view;
        }

        void bind(Book book) {
            title.setText(book.getTitle());
            author.setText(book.getAuthor());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onBookItemClick != null) {
                        onBookItemClick.onClick(book);
                    }
                }
            });
        }


    }
}