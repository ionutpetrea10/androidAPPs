package com.example.nordwest.my_uni_app;

import android.support.v4.app.Fragment;

public class AuthActivity extends FragmentActivity {


    @Override
    protected Fragment obtainFragment() {
        return AuthenticationFragmnet.newInstance();
    }
}
