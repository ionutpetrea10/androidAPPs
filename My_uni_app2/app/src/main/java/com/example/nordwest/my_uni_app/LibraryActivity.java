package com.example.nordwest.my_uni_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nordwest.my_uni_app.database.Books;
import com.example.nordwest.my_uni_app.database.UnivDao;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    Button addButton, getButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        //Linking the activity with DB and getting access to DAO by initializing it as follow

        final UnivDao univDao = ((AppDelegation) getApplicationContext()).getBooksDatabese().getUnivDao();

        addButton = findViewById(R.id.btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
          //by default ROOM works with found method and insertion and retrieve it is going live
            @Override
            public void onClick(View v) {
                univDao.insertBooks(createBooks());
                String currentDBPath = getDatabasePath("books_db").getAbsolutePath();
                Toast.makeText(LibraryActivity.this, currentDBPath, Toast.LENGTH_SHORT).show();
            }
        });


        getButton = findViewById(R.id.btn_get);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showToast(univDao.getBooks());
            }
        });

    }



    private List<Books> createBooks() {

        List<Books> books = new ArrayList<>(10);
        for (int i = 0; i < 10; i++){
        books.add(new Books( i, "Java Programming language vol: "+i, "1st", "Computing & Internet"));
        }
        return books;
    }

    private void showToast(List<Books> books) {
        StringBuilder builder = new StringBuilder();
        for(int i=0, size = books.size(); i < size; i++){
            builder.append(books.get(i).toString()).append("\n");
        }
        Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();

    }
}
