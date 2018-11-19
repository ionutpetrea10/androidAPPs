package com.nordwest.university_app;

import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReviewBookActivity extends AppCompatActivity {

    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    public static String BOOK_ID_KEY = "BOOK_ID";
    Cursor cursor;
    String bookISBN;
    RecyclerView rv;
    List<ReviewHolder> retriedReviews = new ArrayList<>();
    TextView bookReviewed;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_activity);
        Bundle bundle = getIntent().getExtras();
        bookISBN = bundle.getString(BOOK_ID_KEY);
        bookReviewed = findViewById(R.id.bookReviewed);
        dialog = new Dialog(this);



        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();


        String ISBN, reviewTxt, author, reviewID, titleOfReviewedBook;

            cursor = db.rawQuery("SELECT _review_id_, _book_id_, _user_id_, _review_, _title_ FROM _book_, _reviews_ where _book_._ISBN_ = _reviews_._book_id_ AND  _reviews_._book_id_ =? ", new String[]{bookISBN});
            if (cursor != null) {
                //retriedReviews.clear();
                //rv.removeAllViewsInLayout();
                if (cursor.moveToFirst()) {
                    do {

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


        rv = findViewById(R.id.recycleReviews);

        MyReviewAddapter myReviewAddapter = new MyReviewAddapter(this, retriedReviews);
        rv.setAdapter(myReviewAddapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        Toast.makeText(this, bookISBN, Toast.LENGTH_SHORT).show();



    }

}
