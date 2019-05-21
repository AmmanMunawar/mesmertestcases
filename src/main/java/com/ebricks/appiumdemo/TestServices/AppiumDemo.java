package com.ebricks.appiumdemo.TestServices;

import com.ebricks.appiumdemo.scriptModel.ScriptInputData;
import com.ebricks.appiumdemo.scriptModel.UIElement;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
//For XML Librairies
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class AppiumDemo {
    private static final Logger LOGGER = LogManager.getLogger(AppiumDemo.class.getName());
    private static AndroidDriver<MobileElement> driver;
    private ScriptInputData scriptInputData;
    private ExecutorService scriptExecutor;
    private List<Future<UIElement>> uiElementFutureList;

    public static void initialiazeConnectionWithAppium() {

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

    public void init() {

        initialiazeConnectionWithAppium();
        scriptExecutor = Executors.newFixedThreadPool(3);
        this.uiElementFutureList = new ArrayList<Future<UIElement>>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            scriptInputData = objectMapper.readValue(new FileReader(System.getProperty("user.dir") + "/resources/appelements.json")
                    , ScriptInputData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testProcess() {

        for (final UIElement uiElement : this.scriptInputData.getUiElementList()) {

            Future<UIElement> uiElementFuture = this.scriptExecutor.submit(new Callable<UIElement>() {
                Document xmlDocument = convertXMLStringToDocument(driver.getPageSource());
                NodeList nodeList = xmlDocument.getElementsByTagName("*");

                public UIElement call() throws Exception {

                    UIElement uiElement1 = createUIElementAndCompareTwoObjects(nodeList, uiElement);
                    driver.findElement(By.xpath("//android.widget.Button[@text='" + uiElement1.getText() + "']")).click();
                    Thread.sleep(2000);
                    return uiElement1;
                }
            });
            this.uiElementFutureList.add(uiElementFuture);
        }
        for (Future<UIElement> uiElementFuture : this.uiElementFutureList) {
            try {
                uiElementFuture.get();
            } catch (InterruptedException e) {
                LOGGER.error(e);
            } catch (ExecutionException e) {
                LOGGER.error(e);
            }

        }
    }

    public UIElement createUIElementAndCompareTwoObjects(NodeList xmlNodeList, UIElement uiElement) {

        UIElement uiElementTemp = new UIElement();
        for (int i = 0; i < xmlNodeList.getLength(); i++) {

            Node node = xmlNodeList.item(i);
            Element eElement = (Element) node;
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                uiElementTemp.setText(eElement.getAttribute("text"));
                uiElementTemp.setBounds(eElement.getAttribute("bounds"));
                uiElementTemp.setCheckable(Boolean.valueOf(eElement.getAttribute("checkable")));
                uiElementTemp.setChecked(Boolean.valueOf(eElement.getAttribute("checked")));
                uiElementTemp.setClickable(Boolean.valueOf(eElement.getAttribute("clickable")));
                uiElementTemp.setContentDesc(eElement.getAttribute("content-desc"));
                uiElementTemp.setEnabled(Boolean.valueOf(eElement.getAttribute("enabled")));
                uiElementTemp.setFocusable(Boolean.valueOf(eElement.getAttribute("focusable")));
                uiElementTemp.setLongClickable(Boolean.valueOf(eElement.getAttribute("long-clickable")));
                uiElementTemp.setFocused(Boolean.valueOf(eElement.getAttribute("focused")));
                uiElementTemp.setPassword(Boolean.valueOf(eElement.getAttribute("Password")));
                uiElementTemp.setPkg(eElement.getAttribute("package"));
                uiElementTemp.setResourceId(eElement.getAttribute("resource-id"));
                uiElementTemp.setScrollable(Boolean.valueOf(eElement.getAttribute("scrollable")));
                uiElementTemp.setSelected(Boolean.valueOf(eElement.getAttribute("selected")));
                uiElementTemp.setType(eElement.getAttribute("class"));
            }
            if (uiElement.isEqual(uiElementTemp)) {
                return uiElementTemp;
            }
        }
        return null;
    }

    public static Document convertXMLStringToDocument(String xmlStr) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document xmlDocument = builder.parse(new InputSource(new StringReader(xmlStr)));
            return xmlDocument;
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return null;
    }

    public void end() {
        if (this.scriptExecutor != null) {

            this.scriptExecutor.shutdown();
            try {
                if (!this.scriptExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
                    LOGGER.info("Task didn't complete in given time");
                }
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
        driver.quit();
    }
}
