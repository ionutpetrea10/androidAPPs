package com.example.nordwest.my_uni_app;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nordwest.my_uni_app.database.UnivDao;
import com.example.nordwest.my_uni_app.database.User;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btn_register, show;
    EditText _txtFname_, _txtSname_, _txtGroup_, _txtPass_, _txtEmail_;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       // openHelper = new DatabaseHelper(this);
        _btn_register = findViewById(R.id.btn_reg_regAct);
        _txtFname_ = findViewById(R.id.reg_Fname);
        _txtSname_ = findViewById(R.id.reg_Sname);
        _txtEmail_ = findViewById(R.id.reg_Email);
        _txtGroup_ = findViewById(R.id.reg_Group);
        _txtPass_ = findViewById(R.id.reg_Pass);



        final UnivDao univDao = ((AppDelegation) getApplicationContext()).getBooksDatabese().getUnivDao();

        _btn_register.setOnClickListener(new View.OnClickListener() {
            //by default ROOM works with found method and insertion and retrieve it is going live
            @Override
            public void onClick(View v) {
                univDao.insertUser(newUser());
                //String currentDBPath = getDatabasePath("books_db").getAbsolutePath();
               // Toast.makeText(LibraryActivity.this, currentDBPath, Toast.LENGTH_SHORT).show();
            }
        });


        show = findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(univDao.getUser());
            }
        });






      /*  _btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = openHelper.getWritableDatabase();
                String fName = _txtFname_.getText().toString();
                String sName = _txtSname_.getText().toString();
                String email = _txtEmail_.getText().toString();
                String group = _txtGroup_.getText().toString();
                String password = _txtPass_.getText().toString();

                ContentValues contentValues = new ContentValues();
                contentValues.put(Contract.StudentEntry.STUDENT_FNAME,fName);
                contentValues.put(Contract.StudentEntry.STUDENT_SNAME,sName);
                contentValues.put(Contract.StudentEntry.STUDENT_EMAIL,email);
                contentValues.put(Contract.StudentEntry.STUDENT_GROUP,group);
                contentValues.put(Contract.StudentEntry.STUDENT_PASWD,password);
                db.insert(Contract.StudentEntry.TABLE_NAME, null, contentValues);
                db.close();
                Intent intent = new Intent(register.this,Login.class);
                startActivity(intent);

                Log.d("Database Operations","New record inserted in table "+ Contract.StudentEntry.TABLE_NAME);
                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
            }


        });*/
    }

    private List<User> newUser () {

        List<User> user = new ArrayList<>(1);

            user.add(new User(_txtFname_.getText().toString(), _txtSname_.getText().toString(), _txtEmail_.getText().toString(), _txtGroup_.getText().toString(), _txtPass_.getText().toString()));
        return user;
    }

    private void showToast(List<User> user) {
        StringBuilder builder = new StringBuilder();
        for(int i=0, size = user.size(); i < size; i++){
            builder.append(user.get(i).toString()).append("\n");
        }
        Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();

    }


}
