package com.example.nordwest.my_uni_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {


    TextView profileName ,profileSt_ID ,groupProfile ,emailProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.userProfileName);
        profileSt_ID = findViewById(R.id.studentProfileID);
        groupProfile = findViewById(R.id.groupProfile);
        emailProfile = findViewById(R.id.studentEmailProfile);





        Toast.makeText(getBaseContext(),"Profile " ,Toast.LENGTH_LONG).show();

    }
}
