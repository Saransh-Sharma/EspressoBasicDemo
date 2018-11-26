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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

public class FirstTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    private String textFieldId = "com.mycompany.greeter:id/greetEditText";
    private String greetButton = "com.mycompany.greeter:id/greetButton";
    private String greetText = "com.mycompany.greeter:id/messageTextView";
    private UiDevice mDevice;


    @Before
    public void base() throws UiObjectNotFoundException {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();
        try {
            clickLauncherApp();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void verifyGreetingsMessage() {
        enterNameForGreetings();
        clickOnGreetButton();
        String greetingMessage = getGreetings();

    }

    public void enterNameForGreetings() {
        UiObject2 textField = mDevice.findObject(By.res("com.mycompany.greeter","greetEditText"));
        textField.setText("Jai");
    }

    public void clickOnGreetButton() {
        UiObject2 button = mDevice.findObject(By.text(greetButton));
        button.click();
    }

    public String getGreetings() {
        UiObject2 greetMessage = mDevice.findObject(By.text(greetText));
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

