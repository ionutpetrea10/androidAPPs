package com.example.nordwest.my_uni_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

//this java activity for a basic single fragment
public abstract class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_login_fragment);

    /*logic which check for bundle content. The bundle might have some data as when an activity is re-initialized,
    then the savedInstance contains the most recent data , it contains data of the activity's previous initialization part.
     */
        if (savedInstanceState == null){
        //initializing the manager which helps to handle the fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            //the fragment will be moved/added by transition into a newly yet empty layout
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, obtainFragment()).commit();

        }
    }
    protected abstract Fragment obtainFragment();
}
