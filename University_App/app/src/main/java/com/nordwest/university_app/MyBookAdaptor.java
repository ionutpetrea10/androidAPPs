package com.nordwest.university_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyBookAdaptor extends RecyclerView.Adapter<MyBookAdaptor.myViewHolder> {

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
    public void onBindViewHolder(myViewHolder holder, final int position) {
        holder.book_ISBN.setText(mBooks.get(position).getBookISBN());
        holder.book_title.setText(mBooks.get(position).getBookTitle());
        holder.book_author.setText(mBooks.get(position).getBookAuthor());
        holder.book_edition.setText(mBooks.get(position).getBookEdition());
        holder.btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "click at position" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView book_ISBN, book_title,book_author, book_edition;
        Button btnReview;

    public myViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        book_ISBN = itemView.findViewById(R.id.book_ISBN);
        book_title = itemView.findViewById(R.id.book_title);
        book_author = itemView.findViewById(R.id.book_author);
        book_edition = itemView.findViewById(R.id.book_edition);
        btnReview = itemView.findViewById(R.id.btnReview);
    }

        @Override
        public void onClick(View view) {

        }
    }
}
