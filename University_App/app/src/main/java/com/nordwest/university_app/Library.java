package com.nordwest.university_app;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Library extends AppCompatActivity {

    MyBookAdaptor myBookAdaptor;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    public static Button cancelReservation;
    Button btnSearch;
    EditText searchedBook;
    Cursor cursor;
    RecyclerView rv;

    ArrayList al = new ArrayList();
    List<MyViewBookHolder> retriedBooks = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        /*Creates the link between layout widgets (elements) and activity objects  */
        btnSearch = findViewById(R.id.buttonSearch);
        cancelReservation = findViewById(R.id.cancelReservation);
        searchedBook = findViewById(R.id.searchedBook);

        rv = findViewById(R.id.rvBooks);
        myBookAdaptor = new MyBookAdaptor(this, retriedBooks);
        rv.setLayoutManager(new LinearLayoutManager(this));


        btnSearch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retriedBooks.clear();

                String choice, ISBN, title, author, edition;
                choice = searchedBook.getText().toString().trim();



                cursor = db.rawQuery("SELECT _ISBN_, _title_, _edition_, (_fn_ || \" \" || _sn_) as _author_\n" +
                        "FROM  _book_ , _author_, _has_written_\n" +
                        "where _book_._ISBN_ = _has_written_._book_id_ and _author_._author_id_ = _has_written_._author_id_ and _book_._catergory_type_ =? ", new String[]{choice});
                if (cursor != null && cursor.moveToFirst()) {

                    if(Contract.StudentEntry.student_has_reservation){
                        cancelReservation.setVisibility(View.VISIBLE);
                    }
                        do {
                            ISBN = cursor.getString(cursor.getColumnIndex("_ISBN_"));
                            title = cursor.getString(cursor.getColumnIndex("_title_"));
                            author = cursor.getString(cursor.getColumnIndex("_author_"));
                            edition = cursor.getString(cursor.getColumnIndex("_edition_"));

                            retriedBooks.add(new MyViewBookHolder(ISBN,title,author,edition));

                            al.add(cursor.getString(cursor.getColumnIndex("_title_")) + " " + cursor.getString(cursor.getColumnIndex("_ISBN_")));
                            cursor.toString();
                            rv.setAdapter(myBookAdaptor);
                        } while (cursor.moveToNext());
                    } else {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    }
                }
        });

        cancelReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete(Contract.ReservationEntry.TABLE_RESERVATION_NAME, Contract.ReservationEntry.USER_ID +" =? ", new String[]{Contract.StudentEntry.actualUserStudentID});
                Contract.StudentEntry.student_has_reservation = false;
                //Toast a message to inform the user about the record being successfully inserted
                Toast.makeText(Library.this, "Reservation canceled", Toast.LENGTH_SHORT).show();
                //finish the activity
                finish();
                //restart the actual activity (LibraryActivity) which will offer the reserve option
                startActivity(getIntent());
                cancelReservation.setVisibility(View.INVISIBLE);
            }
        });
    }

}
