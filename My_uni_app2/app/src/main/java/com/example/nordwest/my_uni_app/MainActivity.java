package com.example.nordwest.my_uni_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    private Button loginButton, registerButton;

    /*setting listeners to the buttons in order to have something done when the buttons are clicked*/
    private View.OnClickListener OnLoginButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //open login (authentication) activity and launch the corresponding layout
            Intent launchDashboard = new Intent(MainActivity.this, AuthActivity.class);
            startActivity(launchDashboard);

        }
    };

    private View.OnClickListener OnRegisterButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent launchDashboard = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(launchDashboard);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.btn_login);
        registerButton = findViewById(R.id.btn_registration);


        loginButton.setOnClickListener(OnLoginButtonClickListener);
        registerButton.setOnClickListener(OnRegisterButtonClickListener);

    }
}
