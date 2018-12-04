package com.nordwest.university_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StudentProfile extends AppCompatActivity {

    TextView textViewsimplu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        textViewsimplu.findViewById(R.id.textViewsimplu);
        textViewsimplu.setText(Contract.StudentEntry.actualUserEmail);
    }
}
