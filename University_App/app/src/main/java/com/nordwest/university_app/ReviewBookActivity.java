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

    /*when GO BACK TO LIBRARY BUTTON is pressed Library Activity will get resumed which means that this button will have
    * same functionality as standard back button*/
    private View.OnClickListener mOnGoBackButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ReviewBookActivity.super.onBackPressed();

        }
    };

    /*when ADD NEW REVIEW button is pressed a window will popup allowing user to insert a new review on the book that was earlier chosen*/
    private View.OnClickListener mOnAddButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialog.setContentView(R.layout.custompop_up);
            dialog.show();

            /*here all buttons and txt fields on the dialog windows are going to be found and linked with the objects so that later they
             will perform corresponding actions */
            btnReviewedDone = dialog.findViewById(R.id.btnReviewedDone);
            closePopup = dialog.findViewById(R.id.closePopup);
            reviewContent = dialog.findViewById(R.id.reviewContent);

            /*when button DONE will be pressed the content of the review will be inserted into the database as a new record with ContentValue*/
            btnReviewedDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String reviewContentText = reviewContent.getText().toString();
                    ContentValues contentValues = new ContentValues();

                    try{
                        if (!TextUtils.isEmpty(reviewContentText)){
                            //new database helper object opened
                            db = openHelper.getWritableDatabase();
                            //get ready the content by inserting required
                            contentValues.put(Contract.ReviewEntry.BOOK_ID, bookISBN);
                            contentValues.put(Contract.ReviewEntry.REVIEW_TEXT, reviewContentText);
                            contentValues.put(Contract.ReviewEntry.USER_ID, Contract.StudentEntry.actualUserStudentID);
                            //content value inserted into database
                            db.insert(Contract.ReviewEntry.TABLE_REVIEWS_NAME, null, contentValues);
                            //the myReviewAddapter will be notified about the change which supposed to refresh the RecycleView
                            myReviewAddapter.notifyDataSetChanged();
                            //close the popup window
                            dialog.dismiss();
                            //Toast a message to inform the user about the record being successfully inserted
                            Toast.makeText(ReviewBookActivity.this, "New review added", Toast.LENGTH_SHORT).show();
                            //finish the activity
                            finish();
                            //restart the actual activity (ReviewActivity) which will display newly inserted review
                            startActivity(getIntent());
                        }else {
                            //In case that something goes wrong a message will be inserted informing the user that the review was not inserted
                            Toast.makeText(ReviewBookActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    /*As working with database sometimes goes wrong because of many reasons such as table structure changes, etc
                     a catch error will be handy when debugging theerror
                    * Therefore, a stack trace will be printed in the system files which should never be visibile to end users
                    * (for user experience and security purposes)*/
                    }catch (Exception e) {
                        e.printStackTrace();
                    }



                }
            });
            //when the close button (text) will be pressed the dialog must end
            closePopup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //end the dialog
                    dialog.dismiss();
                }
            });
        }
    };
    //this method simply should extract data from the ta
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
