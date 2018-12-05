package com.example.nordwest.my_uni_app.database;

//data access object DAO. There are all methods we are communicating with the DB

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UnivDao {


    /*
    methods that add records into DB. OnConflictStrategy will compare records based on Primary keys (ISBN) and will replace records if duplicate
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(List<User> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBooks(List<Books> books);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategories(List<Category> categories);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setLinks(List<BookCategory> bookCategories);

    /*queries to retrieve data form DB*/
    @Query("SELECT * FROM books")
    List<Books> getBooks();

    @Query("SELECT * FROM user")
    List<User> getUser();

    @Query("SELECT * FROM category")
    List<Category> getCategory();

    //delete method

    @Delete
    void deleteBooks(Books books);

    //method that links books with category
    @Query("SELECT * FROM CATEGORY WHERE _category_type_ = :category_type")
    List<Category> getCategoryOfBook(String category_type);



}
