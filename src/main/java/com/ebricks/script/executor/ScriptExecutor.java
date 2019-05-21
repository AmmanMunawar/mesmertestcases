package com.ebricks.script.executor;

import com.ebricks.script.config.Configuration;
import com.ebricks.script.model.ScriptInputData;
import com.ebricks.script.model.UIElement;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class ScriptExecutor {
    private static final Logger LOGGER = LogManager.getLogger(ScriptExecutor.class.getName());
    private static AndroidDriver<MobileElement> driver;
    private ScriptInputData scriptInputData;
    private UIElement uiElement;
    private Configuration configuration = Configuration.getInstance();


    public void initialiazeConnectionWithAppium() {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName",configuration.getDeviceName());
        caps.setCapability("platformName", configuration.getPlatformName());
        caps.setCapability("platformVersion", configuration.getPlatformVersion());
        caps.setCapability("automationName", configuration.getAutomationName());
        caps.setCapability("app", System.getProperty("user.dir") + "/resources/spellingOverlapError.apk");
        try {
            driver = new AndroidDriver<MobileElement>(new URL(configuration.getAppiumURL()), caps);
        } catch (MalformedURLException e) {
            LOGGER.error(e);
        }
    }

    public void init() {

        initialiazeConnectionWithAppium();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            scriptInputData = objectMapper.readValue(new FileReader(System.getProperty("user.dir") + "/resources/appelements.json")
                    , ScriptInputData.class);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public void process() throws InterruptedException {

        for (final UIElement uiElement : this.scriptInputData.getUiElementList()) {

            Document xmlDocument = convertXMLStringToDocument(driver.getPageSource());
            NodeList nodeList = xmlDocument.getElementsByTagName("*");
            this.uiElement=uiElement;
            UIElement uiElement1 = createUIElementfromNodelist(nodeList);
            MobileElement mobileElement = driver.findElement(By.xpath("//"+uiElement1.getType()+"[@text='" + uiElement1.getText() + "']"));
            mobileElement.click();
            Thread.sleep(2000);
        }
    }

    public boolean compareUIElemetsobjects(UIElement uiElement){
        if(this.uiElement.isEqual(uiElement)){
            return true;
        }
        return false;
    }

    public UIElement createUIElementfromNodelist(NodeList xmlNodeList) {

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
            if (this.compareUIElemetsobjects(uiElementTemp)) {
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
        driver.quit();
    }
}
