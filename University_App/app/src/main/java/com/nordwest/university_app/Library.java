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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Library extends AppCompatActivity {


    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Button btnSearch;
    Switch switchCategory, switchISBN;
    EditText editTextdb;
    TextView textViewdb;
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
        editTextdb = findViewById(R.id.searchedBook);
        textViewdb = findViewById(R.id.textViewdb);
        switchCategory = findViewById(R.id.switchCategory);
        switchISBN = findViewById(R.id.switchISBN);


        switchCategory.setOnClickListener(mOnSw1ClickListener);
        switchISBN.setOnClickListener(mOnSw2ClickListener);
        btnSearch.setOnClickListener(mOnBtnSearchClickListener);

        rv = findViewById(R.id.rvLibrary);

        MyBookAdaptor myBookAdaptor = new MyBookAdaptor(this, retriedBooks);
        rv.setAdapter(myBookAdaptor);
        rv.setLayoutManager(new LinearLayoutManager(this));






    }

    private View.OnClickListener mOnBtnSearchClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String choice, ISBN, title, author, edition;
            choice = editTextdb.getText().toString().trim();
            if (switchCategory.isChecked()) {
                cursor = db.rawQuery("SELECT _ISBN_, _title_, _edition_, (_fn_ || \" \" || _sn_) as _author_\n" +
                        "FROM  _book_ , _author_, _has_written_\n" +
                        "where _book_._ISBN_ = _has_written_._book_id_ and _author_._author_id_ = _has_written_._author_id_ and _book_._catergory_type_ =? ", new String[]{choice});
                if (cursor != null) {
                    retriedBooks.clear();
                    rv.removeAllViewsInLayout();
                    if (cursor.moveToFirst()) {
                        do {

                            ISBN = cursor.getString(cursor.getColumnIndex("_ISBN_"));
                            title = cursor.getString(cursor.getColumnIndex("_title_"));
                            author = cursor.getString(cursor.getColumnIndex("_author_"));
                            edition = cursor.getString(cursor.getColumnIndex("_edition_"));

                            retriedBooks.add(new MyViewBookHolder(ISBN,title,author,edition));


                            al.add(cursor.getString(cursor.getColumnIndex("_title_")) + " " + cursor.getString(cursor.getColumnIndex("_ISBN_")));
                            cursor.toString();
                        } while (cursor.moveToNext());

                        textViewdb.setText(al.toString() + ISBN);
                        al.clear();

                    } else {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    }
                }
            }else {
                cursor = db.rawQuery("SELECT _ISBN_, _title_, _edition_, (_fn_ || \" \" || _sn_) as Author\n" +
                        "FROM  _book_ , _author_, _has_written_\n" +
                        "where _book_._ISBN_ = _has_written_._book_id_ and _author_._author_id_ = _has_written_._author_id_ and _book_._ISBN_ =? ", new String[]{choice});
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            ISBN = cursor.getString(cursor.getColumnIndex("_title_")) + cursor.getString(cursor.getColumnIndex("_ISBN_"));
                            al.add(cursor.getString(cursor.getColumnIndex("_title_")) + " " + cursor.getString(cursor.getColumnIndex("_ISBN_")));
                            cursor.toString();
                        } while (cursor.moveToNext());

                        textViewdb.setText(al.toString() + ISBN);
                        al.clear();
                    } else {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    };

    private View.OnClickListener mOnSw1ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (switchCategory.isChecked()){
                switchISBN.setChecked(false);
                textViewdb.setText(null);
            }
         }
    };
    private View.OnClickListener mOnSw2ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

           if (switchISBN.isChecked()){
               switchCategory.setChecked(false);
               textViewdb.setText(null);
           }
         }
    };

}
