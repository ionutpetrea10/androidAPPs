package com.example.nordwest.my_uni_app.database;

//annotation Entity ??????
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Books {

    @PrimaryKey
    @ColumnInfo(name = "_ISBN_")
    private int ISBN;

    @ColumnInfo(name = "_title_")
    private String title;

    @ColumnInfo(name = "_edition_")
    private String edition;

    @ColumnInfo(name = "category_type")
    private String category_type;

    public Books() {
    }

    public Books(int ISBN, String title, String edition, String category_type) {
        this.ISBN = ISBN;
        this.title = title;
        this.edition = edition;
        this.category_type = category_type;
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

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }

    @Override
    public String toString() {
        return "Books{" +
                "ISBN=" + ISBN +
                ", title='" + title +
                ", edition='" + edition +
                ", category_type='" + category_type +'}';
    }
}
