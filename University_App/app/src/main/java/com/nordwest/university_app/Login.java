package com.nordwest.university_app;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    /*creating some objects used later */
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Button loginBtn, btn_issue, buttonRegister;
    EditText userEmail, userPassword;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*when the activity is launched setContentView method will set the activity_login as interface on the whole display*/
        setContentView(R.layout.activity_login);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();


        /*in order to link the layout components with the activity and make the app dynamic
         * each component on the layout must connect to a similar element on the activity
         * as we have already declared the fields we must just connect them
         * for this propose we use findViewByID() method
         * there fore by linking the components from activity with the ponce on the layout we can refer to them through the
         * bellow links*/

        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);
        loginBtn = findViewById(R.id.loginBtn);
        buttonRegister = findViewById(R.id.buttonRegister);
        btn_issue = findViewById(R.id.btn_issue);

        //when the buttons are pressed the following methods are going to be called

        loginBtn.setOnClickListener(mOnLoginClickListener);
        buttonRegister.setOnClickListener(mOnRegisterClickListener);
        btn_issue.setOnClickListener(mOnIssueClickListener);


    }

    /*setting listeners to the buttons in order to have something done when the buttons are clicked*/
    private View.OnClickListener mOnLoginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email = userEmail.getText().toString().trim().toLowerCase();
            String pass = userPassword.getText().toString().trim();

            if (isEmailValid() && isPasswordValid()){
                userEmail.setText("");
                userPassword.setText("");
                cursor = db.rawQuery("SELECT * FROM " +Contract.StudentEntry.TABLE_USER_NAME + " WHERE " +Contract.StudentEntry.STUDENT_EMAIL +" =? AND " + Contract.StudentEntry.STUDENT_PASWD + " =? ", new String[]{email, pass});

                if ((cursor != null) && (cursor.moveToFirst()) ){
                        do{
                            Contract.StudentEntry.actualUserFirstName = cursor.getString(cursor.getColumnIndex(Contract.StudentEntry.STUDENT_FNAME));
                            Contract.StudentEntry.actualUserSecondName= cursor.getString(cursor.getColumnIndex(Contract.StudentEntry.STUDENT_SNAME));
                            Contract.StudentEntry.actualUserStudentID= cursor.getString(cursor.getColumnIndex(Contract.StudentEntry.STUDENT_ID));
                            Contract.StudentEntry.actualUserEmail= cursor.getString(cursor.getColumnIndex(Contract.StudentEntry.STUDENT_EMAIL));
                            Contract.StudentEntry.actualUserGroupName= cursor.getString(cursor.getColumnIndex(Contract.StudentEntry.STUDENT_GROUP));

                        }while (cursor.moveToNext());


                        Intent intent = new Intent(Login.this, Dashboard.class);
                        startActivity(intent);

                }else {
                    Toast.makeText(Login.this, R.string.login_inputError, Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(Login.this, R.string.login_inputError, Toast.LENGTH_SHORT).show();
            }
        }
    };



    private View.OnClickListener mOnRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //
            Intent launchRegistration = new Intent(Login.this, RegistrationActivity.class);
            startActivity(launchRegistration);
        }
    };

    private View.OnClickListener mOnIssueClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //
            Intent i = new Intent(Intent.ACTION_DIAL);
            String p = "tel:" + getString(R.string.phone_number);
            i.setData(Uri.parse(p));
            startActivity(i);
        }
    };


    //check if the email is not empty and it match with and the text inserted is an email and return true or false

    private boolean isEmailValid(){
        return !TextUtils.isEmpty(userEmail.getText()) && Patterns.EMAIL_ADDRESS.matcher(userEmail.getText()).matches();
    }

    //check if the password is not empty and return true or false

    private boolean isPasswordValid(){
        return !TextUtils.isEmpty(userPassword.getText());
    }

    //method that displays a Toast message if the login fails which takes as argument a string from resources and use it as message

    private void showMessage (@StringRes int string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
        userEmail.setText("");
        userPassword.setText("");
    }


}
