package com.example.nordwest.my_uni_app.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
//this class returns our DAO


@Database(entities = {Books.class, Category.class,
        BookCategory.class, User.class}, version = 2)

public abstract class BooksDatabase extends RoomDatabase {

   abstract UnivDao getUnivDao();

}
