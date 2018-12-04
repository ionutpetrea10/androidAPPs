package com.example.nordwest.my_uni_app.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Category {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "_category_type_")
    private String category_type;

    public Category(String category_type) {
        this.category_type = category_type;
    }

    public Category() {
    }

    public String getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }
}
