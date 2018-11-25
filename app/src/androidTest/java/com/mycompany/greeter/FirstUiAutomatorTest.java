package com.mycompany.greeter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Scanner;

@RunWith(AndroidJUnit4.class)
public class FirstUiAutomatorTest {


    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    String greetTextField = "com.mycompany.greeter:id/greetEditText";
    String greetButton = "GREET";
    String greetTextFinalField = "com.mycompany.greeter:id/messageTextView";


    private UiDevice mDevice;

    @Before
    public void setUp() throws UiObjectNotFoundException {


        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();
        mDevice.wait(Until.hasObject(By.pkg(getLauncherPackageName()).depth(0)), 1000);
        mDevice.pressMenu();


        clickLauncherApp();


    }

    @Test
    public void displayName() {

        // Get User name Input
        String enteredUserName = "Sunny";

        // Enter username in greet text field
        enterUserName(enteredUserName);

        // Click Greet button
        clickGreetButton();

        // get Final Greet Message
        String stringDisplayedInApp = getFinalGreetMessage();

        Assert.assertEquals(stringDisplayedInApp, "Hello, " + enteredUserName + "!");

    }

    public String getUsername() {
        System.out.println("How would you like to be addressed");
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }


    public void enterUserName(String userName) {
        UiObject2 textField = mDevice.findObject(By.res(greetTextField));
        System.out.print(textField);

        textField.setText(userName);
    }

    public void clickGreetButton() {
        UiObject2 clickButton = mDevice.findObject(By.text(greetButton));
        clickButton.click();
    }

    public String getFinalGreetMessage() {
        UiObject2 greetMessage = mDevice.findObject(By.res(greetTextFinalField));
        return greetMessage.getText();


    }


    private String getLauncherPackageName() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        PackageManager pm = InstrumentationRegistry.getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return resolveInfo.activityInfo.packageName;

    }

    public void clickLauncherApp() throws UiObjectNotFoundException {
        UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
        appViews.setAsHorizontalList();

        UiObject greeterApp = appViews.getChildByText(
                new UiSelector().className(android.widget.TextView.class.getName()), "Greeter");

        greeterApp.clickAndWaitForNewWindow();
    }


}