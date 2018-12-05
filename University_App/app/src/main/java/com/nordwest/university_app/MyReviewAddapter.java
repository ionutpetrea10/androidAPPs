package com.nordwest.university_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyReviewAddapter extends RecyclerView.Adapter<MyReviewAddapter.myReviewViewHolder> {
    Context context;
    List<ReviewHolder> mReviews;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;

    public MyReviewAddapter(Context context, List<ReviewHolder> mReviews) {
        this.context = context;
        this.mReviews = mReviews;
    }

    @Override
    public myReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.review_card_model, parent, false);
        return new myReviewViewHolder(v);    }

    @Override
    public void onBindViewHolder(final myReviewViewHolder holder, int position) {
        holder.review_author.setText(mReviews.get(position).getReviewAuthor());
        holder.reviewID.setText(mReviews.get(position).getReviewID());
        holder.bookISBN.setText(mReviews.get(position).getBookISBN());
        holder.reviewText.setText(mReviews.get(position).getReviewText());
        openHelper = new DatabaseHelper(context);
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }


    public class myReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView reviewID, bookISBN,review_author, reviewText;

        public myReviewViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            review_author = itemView.findViewById(R.id.startingTimeText);
            bookISBN = itemView.findViewById(R.id.dateClass);
            reviewID = itemView.findViewById(R.id.roomClass);
            reviewText = itemView.findViewById(R.id.subjectClass);
            
        }

        @Override
        public void onClick(View view) {

        }
    }
}
