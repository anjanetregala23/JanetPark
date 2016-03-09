package com.parku.au.janetpark;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import com.parku.au.janetpark.view.InstructionsActivity;
import com.parku.au.janetpark.view.LoginActivity;

/**
 * Created by Janet.Regala on 3/8/2016.
 */
public class InstructionsActivityTest extends ActivityInstrumentationTestCase2<InstructionsActivity> {

    private Solo solo;

    public InstructionsActivityTest(){
        super(InstructionsActivity.class);
    }

    public void setUp(){
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testInstructions(){

        solo.assertCurrentActivity("wrong activity", InstructionsActivity.class);
       // assertTrue(solo.waitForView(InstructionsActivity.class));
        solo.assertCurrentActivity("wrong activity", LoginActivity.class);

    }
}
