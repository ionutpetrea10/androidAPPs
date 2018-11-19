package com.nordwest.university_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyBookAdaptor extends RecyclerView.Adapter<MyBookAdaptor.myViewHolder>  {

    Context context;
    List<MyViewBookHolder> mBooks;

    public MyBookAdaptor(Context context, List<MyViewBookHolder> mBooks) {
        this.context = context;
        this.mBooks = mBooks;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.model_book, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        holder.book_ISBN.setText(mBooks.get(position).getBookISBN());
        holder.book_title.setText(mBooks.get(position).getBookTitle());
        holder.book_author.setText(mBooks.get(position).getBookAuthor());
        holder.book_edition.setText(mBooks.get(position).getBookEdition());

        holder.btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookISBN = mBooks.get(position).getBookISBN();
                context.startActivity(new Intent(context, ReviewBookActivity.class).putExtra(ReviewBookActivity.BOOK_ID_KEY, bookISBN));
               // holder.searchedBook.setText("");
                //Toast.makeText(context, "click at position" + mBooks.get(position).getBookISBN(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView book_ISBN, book_title,book_author, book_edition, searchedBook;
        Button btnReview;

    public myViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        book_ISBN = itemView.findViewById(R.id.book_ISBN);
        book_title = itemView.findViewById(R.id.review_ID);
        book_author = itemView.findViewById(R.id.book_author);
        book_edition = itemView.findViewById(R.id.reviewOfTheBook);
        btnReview = itemView.findViewById(R.id.btnReview);
        searchedBook = itemView.findViewById(R.id.searchedBook);

    }

        @Override
        public void onClick(View view) {

        }
    }
}

