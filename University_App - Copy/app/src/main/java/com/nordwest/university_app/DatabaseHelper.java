package com.nordwest.university_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//extend with SQL class in order to create DB

public class DatabaseHelper extends SQLiteOpenHelper {

    //create constant variables for database_name and version
    public static final String DATABASE_NAME = "university.db";
    public static final int DATABASE_VERSION = 1;


    //The SQL query for table creation
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+Contract.StudentEntry.TABLE_NAME +
            " (" +
            "`_student_id_`INTEGER PRIMARY KEY AUTOINCREMENT," +
            "`_fn_`TEXT NOT NULL," +
            "`_sn_`TEXT NOT NULL," +
            "`_email_`TEXT NOT NULL," +
            "`_password_`TEXT NOT NULL," +
            "`_group_`TEXT NOT NULL" +
            ");";



    //The SQL query if table exists before creation
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + Contract.StudentEntry.TABLE_NAME;


    //constructor for this class
    //In order to create a data base we need this constructor which needs the following parameters
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        //A log message in order to debug easier
        Log.d("Database Operations","Database created");
    }


    //create the table
    @Override
    public void onCreate(SQLiteDatabase db) {

        //passing through execSQL the SQLiteDB parameter (db=CREATE_TABLE)
        // object in order to create the table

        db.execSQL(CREATE_TABLE);


        //A log message in order to debug easier
        Log.d("Database Operations","Table "+Contract.StudentEntry.TABLE_NAME+" created");
    }

    //upload existing table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //delete existing table though execSQL() by passing the DB object parameters
        //if table dropped call the onCreate method
        // in order to continue the table creation

        db.execSQL(DROP_TABLE);
        onCreate(db);

        //A log message in order to debug easier
        Log.d("Database Operations","Table "+Contract.StudentEntry.TABLE_NAME+" dropped and oncreate called ");
    }
    //at this point DB and the table is created in order to insert data in the table _user_ new methods must be created within the class DBHelper

//    //method to insert data into the table
//    public static void addStudent(String fName, String sName, String email, String address, String password, SQLiteDatabase database) {
//
//        //create a contentValues obj to be passed to the DB table as a record
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(Contract.StudentEntry.STUDENT_FNAME,fName);
//        contentValues.put(Contract.StudentEntry.STUDENT_SNAME,sName);
//        contentValues.put(Contract.StudentEntry.STUDENT_EMAIL,email);
//        contentValues.put(Contract.StudentEntry.STUDENT_ADDRESS,address);
//        contentValues.put(Contract.StudentEntry.STUDENT_PASWD,password);
//
//        //data collection was passed into contentValues at this stage
//        //next the contentValues wil be passed into database table _user_ as a record (obj)
//        database.insert(Contract.StudentEntry.TABLE_NAME, null, contentValues);
//        //A log message in order to debug easier
//        Log.d("Database Operations","New record inserted in table "+ Contract.StudentEntry.TABLE_NAME);
//    }
    public Cursor getInforation(DatabaseHelper db){
        SQLiteDatabase SQ = db.getReadableDatabase();
        String[] collection = {Contract.StudentEntry.STUDENT_EMAIL, Contract.StudentEntry.STUDENT_PASWD};
        Cursor cursor = SQ.query(Contract.StudentEntry.TABLE_NAME,collection,null,null,null,null,null);
        return cursor;
    }

}
