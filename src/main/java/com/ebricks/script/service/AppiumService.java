package com.ebricks.script.service;

import com.ebricks.script.Path;
import com.ebricks.script.config.Configuration;
import com.ebricks.script.executor.FindPointPercentage;
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
import static java.time.Duration.ofMillis;


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



    public static AppiumService getInstance() {
        if (instance == null) {
            instance = new AppiumService();
        }
        return instance;
    }

    public void createSession() {

        startServer();
        cap.setCapability("newCommandTimeout",12000);
        caps.setCapability("deviceName", configuration.getDEVICE().getUdid());
        caps.setCapability("platformName", configuration.getDEVICE().getPlatform());
        caps.setCapability("platformVersion", configuration.getDEVICE().getOs());
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", System.getProperty("user.dir") + "/resources/"+configuration.getBUILD().getFILE_NAME());

        try {

            driver = new AndroidDriver<MobileElement>(new URL(  "http://127.0.0.1:"+configuration.getAPPIUM_PORT() + "/wd/hub"), caps);
        } catch (MalformedURLException e) {
            LOGGER.error(e);
        }
    }

    private void startServer() {

        cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");
        cap.setCapability("newCommandTimeout",12000);
        builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(configuration.getAPPIUM_PORT());
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
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

    public void swipe(int startX, int startY, int endX, int endY, int touchDuration) {

        TouchAction swipe = new TouchAction(driver);
        swipe.press(point(startX, startY)).waitAction(waitOptions(ofMillis(touchDuration))).
                moveTo(point(endX, endY)).release().perform();
    }

    public void click(UIElement uiElement) {

        int pointX = FindPointPercentage.getInstance().getPointX(uiElement.getBounds());
        int pointY = FindPointPercentage.getInstance().getPointY(uiElement.getBounds());
        LOGGER.info(pointX);
        LOGGER.info(pointY);
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(point(pointX,pointY)).perform();

    }

    public void setValue(UIElement uiElement, String text) {

        MobileElement mobileElement = null;
        try {
            mobileElement = driver.findElement(By.xpath("//*[@text='" + uiElement.getText() + "']"));
        }
        catch (Exception e){

            LOGGER.info("Couldn't found element with xpath");
            LOGGER.info(uiElement.getText());
            mobileElement = driver.findElement(By.id(uiElement.getId()));
        }
        finally {

            mobileElement.sendKeys(text);
        }

    }

    private void stopServer() {
        service.stop();
    }

    public void quit() {

        driver.quit();
        stopServer();
    }

    public String getScreenShotAs(String screenshotname, boolean isloadedScreenShot) {

        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        File targetFile = null;

        if(isloadedScreenShot){
            targetFile = new File(Path.getinstance().getLoadingDirPath() + "/" + screenshotname);
        }
        else{
            targetFile = new File(Path.getinstance().getOutpurImageDir() + "/" + screenshotname);
        }
        try {

            FileUtils.copyFile(srcFile, targetFile);
        } catch (IOException e) {

            LOGGER.error("Exception", e);
        }
        return screenshotname;
    }

    public AndroidDriver<MobileElement> getDriver() {
        return driver;
    }

}