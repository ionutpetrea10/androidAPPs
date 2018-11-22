package com.nordwest.university_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Singele_flor_planActivity extends AppCompatActivity {
    TextView closeReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singele_flor_plan);
        closeReturn = findViewById(R.id.closeReturn);


        closeReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singele_flor_planActivity.super.onBackPressed();

            }
        });

    }
}
