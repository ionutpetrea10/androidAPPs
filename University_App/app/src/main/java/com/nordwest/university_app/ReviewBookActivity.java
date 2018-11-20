package com.nordwest.university_app;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReviewBookActivity extends AppCompatActivity {

    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    public static String BOOK_ID_KEY = "BOOK_ID";
    public static String bookISBN;
    Cursor cursor;
    RecyclerView rv;
    List<ReviewHolder> retriedReviews = new ArrayList<>();
    TextView bookReviewed;
    Button btnADDReview, goBackLibrary;
    Dialog dialog;
    Button btnReviewedDone;
    TextView closePopup, reviewContent;
    MyReviewAddapter myReviewAddapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_activity);
        Bundle bundle = getIntent().getExtras();
        bookISBN = bundle.getString(BOOK_ID_KEY);
        bookReviewed = findViewById(R.id.bookReviewed);
        btnADDReview = findViewById(R.id.addNewReview);
        goBackLibrary = findViewById(R.id.goBackLibrary);
        dialog = new Dialog(this);



        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();

        btnADDReview.setOnClickListener(mOnAddButtonClickListener);
        goBackLibrary.setOnClickListener(mOnGoBackButtonClickListener);
        callForData();

        rv = findViewById(R.id.recycleReviews);
        rv.setNestedScrollingEnabled(false);
         myReviewAddapter = new MyReviewAddapter(this, retriedReviews);
        rv.setAdapter(myReviewAddapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }
    private View.OnClickListener mOnGoBackButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent goToLibrary = new Intent(ReviewBookActivity.this, Library.class);
            startActivity(goToLibrary);
        }
    };
    private View.OnClickListener mOnAddButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialog.setContentView(R.layout.custompop_up);
            dialog.show();

            btnReviewedDone = dialog.findViewById(R.id.btnReviewedDone);
            closePopup = dialog.findViewById(R.id.closePopup);
            reviewContent = dialog.findViewById(R.id.reviewContent);
            btnReviewedDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String reviewContentText = reviewContent.getText().toString();
                    ContentValues contentValues = new ContentValues();

                    try{
                        if (!TextUtils.isEmpty(reviewContentText)){

                            /*insert into _reviews_ (_review_,_user_id_,_book_id_) values ("Nice book! Recomanded.", "6", "1260440214")*/
                            db = openHelper.getWritableDatabase();
                            contentValues.put(Contract.ReviewEntry.BOOK_ID, bookISBN);
                            contentValues.put(Contract.ReviewEntry.REVIEW_TEXT, reviewContentText);
                            contentValues.put(Contract.ReviewEntry.USER_ID, Contract.StudentEntry.actualUserStudentID);
                            db.insert(Contract.ReviewEntry.TABLE_REVIEWS_NAME, null, contentValues);
                            myReviewAddapter.notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(ReviewBookActivity.this, "New review added", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(getIntent());
                        }else {
                            Toast.makeText(ReviewBookActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }



                }
            });
            closePopup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }
    };

    public void callForData (){
        cursor = db.rawQuery("SELECT _review_id_, _book_id_, _user_id_, _review_, _title_ FROM _book_, _reviews_ where _book_._ISBN_ = _reviews_._book_id_ AND  _reviews_._book_id_ =? ", new String[]{bookISBN});
        if (cursor != null) {
            //retriedReviews.clear();
            //rv.removeAllViewsInLayout();
            if (cursor.moveToFirst()) {
                do {
                    String ISBN, reviewTxt, author, reviewID;
                    ISBN = cursor.getString(cursor.getColumnIndex("_book_id_"));
                    reviewTxt = cursor.getString(cursor.getColumnIndex("_review_"));
                    author = cursor.getString(cursor.getColumnIndex("_user_id_"));
                    reviewID = cursor.getString(cursor.getColumnIndex("_review_id_"));
                    bookReviewed.setText(cursor.getString(cursor.getColumnIndex("_title_")));;
                    retriedReviews.add(new ReviewHolder(reviewID,ISBN,author,"\" "+reviewTxt + "\""));
                } while (cursor.moveToNext());

            } else {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();

            }
        }
    }

}
