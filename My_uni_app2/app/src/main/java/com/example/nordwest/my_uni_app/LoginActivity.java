package com.example.nordwest.my_uni_app;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {



    private EditText mLogin, mPassword;
    private Button mLoginBtn, mRegister;



    /*setting listeners to the buttons in order to have something done when the buttons are clicked*/
    private View.OnClickListener mOnLoginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isEmailValid() && isPasswordValid()){


                //let the user to enter the app if both methods return true
                Intent launchDashboard = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(launchDashboard);

            }else {
                showMessage(R.string.login_inputError);
            }

        }
    };

    private View.OnClickListener mOnRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //

        }
    };


    //check if the email is not empty and it match with and the text inserted is an email and return true or false

    private boolean isEmailValid(){
        return !TextUtils.isEmpty(mLogin.getText()) && Patterns.EMAIL_ADDRESS.matcher(mLogin.getText()).matches();
    }

    //check if the password is not empty and return true or false

    private boolean isPasswordValid(){
        return !TextUtils.isEmpty(mPassword.getText());
    }

    //method that displays a Toast message if the login fails which takes as argument a string from resources and use it as message

    private void showMessage (@StringRes int string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*the view will set mentioned layout on the whole display when the app will launch */
        setContentView(R.layout.activity_timetable);

        /*in order to link the layout components with the activity and make the app dynamic
        * each component on the layout must connect to a similar element on the activity
        * as we have already declared the fields we must just connect them
        * for this propose we use findViewByID() method
        * there fore by linking the components from activity with the ponce on the layout we can refer to them through the
        * bellow links*/


        mLogin = findViewById(R.id.edLogin);
        mPassword = findViewById(R.id.edPassword);
        mLoginBtn = findViewById(R.id.buttonLogin);
        mRegister = findViewById(R.id.buttonRegister);


        //when the buttons are pressed the following methods are going to be called
        mLoginBtn.setOnClickListener(mOnLoginClickListener);
        mRegister.setOnClickListener(mOnRegisterClickListener);



    }
}
