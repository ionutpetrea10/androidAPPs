package com.example.nordwest.my_uni_app.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Books.class, parentColumns = "_ISBN_", childColumns = "_ISBN_"),
        @ForeignKey(entity = Category.class, parentColumns = "_category_type_", childColumns = "_category_type_")
})

public class BookCategory {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id_")
    private int ID;

    @ColumnInfo(name = "_ISBN_")
    private int ISBN;

    @ColumnInfo(name = "_title_")
    private String title;

    @ColumnInfo(name = "_category_type_")
    private String category_type;

    public BookCategory(int ID, int ISBN, String title, String category_type) {
        this.ID = ID;
        this.ISBN = ISBN;
        this.title = title;
        this.category_type = category_type;
    }

    public BookCategory() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }
}
