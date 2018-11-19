package com.nordwest.university_app;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyReviewAddapter extends RecyclerView.Adapter<MyReviewAddapter.myReviewViewHolder> {
    Context context;
    List<ReviewHolder> mReviews;
    Dialog dialog;
    Button btnReviewedDone;
    TextView closePopup;

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
        dialog = new Dialog(context);

        holder.btnADDReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.setContentView(R.layout.custompop_up);
                dialog.show();

                btnReviewedDone = dialog.findViewById(R.id.btnReviewedDone);
                closePopup = dialog.findViewById(R.id.closePopup);

                btnReviewedDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
                    }
                });
                closePopup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });



    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }


    public class myReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView reviewID, bookISBN,review_author, reviewText;
        Button btnADDReview;

        public myReviewViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            review_author = itemView.findViewById(R.id.review_author);
            bookISBN = itemView.findViewById(R.id.reviewed_ISBN);
            reviewID = itemView.findViewById(R.id.review_ID);
            reviewText = itemView.findViewById(R.id.reviewOfTheBook);
            btnADDReview = itemView.findViewById(R.id.btnAddReview);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
