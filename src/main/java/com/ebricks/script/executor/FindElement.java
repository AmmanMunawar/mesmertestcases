package com.ebricks.script.executor;

import com.ebricks.script.FindAttributesWeightage;
import com.ebricks.script.Path;
import com.ebricks.script.ScriptReadWriteFromFile;
import com.ebricks.script.model.UIElement;
import com.ebricks.script.service.AppiumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FindElement {

    private static final Logger LOGGER = LogManager.getLogger(FindElement.class.getName());
    private static FindElement instance;

    private FindElement() {
    }

    public static FindElement getInstance() {
        if (instance == null) {
            instance = new FindElement();
        }
        return instance;
    }

    public UIElement findUIElementByXY(int x, int y, String xmlString) {

        Document xmlDocument = convertXMLStringToDocument(xmlString);
        NodeList xmlNodeList = xmlDocument.getElementsByTagName("*");
        List<Element> elements = new ArrayList<>();

        for (int i = 0; i < xmlNodeList.getLength(); i++) {

            Node node = xmlNodeList.item(i);
            Element eElement = (Element) node;
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                if (eElement.getAttribute("bounds") != "") {

                    if (this.compareBoundValues(x, y, eElement.getAttribute("bounds"))) {

                        elements.add(eElement);
                    }
                }
            }

        }
        return uiElementwithShortestDistance(x, y, elements);
    }

    public UIElement findElementByudaId(int udid, String domName) throws IOException {

        Document xmlDocument = convertXMLStringToDocument(ScriptReadWriteFromFile.getInstance().readXMLFile(Path.getinstance().getTestCasePath() + domName));
        NodeList xmlNodeList = xmlDocument.getElementsByTagName("*");

        for (int i = 0; i < xmlNodeList.getLength(); i++) {

            Node node = xmlNodeList.item(i);
            Element eElement = (Element) node;

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                if (String.valueOf(udid).equals(eElement.getAttribute("udaId"))) {

                    return createUIElementObject(eElement);
                }
            }
        }
        return null;
    }

    public boolean findElementByXpath(String Xpath){

        return AppiumService.getInstance().getDriver().findElementByXPath(Xpath) != null;
    }

    public UIElement findUIElement(String xmlString, UIElement uiElement, int pointX, int pointY, boolean elementForUDID) {

        if (uiElement == null) {

            return null;
        }

        FindAttributesWeightage checkIfSameTextonPageSource = FindAttributesWeightage.getInstance();
        HashMap<Double, UIElement> capitalCities = new HashMap<Double, UIElement>();
        Document xmlDocument = convertXMLStringToDocument(xmlString);
        NodeList xmlNodeList = xmlDocument.getElementsByTagName("*");

        for (int i = 0; i < xmlNodeList.getLength(); i++) {

            UIElement uiElementTemp = new UIElement();
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
                uiElementTemp.setId(eElement.getAttribute("resource-id"));
                uiElementTemp.setScrollable(Boolean.valueOf(eElement.getAttribute("scrollable")));
                uiElementTemp.setSelected(Boolean.valueOf(eElement.getAttribute("selected")));
                uiElementTemp.setType(eElement.getAttribute("class"));

            }
            if (!elementForUDID) {
                if (uiElement.getType().equals(uiElementTemp.getType())) {

                    if (
                            checkIfSameTextonPageSource.isFullyFoundedSameTextPoints() ||
                                    checkIfSameTextonPageSource.isIsgreaterthen80SameTextPoints()
                    ) {

                        if (FindAttributesWeightage.jaroDistance(uiElement.getText(), uiElementTemp.getText()) >= 0.8) {

                            capitalCities.put(uiElement.isEqual(uiElementTemp, pointX, pointY, elementForUDID), uiElementTemp);
                        }
                    } else {

                        capitalCities.put(uiElement.isEqual(uiElementTemp, pointX, pointY, elementForUDID), uiElementTemp);
                    }
                }
            } else{
                //this is finding for udaElement

                capitalCities.put(uiElement.isEqual(uiElementTemp, pointX, pointY, elementForUDID), uiElementTemp);
            }
        }

        return capitalCities.get(Collections.max(capitalCities.keySet()));
    }

    private boolean compareBoundValues(int x, int y, String bounds) {

        bounds = bounds.replaceAll("\\D", ",");
        String[] splitted = bounds.split(",");
        int boundX = Integer.parseInt(splitted[1]);
        int boundY = Integer.parseInt(splitted[2]);
        int boundWidth = Integer.parseInt(splitted[4]);
        int boundHeight = Integer.parseInt(splitted[5]);
        return x >= boundX && y >= boundY && x <= boundWidth && y <= boundHeight;

    }

    private UIElement uiElementwithShortestDistance(int x, int y, List<Element> elements) {

        Element foundelement = elements.get(0);
        String bounds[] = foundelement.getAttribute("bounds").replaceAll("\\D", ",").split(",");
        int smallWidth = Integer.parseInt(bounds[4]);
        int smallheight = Integer.parseInt(bounds[5]);

        for (Element element : elements) {

            int boundX = Integer.parseInt(element.getAttribute("bounds").replaceAll("\\D", ",").split(",")[1]);
            int boundY = Integer.parseInt(element.getAttribute("bounds").replaceAll("\\D", ",").split(",")[2]);
            int boundWidth = Integer.parseInt(element.getAttribute("bounds").replaceAll("\\D", ",").split(",")[4]);
            int boundHeight = Integer.parseInt(element.getAttribute("bounds").replaceAll("\\D", ",").split(",")[5]);

            if (boundWidth <= smallWidth && boundHeight <= smallheight &&
                    x >= boundX && y >= boundY && x <= boundWidth && y <= boundHeight) {

                smallWidth = boundWidth;
                smallheight = boundHeight;
                foundelement = element;
            }
        }

        return createUIElementObject(foundelement);
    }

    public UIElement createUIElementObject(Element eElement) {

        UIElement uiElementTemp = new UIElement();
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
        uiElementTemp.setId(eElement.getAttribute("resource-id"));
        uiElementTemp.setScrollable(Boolean.valueOf(eElement.getAttribute("scrollable")));
        uiElementTemp.setSelected(Boolean.valueOf(eElement.getAttribute("selected")));
        uiElementTemp.setType(eElement.getAttribute("class"));

        return uiElementTemp;
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

    public ArrayList<Integer> findElementSize(String bounds){

        ArrayList<Integer> elementSize= new ArrayList<Integer>();
        bounds = bounds.replaceAll("\\D", ",");
        String[] splitted = bounds.split(",");
        int boundX = Integer.parseInt(splitted[1]);
        int boundY = Integer.parseInt(splitted[2]);
        int boundWidth = Integer.parseInt(splitted[4]);
        int boundHeight = Integer.parseInt(splitted[5]);
        elementSize.add(boundWidth-boundX);
        elementSize.add(boundHeight-boundY);
        return elementSize;
    }

    public ArrayList<Integer> findElementPosition(String bounds){

        ArrayList<Integer> elementSize= new ArrayList<Integer>();
        bounds = bounds.replaceAll("\\D", ",");
        String[] splitted = bounds.split(",");
        int boundX = Integer.parseInt(splitted[1]);
        int boundY = Integer.parseInt(splitted[2]);
        elementSize.add(boundX);
        elementSize.add(boundY);
        return elementSize;
    }
}