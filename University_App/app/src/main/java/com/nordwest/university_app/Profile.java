package com.nordwest.university_app;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    TextView profileName ,profileSt_ID ,groupProfile ,EmailProfile;

    private static boolean isFabOpen;
    private FloatingActionButton fabMain;
    private FloatingActionButton fabOut;
    private FloatingActionButton fabBusinessCenter;
    private View bgFabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileName = findViewById(R.id.userProfileName);
        profileSt_ID = findViewById(R.id.studentProfileID);
        groupProfile = findViewById(R.id.groupProfile);
        EmailProfile = findViewById(R.id.studentEmailProfile);
        fabBusinessCenter = findViewById(R.id.fab_mainActivity);
        fabMain = findViewById(R.id.fab_main);
        fabOut = findViewById(R.id.fab_signOut);
        bgFabMenu = findViewById(R.id.bg_fab_menu);

        fabMain.setOnClickListener(mOnFabMainClickListener);
        fabOut.setOnClickListener(mOnfabOutClickListener);
        fabBusinessCenter.setOnClickListener(mOnfabBusinessCenterClickListener);
        bgFabMenu.setOnClickListener(mOnbgFabMenuClickListener);


        profileName.setText(Contract.StudentEntry.actualUserFirstName + " " + Contract.StudentEntry.actualUserSecondName);
        profileSt_ID.setText(Contract.StudentEntry.actualUserSecondName.substring(0,3).toUpperCase()+ "101" + Contract.StudentEntry.actualUserStudentID);
        groupProfile.setText(Contract.StudentEntry.actualUserGroupName);
        EmailProfile.setText(Contract.StudentEntry.actualUserEmail);

        Toast.makeText(getBaseContext(),"Profile" +Contract.StudentEntry.actualUserEmail,Toast.LENGTH_LONG).show();




    }

    private View.OnClickListener mOnFabMainClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!fabMain.isPressed()){
                fabOut.animate().translationY(-200);

                Toast.makeText(Profile.this, "presed", Toast.LENGTH_SHORT).show();
            }else {
                CloseFab();
                Toast.makeText(Profile.this, "Presasasa", Toast.LENGTH_SHORT).show();
                isFabOpen = true;

            }
        }
    };

    private View.OnClickListener mOnfabOutClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CloseFab();
            Toast.makeText(Profile.this, "SignOut", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mOnfabBusinessCenterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CloseFab();
            Toast.makeText(Profile.this, "Dashboard", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mOnbgFabMenuClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CloseFab();
            Toast.makeText(Profile.this, "aside", Toast.LENGTH_SHORT).show();
        }
    };



    private void CloseFab() {

        fabMain.animate().rotation(0);
        bgFabMenu.setAlpha(0);
        fabBusinessCenter.animate().translationY(0).rotation(90);
        fabOut.animate().translationY(55).rotation(0);

    }

    private void ShowFabMenu() {
        isFabOpen = true;
        fabBusinessCenter.setVisibility(View.VISIBLE);
        fabOut.setVisibility(View.VISIBLE);
       // bgFabMenu.setVisibility(View.VISIBLE);

        fabMain.animate().rotation(135f);
        fabBusinessCenter.animate().translationY(R.dimen.standard100).rotation(0);
        fabOut.animate().translationY(R.dimen.standard100).rotation(0);

    }
}
