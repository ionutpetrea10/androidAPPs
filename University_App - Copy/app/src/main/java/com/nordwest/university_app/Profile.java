package com.nordwest.university_app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends Activity {

    TextView profileName ,profileSt_ID ,groupProfile ,EmailProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileName = findViewById(R.id.userProfileName);
        profileSt_ID = findViewById(R.id.studentProfileID);
        groupProfile = findViewById(R.id.groupProfile);
        EmailProfile = findViewById(R.id.studentEmailProfile);

        profileName.setText(Contract.StudentEntry.actualUserFirstName + " " + Contract.StudentEntry.actualUserSecondName);
        profileSt_ID.setText(Contract.StudentEntry.actualUserStudentID);
        groupProfile.setText(Contract.StudentEntry.actualUserGroupName);
        EmailProfile.setText(Contract.StudentEntry.actualUserEmail);

        Toast.makeText(getBaseContext(),"Profile" +Contract.StudentEntry.actualUserEmail,Toast.LENGTH_LONG).show();

    }
}
