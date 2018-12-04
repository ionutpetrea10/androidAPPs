package com.example.nordwest.my_uni_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView wifi, dashboard, floorPlan, library, timetable, profile;
    private TextView userNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        wifi = findViewById(R.id.id_wifi);
        dashboard = findViewById(R.id.id_webDashboard);
        floorPlan = findViewById(R.id.id_floorPlan);
        library = findViewById(R.id.id_library);
        timetable = findViewById(R.id.id_timeTable);
        profile = findViewById(R.id.id_profile);


       /* userNameText = findViewById(R.id.dashUserName);
        userNameText.setText(Contract.StudentEntry.actualUserFirstName + " " + Contract.StudentEntry.actualUserSecondName);*/

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });


        //Grating  message when successfully login
        Toast.makeText(getBaseContext(),"Welcome to the Dashboard",Toast.LENGTH_LONG).show();


        //adds click listeners to the cards
        wifi.setOnClickListener( this);
        dashboard.setOnClickListener(this);
        floorPlan.setOnClickListener(this);
        timetable.setOnClickListener(this);
        library.setOnClickListener(this);
        profile.setOnClickListener(this);



    }

    @Override

    public void onClick(View v) {
        Intent intent;
        //tests which card was clicked in order to start correct intent (activity)
        switch (v.getId()){
            case R.id.id_wifi: intent = new Intent(this, OthersActivity.class);
            startActivity(intent);
            break;
            case R.id.id_webDashboard: intent = new Intent(this, WebDashboardActivity.class);
            startActivity(intent);
            break;
            case R.id.id_floorPlan: intent = new Intent(this, FloorplanActivity.class);
            startActivity(intent);
            break;
            case R.id.id_timeTable: intent = new Intent(this, TimetableActivity.class);
            startActivity(intent);
            break;
            case R.id.id_library: intent = new Intent(this, LibraryActivity.class);
            startActivity(intent);
            break;
            case R.id.id_profile: intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            break;
            default: break;
            }
    }
}
