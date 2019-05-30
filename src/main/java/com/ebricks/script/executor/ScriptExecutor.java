package com.ebricks.script.executor;

import com.ebricks.script.ScriptReadWriteFromFile;
import com.ebricks.script.Path;
import com.ebricks.script.model.ScriptInputData;
import com.ebricks.script.model.Step;
import com.ebricks.script.model.UIElement;
import com.ebricks.script.service.AppiumService;

import com.ebricks.script.stepexecutor.StepExecutor;
import com.ebricks.script.stepexecutor.StepFactory;
import com.ebricks.script.stepexecutor.response.StepExecutorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class ScriptExecutor {

    private static final Logger LOGGER = LogManager.getLogger(ScriptExecutor.class.getName());
    private ScriptInputData scriptInputData;
    private StepExecutorResponceWrapper stepExecutorResponces;


    public void init() {

        AppiumService.getInstance().createSession();
        Path.getinstance().makeDirectory();
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            scriptInputData = objectMapper.readValue(new FileReader(System.getProperty("user.dir") + "/resources/elements.json")
                    , ScriptInputData.class);
        } catch (IOException e) {

            LOGGER.error("IOException", e);
        }
    }




    public void process() throws InterruptedException {

        stepExecutorResponces = new StepExecutorResponceWrapper();
        for (Step step : this.scriptInputData.getSteps()) {

            StepExecutor stepExecutor = StepFactory.getInstance().getStepExecutor(step);
            stepExecutor.init();
            StepExecutorResponse stepExecutorResponse = stepExecutor.execute();
            Thread.sleep(3000);
            this.stepExecutorResponces.getStepExecutorResponses().add(stepExecutorResponse);

        }

    }

    public boolean compareUIElemetsobjects(UIElement uiElement, UIElement uiElement1) {
        return uiElement.isEqual(uiElement1);
    }

    public UIElement findUIElement(String xmlString, UIElement uiElement) {

        if (uiElement == null) {

            return null;
        }
        Document xmlDocument = convertXMLStringToDocument(xmlString);
        NodeList xmlNodeList = xmlDocument.getElementsByTagName("*");
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
            if (this.compareUIElemetsobjects(uiElementTemp, uiElement)) {

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

            LOGGER.error("Exception", e);
        }
        return null;
    }

    public void end() {

        ScriptReadWriteFromFile.getInstance().saveStepExecutorReponse(this.stepExecutorResponces);
        AppiumService.getInstance().quit();
    }
}
