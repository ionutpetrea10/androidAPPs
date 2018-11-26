package com.nordwest.university_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Singele_flor_planActivity extends AppCompatActivity {

    TextView closeReturn;
    ImageView floorPlanImage;
    public static String FLOOR_ID = "FLOOR_ID";
    String floorID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singele_flor_plan);
        Bundle bundle = getIntent().getExtras();
        floorID = bundle.getString(FLOOR_ID).trim();

        floorPlanImage = findViewById(R.id.singelFloorPlanImage);
        if (floorID.substring(0,1).equals("RL")){
            floorPlanImage.setImageResource(R.drawable.level3);
        }else if(floorID.substring(0,1).equals("RO")){
            floorPlanImage.setImageResource(R.drawable.lavel1);
        }else if(floorID.substring(0,1).equals("RG")){
            floorPlanImage.setImageResource(R.drawable.lavel2);
        }else {
            floorPlanImage.setImageResource(R.drawable.lavel1);

        }
        //makes the statue bar background transparent
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        closeReturn = findViewById(R.id.closeReturn);


        closeReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singele_flor_planActivity.super.onBackPressed();

            }
        });

    }
}
