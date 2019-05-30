package com.ebricks.script.service;

import com.ebricks.script.Path;
import com.ebricks.script.config.Configuration;
import com.ebricks.script.model.UIElement;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;

public class AppiumService {

    private static final Logger LOGGER = LogManager.getLogger(AppiumService.class.getName());
    private DesiredCapabilities caps = new DesiredCapabilities();
    private Configuration configuration = Configuration.getInstance();
    private AndroidDriver<MobileElement> driver;
    private static AppiumService instance;
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private DesiredCapabilities cap;


    private AppiumService() {}

    private void startServer() {

        cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");
        builder = new AppiumServiceBuilder();
        builder.withIPAddress(configuration.getAppiumIP());
        builder.usingPort(configuration.getAppiumPort());
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public static AppiumService getInstance() {
        if (instance == null) {
            instance = new AppiumService();
        }
        return instance;
    }

    public void createSession() {
        startServer();
        caps.setCapability("deviceName", configuration.getDeviceName());
        caps.setCapability("platformName", configuration.getPlatformName());
        caps.setCapability("platformVersion", configuration.getPlatformVersion());
        caps.setCapability("automationName", configuration.getAutomationName());
        caps.setCapability("app", System.getProperty("user.dir") + "/resources/com.coupons.ciapp-4.3.apk");
        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://" + configuration.getAppiumIP() + ":" + configuration.getAppiumPort() + "/wd/hub"), caps);
        } catch (MalformedURLException e) {
            LOGGER.error(e);
        }
    }

    public String getPageSourse() {
        return driver.getPageSource();
    }

    public void back() {

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public void home() {
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }

    public void lockDevice() {
        driver.lockDevice();
    }

    public void unlockDevice() {
        driver.unlockDevice();
    }

    public void launchApp() {
        driver.launchApp();
    }

    public void swipe(int startX, int startY, int endX, int endY) {

        TouchAction swipe = new TouchAction(driver);
        swipe.press(point(startX, startY)).waitAction(waitOptions(ofSeconds(3))).
                moveTo(point(endX, endY)).release().perform();
    }

    public void click(UIElement uiElement) {
        MobileElement mobileElement = driver.findElement(By.id(uiElement.getResourceId()));
        mobileElement.click();
    }

    public void setValue(UIElement uiElement, String text) {

        MobileElement mobileElement = driver.findElement(By.id(uiElement.getResourceId()));
        mobileElement.click();
        mobileElement.sendKeys(text);
    }

    private void stopServer() {
        service.stop();
    }

    public void quit() {

        driver.quit();
        stopServer();
    }

    public String getScreenShotAs(int id) {

        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(Path.getinstance().getScreenShotPath() + "/" + id + ".jpg");
        try {

            FileUtils.copyFile(srcFile, targetFile);
        } catch (IOException e) {

            LOGGER.error("Exception", e);
        }
        return id + ".jpg";
    }
}
