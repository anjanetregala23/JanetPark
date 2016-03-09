package com.parku.au.janetpark.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.parku.au.janetpark.R;
import com.parku.au.janetpark.constant.Constant;
import com.parku.au.janetpark.managers.SharedPrefManager;
import com.parku.au.janetpark.view.adapters.StepsViewPageAdapters;


public class InstructionsActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private int STEP_PAGE_3 = 2;

    private ViewPager vpSteps;
    private StepsViewPageAdapters vpAdapterSteps;
    private ImageView ivLogin;

    private SharedPrefManager sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_instructions);

        vpAdapterSteps = new StepsViewPageAdapters(this);

        vpSteps = (ViewPager) findViewById(R.id.steps_viewpager);
        vpSteps.setAdapter(vpAdapterSteps);
        vpSteps.addOnPageChangeListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.proceedLoginImageView) {
            sharedPref = new SharedPrefManager(this);
            sharedPref.putBoolean(Constant.FIRST_TIME_RUN, false);

            Intent intent = new Intent(InstructionsActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

        if (position == STEP_PAGE_3) {
            ivLogin = (ImageView) findViewById(R.id.proceedLoginImageView);
            ivLogin.setOnClickListener(this);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
