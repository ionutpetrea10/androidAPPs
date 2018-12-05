package com.example.nordwest.my_uni_app.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Books.class, Category.class, BookCategory.class, User.class}, version = 1)
public abstract class BooksDB extends RoomDatabase {

  public abstract UnivDao getUnivDao();
}
