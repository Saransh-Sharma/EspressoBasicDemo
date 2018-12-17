package com.mycompany.greeter;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;

public class UtilityClass {

    public void wait(String text, int waitTime){
         UiDevice mDevice;
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.wait(Until.findObject(By.res(text)),waitTime);

    }
}
