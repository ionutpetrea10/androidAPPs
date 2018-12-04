package com.example.nordwest.my_uni_app.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id_")
    private int id;

    @ColumnInfo(name = "_fn_")
    private String firstName;

    @ColumnInfo(name = "_sn_")
    private String secondName;

    @ColumnInfo(name = "_email_")
    private String email;

    @ColumnInfo(name = "_group_")
    private String group;

    @ColumnInfo(name = "_password_")
    private String password;

    public User() {
    }

    public User( String firstName, String secondName, String email, String group, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.group = group;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
