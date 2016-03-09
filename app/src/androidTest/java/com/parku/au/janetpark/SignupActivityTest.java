package com.parku.au.janetpark;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import com.parku.au.janetpark.view.LoginActivity;
import com.parku.au.janetpark.view.SignupActivity;

/**
 * Created by Janet.Regala on 3/8/2016.
 */
public class SignupActivityTest extends ActivityInstrumentationTestCase2<SignupActivity>{

    private Solo solo;

    public SignupActivityTest(){
        super(SignupActivity.class);
    }

    public void setUp() throws Exception{
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testSignup1CorrectValues(){
        solo.assertCurrentActivity("wrong activity", SignupActivity.class);

        //empty fields
        solo.enterText(0,""); //Enter firstname
        solo.enterText(1,""); //Enter lastname
        solo.enterText(2,""); //Enter email (required)
        solo.enterText(3, ""); //Enter contact number
        solo.enterText(4, ""); //Enter username (required)
        solo.enterText(5, ""); //Enter password (required)
        solo.enterText(6, ""); //Confirm password (required)
        solo.clickOnButton("Submit");
        assertTrue("did not get an error message for empty field", solo.searchText("Required Field"));

//        //all validation
//        solo.enterText(2, "janet");
//        solo.enterText(4, "");
//        solo.enterText(5, "net123");
//        solo.enterText(6, "net");
//        solo.clickOnButton("Submit");
//        assertTrue("did not get an error message for invalid email", solo.searchText("Invalid Email"));
//        assertTrue("did not get an error message for empty username", solo.searchText("Required Field"));
//        assertTrue("did not get an error message for password validation", solo.searchText("Contains upper and lower case and combination of letters and numbers (6 - 16 characters"));
//        assertTrue("did not get an error message password mismatch", solo.searchText("Password don't match"));
//        solo.clearEditText(2);
//        solo.clearEditText(4);
//        solo.clearEditText(5);
//        solo.clearEditText(6);

        //email validation
        solo.enterText(2, "janet");
        solo.enterText(4, "net");
        solo.enterText(5, "Net123");
        solo.enterText(6, "Net123");
        solo.clickOnButton("Submit");
        assertTrue("did not get an error message for invalid email", solo.searchText("Invalid Email"));
        solo.clearEditText(2);
        solo.clearEditText(4);
        solo.clearEditText(5);
        solo.clearEditText(6);

        //password validation and confirm password
        solo.enterText(0, "");
        solo.enterText(1, "");
        solo.enterText(2, "janet@gmail.com");
        solo.enterText(3, "");
        solo.enterText(4, "net");
        solo.enterText(5, "net123");
        solo.enterText(6, "Net123");
        solo.clickOnButton("Submit");
        assertTrue("did not get an error message for password validation", solo.searchText("Contains upper and lower case and combination of letters and numbers (6 - 16 characters"));
        solo.clearEditText(2);
        solo.clearEditText(4);
        solo.clearEditText(5);
        solo.clearEditText(6);

        //confirm password
        solo.enterText(2,"janet@gmail.com");
        solo.enterText(3,"");
        solo.enterText(4, "net");
        solo.enterText(5, "Net123");
        solo.enterText(6, "et123");
        solo.clickOnButton("Submit");
        assertTrue("did not get an error message password mismatch", solo.searchText("Password don't match"));
        solo.clearEditText(2);
        solo.clearEditText(4);
        solo.clearEditText(5);
        solo.clearEditText(6);

        //correct values
        solo.enterText(0, "Test1"); //Enter firstname
        solo.enterText(1, "Sample1"); //Enter lastname
        solo.enterText(2, "test1@gmail.com");
        solo.enterText(3, "0905222"); //Enter email (required)
        solo.enterText(4, "test1"); //Enter contact number
        solo.enterText(5, "Test123"); //Enter username
        solo.enterText(6, "Test123"); //Enter password
        solo.clickOnButton("Submit"); //Confirm password
        solo.assertCurrentActivity("wrong activityy", LoginActivity.class);
        solo.clickOnText("Signup");
        solo.assertCurrentActivity("wrong activity", SignupActivity.class);



    }

//

}
