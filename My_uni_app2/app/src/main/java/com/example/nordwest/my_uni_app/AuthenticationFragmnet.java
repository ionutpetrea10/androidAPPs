package com.example.nordwest.my_uni_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

;

public class AuthenticationFragmnet extends Fragment {

    private EditText mLogin, mPassword;
    private Button mLoginBtn, mRegister, btn_issue;

    public static AuthenticationFragmnet newInstance() {
        
        Bundle args = new Bundle();
        
        AuthenticationFragmnet fragment = new AuthenticationFragmnet();
        fragment.setArguments(args);
        return fragment;
    }


    /*setting listeners to the buttons in order to have something done when the buttons are clicked*/
    private View.OnClickListener mOnLoginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isEmailValid() && isPasswordValid()){

                //let the user to enter the app if both methods return true

                Intent launchDashboard = new Intent(getActivity(), DashboardActivity.class);

                //launchDashboard.putExtra(ProfileActivity.EMAIL_KEY, mLogin.getText().toString());
                //launchDashboard.putExtra(DashboardActivity.PASSWORD_KEY, mPassword.getText().toString());
                startActivity(launchDashboard);

            }else {
                showMessage(R.string.login_inputError);
            }

        }
    };

    private View.OnClickListener mOnRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //
            Intent launchRegistration = new Intent(getActivity(), RegistrationActivity.class);
            startActivity(launchRegistration);
        }
    };

    private View.OnClickListener mOnIssueClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //
            Intent i = new Intent(Intent.ACTION_DIAL);
            String p = "tel:" + getString(R.string.phone_number);
            i.setData(Uri.parse(p));
            startActivity(i);
        }
    };


    //check if the email is not empty and it match with and the text inserted is an email and return true or false

    private boolean isEmailValid(){
        return !TextUtils.isEmpty(mLogin.getText()) && Patterns.EMAIL_ADDRESS.matcher(mLogin.getText()).matches();
    }

    //check if the password is not empty and return true or false

    private boolean isPasswordValid(){
        return !TextUtils.isEmpty(mPassword.getText());
    }

    //method that displays a Toast message if the login fails which takes as argument a string from resources and use it as message

    private void showMessage (@StringRes int string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        /*the view will set mentioned layout on the whole display when the app will launch */

        View v = inflater.inflate(R.layout.fr_login, container, false);

        /*in order to link the layout components with the activity and make the app dynamic
         * each component on the layout must connect to a similar element on the activity
         * as we have already declared the fields we must just connect them
         * for this propose we use findViewByID() method
         * there fore by linking the components from activity with the ponce on the layout we can refer to them through the
         * bellow links*/


        mLogin = v.findViewById(R.id.edLogin);
        mPassword = v.findViewById(R.id.edPassword);
        mLoginBtn = v.findViewById(R.id.buttonLogin);
        mRegister = v.findViewById(R.id.buttonRegister);
        btn_issue = v.findViewById(R.id.btn_issue);


        //when the buttons are pressed the following methods are going to be called
        mLoginBtn.setOnClickListener(mOnLoginClickListener);
        mRegister.setOnClickListener(mOnRegisterClickListener);
        btn_issue.setOnClickListener(mOnIssueClickListener);




        return v;


    }
}
