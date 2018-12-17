package com.mycompany.greeter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class FirstTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    private String greetTextField = "com.mycompany.greeter:id/greetEditText";
    private String greetButton = "com.mycompany.greeter:id/greetButton";
    private String greetText = "com.mycompany.greeter:id/messageTextView";
    private UiDevice mDevice;
    UtilityClass utilityClass = new UtilityClass();



    @Before
    public void base() throws UiObjectNotFoundException {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();
        openTheApp();

    }

    @Test
    public void verifyGreetingsMessage() {
        enterNameForGreetings();
        clickOnGreetButton();
        String greetingMessage = getGreetings();
        Assert.assertEquals("Hello, Jai!" , greetingMessage);

    }

    public void enterNameForGreetings() {
        utilityClass.wait(greetTextField,1000);
        UiObject2 textField = mDevice.findObject(By.res(greetTextField));
        textField.setText("Jai");
    }

    public void clickOnGreetButton() {
        mDevice.wait(Until.findObject(By.res(greetButton)),1000);
        UiObject2 button = mDevice.findObject(By.res(greetButton));
        button.click();
    }

    public String getGreetings() {
        mDevice.wait(Until.findObject(By.res(greetText)),1000);
        UiObject2 greetMessage = mDevice.findObject(By.res(greetText));
        return greetMessage.getText();

    }
    public void openTheApp(){
        mDevice.wait(Until.findObject(By.text(greetTextField)),1000);
        UiObject2 clickOnAppIcon = mDevice.findObject(By.text("Greeter"));
        clickOnAppIcon.click();

    }




}

