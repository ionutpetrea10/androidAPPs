package com.example.nordwest.my_uni_app;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.nordwest.my_uni_app.database.BooksDB;

//class for extracting records from DB
public class AppDelegation extends Application {


    public void onCreation (){
        super.onCreate();
    }

    //create and initialization of DB

    private BooksDB BooksDatabese;

    public void onCreate(){
        super.onCreate();

        BooksDatabese = Room.databaseBuilder(getApplicationContext(), BooksDB.class, "books_db").allowMainThreadQueries().build();
    }

    public BooksDB getBooksDatabese() {
        return BooksDatabese;
    }
}
