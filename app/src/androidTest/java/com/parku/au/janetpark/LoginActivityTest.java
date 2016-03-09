package com.parku.au.janetpark;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import parku.com.au.parku.view.HomeActivity;
import parku.com.au.parku.view.LoginActivity;


/**
 * Created by Janet.Regala on 3/7/2016.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Solo solo;

    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());

    }

    public void testLogin() {

        //Correct values
        solo.assertCurrentActivity("wrong activity", LoginActivity.class); // check that we have the right activity
        solo.enterText(0, "test1"); //Enter username
        solo.enterText(1, "Test123"); //Enter password
        solo.clickOnButton("Login"); // click login button
        solo.assertCurrentActivity("wrong activity", HomeActivity.class); // Validate that the Activity is the correct one
        solo.goBack();
        solo.clearEditText(0);
        solo.clearEditText(1);

//        //incorect values
//        solo.enterText(0, "asd");
//        solo.enterText(1, "asd");
//        solo.clickOnButton("Login");
//        assertTrue(solo.waitForText("Username or Password is not correct"));
//        solo.clearEditText(0);
//        solo.clearEditText(1);
//
//        //Empty fields
//        solo.enterText(0, "");
//        solo.enterText(1, "");
//        solo.clickOnButton("Login");
//
//        assertTrue("did not get an error message", solo.searchText("Enter Username")); // Validate if the username field is empty
//        assertTrue("did not get an error message", solo.searchText("Enter Password")); // Validate if the password field is empty
//
//        //click signup
//        solo.clickOnText("Signup");
//        solo.assertCurrentActivity("wrong activity", SignupActivity.class);// Validate that the Activity is the correct one
//
//        //click the instruction imageview
//        solo.goBack();
//
//
//       // solo.clickOnView(buttonview);
//        solo.clickOnImage(3);
//        solo.assertCurrentActivity("wrong activity", InstructionsActivity.class);// Validate that the Activity is the correct one
    }

    /**
     * separate methods
     */


//    public void testLogin1CorrectValues(){
//
//        solo.assertCurrentActivity("wrong activity", LoginActivity.class);
//        solo.enterText(0, "net");
//        solo.enterText(1, "Net123");
//        solo.clickOnButton("Login");
//        solo.assertCurrentActivity("wrong activity", MainActivity.class);
//    }
//    public void testLogin2EmptyFields(){
//        solo.assertCurrentActivity("wrong activity", LoginActivity.class);
//        solo.enterText(0, "");
//        solo.enterText(1, "");
//        solo.clickOnButton("Login");
//        assertTrue("did not get an error message", solo.searchText("Enter Username"));
//        assertTrue("did not get an error message", solo.searchText("Enter Password"));
//    }
//    public void testLogin3ClickSignUp(){
//        solo.assertCurrentActivity("wrong activity", LoginActivity.class);
//        solo.clickOnText("Signup");
//        solo.assertCurrentActivity("wrong activity", SignupActivity.class);
//    }
//    public void testLogin4ClickInstructionIcon(){
//        solo.assertCurrentActivity("wrong activity", LoginActivity.class);
//        solo.clickOnImage(3);
//        solo.assertCurrentActivity("wrong activity", InstructionsActivity.class);
//    }


    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}
