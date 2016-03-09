package com.parku.au.janetpark.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.parku.au.janetpark.R;
import com.parku.au.janetpark.network.model.User;

public class HomeActivity extends AppCompatActivity {

    private TextView tvUsername, tvEmail, tvContactNumber;
    private User user;
    private String username, email, contactNumber;
    private final String USERNAME = "Username : ", EMAIL = "Email : ", CONTACT_NUMBER = "Contact Number : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvUsername = (TextView) findViewById(R.id.usernameTextView);
        tvEmail = (TextView) findViewById(R.id.emailTextView);
        tvContactNumber = (TextView) findViewById(R.id.contactNumberTextView);

        showUserData();

    }

    public void showUserData(){

        //receiving the class object from the LoginActivity
        user = (User) getIntent().getSerializableExtra("userDetails");

        username = user.getUsername();
        email = user.getEmail();
        contactNumber = user.getContactNumber();

        tvUsername.setText(USERNAME + username);
        tvEmail.setText(EMAIL + email);
        tvContactNumber.setText(CONTACT_NUMBER+ contactNumber);

    }

}
