package com.ebricks.appiumdemo.TestServices;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDemo {
    private static final Logger LOGGER = LogManager.getLogger(AppiumDemo.class.getName());
    private static AndroidDriver<MobileElement> driver;


    public static void init() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "192.168.155.101.5555");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9");
        caps.setCapability("automationName", "Appium");
        caps.setCapability("app", System.getProperty("user.dir") + "/resources/spellingOverlapError.apk");
        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        } catch (MalformedURLException e) {
            LOGGER.error(e);
        }
    }

    public void testProcess() throws InterruptedException {
        KeyEvent appiumPressBackButton = new KeyEvent(AndroidKey.BACK);
        for (int i = 1; i <= 5; i++) {
            Thread.sleep(2000);
            driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.Button[" + i + "]")).click();
            Thread.sleep(5000);
            driver.pressKey(appiumPressBackButton);
        }
    }

    public void end() {
        driver.quit();
    }
}
