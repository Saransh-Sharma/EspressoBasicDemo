package com.mycompany.greeter;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.Until;
import android.util.Log;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.NoSuchElementException;

@RunWith(AndroidJUnit4.class)
public class FirstUiAutomatorTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);


    private String logTag = "GreeterTest";
    private String greetTextField = "com.mycompany.greeter:id/greetEditText";
    private String greetButton = "GREET";
    private String greetTextFinalField = "com.mycompany.greeter:id/messageTextView";
    private UiDevice mDevice;

    @Before
    public void setUp() throws UiObjectNotFoundException {


        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();

        openApp();

    }

    @Test
    public void displayName() {

        // User name Input
        String enteredUserName = "Sunny";

        // Enter username in greet text field
        enterUserName(enteredUserName);

        // Click Greet button
        clickGreetButton();

        // get Final Greet Message
        String stringDisplayedInApp = getFinalGreetMessage();

        Assert.assertEquals(stringDisplayedInApp, "Hello, " + enteredUserName + "!");

    }


    public boolean enterUserName(String userName) {
        try {

            mDevice.wait(Until.findObject(By.res(greetTextField)), 1000);
            UiObject2 textField = mDevice.findObject(By.res(greetTextField));
            textField.setText(userName);
            return true;
        } catch (NoSuchElementException e) {
            Log.v(logTag, "Enter username field not found");
            return false;
        }
    }

    public boolean clickGreetButton() {
        try {
            mDevice.wait(Until.findObject(By.text(greetButton)), 1000);
            UiObject2 clickButton = mDevice.findObject(By.text(greetButton));
            clickButton.click();
            return true;
        } catch (NoSuchElementException e) {
            Log.v(logTag, "Greet Button Not Found");
            return false;
        }
    }

    public String getFinalGreetMessage() {
        try {

            mDevice.wait(Until.findObject(By.res(greetTextFinalField)), 1000);
            UiObject2 greetMessage = mDevice.findObject(By.res(greetTextFinalField));
            return greetMessage.getText();
        } catch (NoSuchElementException e) {
            Log.v(logTag, "Final Text Not Found");
            return null;
        }

    }


    private boolean openApp() {

        try {
            mDevice.wait(Until.findObject(By.text("Greeter")), 1000);
            UiObject2 clickButton = mDevice.findObject(By.text("Greeter"));
            clickButton.click();
            return true;
        } catch (Exception e) {
            Log.v(logTag, "App Not Found");
            return false;
        }


    }

}
