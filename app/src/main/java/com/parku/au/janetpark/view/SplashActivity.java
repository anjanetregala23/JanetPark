package com.parku.au.janetpark.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parku.au.janetpark.constant.Constant;
import com.parku.au.janetpark.managers.SharedPrefManager;

public class SplashActivity extends AppCompatActivity {

    private Intent intent;
    private SharedPrefManager sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = new SharedPrefManager(this);

        boolean isFirstTimeRun = sharedPref.getBoolean(Constant.FIRST_TIME_RUN, true);

        if(isFirstTimeRun) {
            intent = new Intent(this, InstructionsActivity.class);
        }
        else {
            intent = new Intent(this, LoginActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
