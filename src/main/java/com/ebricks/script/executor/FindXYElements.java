package com.ebricks.script.executor;

import com.ebricks.script.model.UIElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class FindXYElements {

    private static final Logger LOGGER = LogManager.getLogger(FindXYElements.class.getName());
    private List<Element> elements = new ArrayList<>();
    private static FindXYElements instance;

    private FindXYElements() {
    }

    public static FindXYElements getInstance() {
        if (instance == null) {
            instance = new FindXYElements();
        }
        return instance;
    }

    public UIElement findUIelement(int x, int y, String xmlString) {
        Document xmlDocument = convertXMLStringToDocument(xmlString);
        NodeList xmlNodeList = xmlDocument.getElementsByTagName("*");
        for (int i = 0; i < xmlNodeList.getLength(); i++) {
            Node node = xmlNodeList.item(i);
            Element eElement = (Element) node;
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if(eElement.getAttribute("bounds")!="") {
                    if (this.compareBoundValues(x, y, eElement.getAttribute("bounds"))) {
                        this.elements.add(eElement);
                    }
                }
            }

        }
        return uiElementwithShortestDistance();
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

    public boolean compareBoundValues(int x, int y, String bounds) {
        bounds = bounds.replaceAll("\\D", ",");
        String[] splitted = bounds.split(",");
        int boundX = Integer.parseInt(splitted[1]);
        int boundY = Integer.parseInt(splitted[2]);
        int boundWidth = Integer.parseInt(splitted[4]);
        int boundHeight = Integer.parseInt(splitted[5]);
        if (x >= boundX && y >= boundY && x <= boundWidth && y <= boundHeight) {
            return true;
        }
        return false;
    }

    public UIElement uiElementwithShortestDistance() {

        Element foundelement = this.elements.get(0);
        String bounds[] = foundelement.getAttribute("bounds").replaceAll("\\D", ",").split(",");
        int smallWidth = Integer.parseInt(bounds[4]);
        int smallheight = Integer.parseInt(bounds[5]);
        for (Element element : this.elements) {

            int boundWidth = Integer.parseInt(element.getAttribute("bounds").replaceAll("\\D", ",").split(",")[4]);
            int boundHeight = Integer.parseInt(element.getAttribute("bounds").replaceAll("\\D", ",").split(",")[5]);

            if (boundWidth <= smallWidth && boundHeight <= smallheight) {
                smallWidth = boundWidth;
                smallheight = boundHeight;
                foundelement = element;
            }
        }
        for (int i=0;i<this.elements.size();i++){
            this.elements.remove(i);
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
        uiElementTemp.setResourceId(eElement.getAttribute("resource-id"));
        uiElementTemp.setScrollable(Boolean.valueOf(eElement.getAttribute("scrollable")));
        uiElementTemp.setSelected(Boolean.valueOf(eElement.getAttribute("selected")));
        uiElementTemp.setType(eElement.getAttribute("class"));
        return uiElementTemp;
    }



}
