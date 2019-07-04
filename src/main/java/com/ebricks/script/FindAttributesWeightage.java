package com.ebricks.script;

import com.ebricks.script.executor.FindElement;
import com.ebricks.script.model.UIElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindAttributesWeightage {

    private static final Logger LOGGER = LogManager.getLogger(FindAttributesWeightage.class.getName());
    private int textWeightage;
    private double positionWeightage;
    private int boundsWeightage;
    private int pkgWeightage;
    private int classWeightage;
    private int contentDescWeightage;
    private int idWeightage;
    private int checkableWeightage;
    private int checkedWeightage;
    private int clickableWeightage;
    private int enabledWeightage;
    private int focusableWeightage;
    private int focusedWeightage;
    private int scrollableWeightage;
    private int longClickableWeightage;
    private int passwordWeightage;
    private int selectedWeightage;
    private boolean isTextPointslessThen80;
    private boolean isTextPointsGreaterThen80;
    private boolean islessthen80SameTextPoints;
    private boolean isgreaterthen80SameTextPoints;
    private boolean isFullyFoundedSameTextPoints;
    private boolean isTextfullyFounded = false;
    private static FindAttributesWeightage instance;

    private FindAttributesWeightage() {
    }

    public static FindAttributesWeightage getInstance() {

        if (instance == null) {
            instance = new FindAttributesWeightage();
        }
        return instance;
    }


    public void findWeightage(UIElement uiElement, String pageSource) {

        List<UIElement> uiElementPageSource = addElementinList(uiElement, pageSource); // this is for PageSource
        HashMap<String, String> textWeightageHashmap = new HashMap<String, String>();
        HashMap<String, String> resIdWeigtageHashmap = new HashMap<String, String>();
        HashMap<String, String> pkgWeightageHashmap = new HashMap<String, String>();

        int posWeightage = 1;
        int sizeWeightage = 1;
        int i = 0;

        isTextPointslessThen80 = false;
        isTextPointsGreaterThen80 = false;
        islessthen80SameTextPoints = false;
        isgreaterthen80SameTextPoints = false;
        isFullyFoundedSameTextPoints = false;
        isTextfullyFounded = false;
        List<Double> textComparisonPointsList = new ArrayList<>();

        while (i < uiElementPageSource.size()) {

            textWeightageHashmap.put(uiElementPageSource.get(i).getText(), "1");
            resIdWeigtageHashmap.put(uiElementPageSource.get(i).getId(), "1");
            pkgWeightageHashmap.put(uiElementPageSource.get(i).getPkg(), "1");
            double textComparisionPoint = jaroDistance(uiElement.getText(), uiElementPageSource.get(i).getText());

            if (textComparisionPoint == 1.0) {

                isTextfullyFounded = true;

                if (ifTextwithSamePointExist(textComparisonPointsList, textComparisionPoint) == true) {

                    isFullyFoundedSameTextPoints = true;
                }
                textComparisonPointsList.add(textComparisionPoint);

            } else if (textComparisionPoint >= 0.80) {

                isTextPointsGreaterThen80 = true;
                if (ifTextwithSamePointExist(textComparisonPointsList, textComparisionPoint) == true) {
                    isgreaterthen80SameTextPoints = true;
                }
                textComparisonPointsList.add(Double.valueOf(textComparisionPoint));
            } else if (textComparisionPoint < 0.80) {
                isTextPointslessThen80 = true;
                posWeightage = posWeightage + 1;
                sizeWeightage = sizeWeightage + 1;
                if (ifTextwithSamePointExist(textComparisonPointsList, textComparisionPoint) == true) {
                    islessthen80SameTextPoints = true;
                }
                textComparisonPointsList.add(Double.valueOf(textComparisionPoint));
            }

            i = i + 1;
        }
        if (isTextfullyFounded == true && isFullyFoundedSameTextPoints == false) {
            setWeightage(textWeightageHashmap.size(), 0, 1, pkgWeightageHashmap.size(), 1, 1,
                    resIdWeigtageHashmap.size(), 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1
            );
            return;
        } else if (isTextfullyFounded == true && isFullyFoundedSameTextPoints == true) {
            setWeightage(textWeightageHashmap.size(), 1 / uiElementPageSource.size(), 1, pkgWeightageHashmap.size(), 1, 1,
                    resIdWeigtageHashmap.size(), 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1);
            return;
        } else if (isTextPointsGreaterThen80 == true && isgreaterthen80SameTextPoints == false) {
            setWeightage(textWeightageHashmap.size(), 0, 1, pkgWeightageHashmap.size(), 1, 1,
                    resIdWeigtageHashmap.size(), 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1
            );
            return;
        } else if (isTextPointsGreaterThen80 == true && isgreaterthen80SameTextPoints == true) {
            setWeightage(textWeightageHashmap.size(), 0.8 / uiElementPageSource.size(), 1, pkgWeightageHashmap.size(), 1, 1,
                    resIdWeigtageHashmap.size(), 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1);
            return;
        } else if (isTextPointslessThen80 == true && isTextPointsGreaterThen80 == false && islessthen80SameTextPoints == true) {
            setWeightage(textWeightageHashmap.size(), posWeightage, sizeWeightage, pkgWeightageHashmap.size(), 1, 1,
                    resIdWeigtageHashmap.size(), 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1
            );
            return;
        } else if (isTextPointslessThen80 == true && isTextPointsGreaterThen80 == false && islessthen80SameTextPoints == false) {
            setWeightage(textWeightageHashmap.size(), posWeightage, sizeWeightage, pkgWeightageHashmap.size(), 1, 1,
                    resIdWeigtageHashmap.size(), 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1
            );
        }
    }

    private boolean ifTextwithSamePointExist(List<Double> textComparisonPointsList, double texComparisontPoint) {

        for (int i = 0; i < textComparisonPointsList.size(); i++) {
            if (texComparisontPoint == textComparisonPointsList.get(i)) {
                return true;
            }
        }
        return false;
    }

    private void setWeightage(
            int textWeightage,
            double positionWeightage,
            int boundsWeightage,
            int pkgWeightage,
            int classWeightage,
            int contentDescWeightage,
            int idWeightage,
            int checkableWeightage,
            int checkedWeightage,
            int clickableWeightage,
            int enabledWeightage,
            int focusableWeightage,
            int focusedWeightage,
            int scrollableWeightage,
            int longClickableWeightage,
            int passwordWeightage,
            int selectedWeightage) {

        setPositionWeightage(positionWeightage);
        setTextWeightage(textWeightage);
        setBoundsWeightage(boundsWeightage);
        setIdWeightage(idWeightage);
        setPkgWeightage(pkgWeightage);
        setCheckableWeightage(checkableWeightage);
        setCheckedWeightage(checkedWeightage);
        setClassWeightage(classWeightage);
        setClickableWeightage(clickableWeightage);
        setContentDescWeightage(contentDescWeightage);
        setEnabledWeightage(enabledWeightage);
        setFocusableWeightage(focusableWeightage);
        setFocusedWeightage(focusedWeightage);
        setLongClickableWeightage(longClickableWeightage);
        setPasswordWeightage(passwordWeightage);
        setSelectedWeightage(selectedWeightage);
        setScrollableWeightage(scrollableWeightage);

    }

    private List<UIElement> addElementinList(UIElement uiElement, String xmlString) {

        Document xmlDocument = FindElement.getInstance().convertXMLStringToDocument(xmlString);
        NodeList xmlNodeList = xmlDocument.getElementsByTagName("*");
        List<UIElement> uiElements = new ArrayList<>();

        for (int i = 0; i < xmlNodeList.getLength(); i++) {

            Node node = xmlNodeList.item(i);
            Element eElement = (Element) node;

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                if (eElement.getAttribute("class").equals(uiElement.getType())) {

                    uiElements.add(FindElement.getInstance().createUIElementObject(eElement));
                }
            }

        }
        return uiElements;
    }

    public static double jaroDistance(String s, String t) {

        int s_len = s.length();
        int t_len = t.length();
        if (s_len == 0 && t_len == 0) return 1;
        int match_distance = Integer.max(s_len, t_len) / 2 - 1;
        boolean[] s_matches = new boolean[s_len];
        boolean[] t_matches = new boolean[t_len];
        int matches = 0;
        int transpositions = 0;

        for (int i = 0; i < s_len; i++) {
            int start = Integer.max(0, i - match_distance);
            int end = Integer.min(i + match_distance + 1, t_len);

            for (int j = start; j < end; j++) {
                if (t_matches[j]) continue;
                if (s.charAt(i) != t.charAt(j)) continue;
                s_matches[i] = true;
                t_matches[j] = true;
                matches++;
                break;
            }
        }

        if (matches == 0) return 0;

        int k = 0;
        for (int i = 0; i < s_len; i++) {
            if (!s_matches[i]) continue;
            while (!t_matches[k]) k++;
            if (s.charAt(i) != t.charAt(k)) transpositions++;
            k++;
        }

        return (((double) matches / s_len) +
                ((double) matches / t_len) +
                (((double) matches - transpositions / 2.0) / matches)) / 3.0;
    }

    public int getTextWeightage() {
        return textWeightage;
    }

    public void setTextWeightage(int textWeightage) {
        this.textWeightage = textWeightage;
    }

    public double getPositionWeightage() {
        return positionWeightage;
    }

    public void setPositionWeightage(double positionWeightage) {
        this.positionWeightage = positionWeightage;
    }

    public int getBoundsWeightage() {
        return boundsWeightage;
    }

    public void setBoundsWeightage(int boundsWeightage) {
        this.boundsWeightage = boundsWeightage;
    }

    public int getPkgWeightage() {
        return pkgWeightage;
    }

    public void setPkgWeightage(int pkgWeightage) {
        this.pkgWeightage = pkgWeightage;
    }

    public int getClassWeightage() {
        return classWeightage;
    }

    public void setClassWeightage(int classWeightage) {
        this.classWeightage = classWeightage;
    }

    public int getContentDescWeightage() {
        return contentDescWeightage;
    }

    public void setContentDescWeightage(int contentDescWeightage) {
        this.contentDescWeightage = contentDescWeightage;
    }

    public int getIdWeightage() {
        return idWeightage;
    }

    public void setIdWeightage(int idWeightage) {
        this.idWeightage = idWeightage;
    }

    public int getCheckableWeightage() {
        return checkableWeightage;
    }

    public void setCheckableWeightage(int checkableWeightage) {
        this.checkableWeightage = checkableWeightage;
    }

    public int getCheckedWeightage() {
        return checkedWeightage;
    }

    public void setCheckedWeightage(int checkedWeightage) {
        this.checkedWeightage = checkedWeightage;
    }

    public int getClickableWeightage() {
        return clickableWeightage;
    }

    public void setClickableWeightage(int clickableWeightage) {
        this.clickableWeightage = clickableWeightage;
    }

    public int getEnabledWeightage() {
        return enabledWeightage;
    }

    public void setEnabledWeightage(int enabledWeightage) {
        this.enabledWeightage = enabledWeightage;
    }

    public int getFocusableWeightage() {
        return focusableWeightage;
    }

    public void setFocusableWeightage(int focusableWeightage) {
        this.focusableWeightage = focusableWeightage;
    }

    public int getFocusedWeightage() {
        return focusedWeightage;
    }

    public void setFocusedWeightage(int focusedWeightage) {
        this.focusedWeightage = focusedWeightage;
    }

    public int getScrollableWeightage() {
        return scrollableWeightage;
    }

    public void setScrollableWeightage(int scrollableWeightage) {
        this.scrollableWeightage = scrollableWeightage;
    }

    public int getLongClickableWeightage() {
        return longClickableWeightage;
    }

    public void setLongClickableWeightage(int longClickableWeightage) {
        this.longClickableWeightage = longClickableWeightage;
    }

    public int getPasswordWeightage() {
        return passwordWeightage;
    }

    public void setPasswordWeightage(int passwordWeightage) {
        this.passwordWeightage = passwordWeightage;
    }

    public int getSelectedWeightage() {
        return selectedWeightage;
    }

    public void setSelectedWeightage(int selectedWeightage) {
        this.selectedWeightage = selectedWeightage;
    }

    public boolean isTextPointslessThen80() {
        return isTextPointslessThen80;
    }

    public void setTextPointslessThen80(boolean textPointslessThen80) {
        isTextPointslessThen80 = textPointslessThen80;
    }

    public boolean isTextPointsGreaterThen80() {
        return isTextPointsGreaterThen80;
    }

    public void setTextPointsGreaterThen80(boolean textPointsGreaterThen80) {
        isTextPointsGreaterThen80 = textPointsGreaterThen80;
    }

    public boolean isIslessthen80SameTextPoints() {
        return islessthen80SameTextPoints;
    }

    public void setIslessthen80SameTextPoints(boolean islessthen80SameTextPoints) {
        this.islessthen80SameTextPoints = islessthen80SameTextPoints;
    }

    public boolean isIsgreaterthen80SameTextPoints() {
        return isgreaterthen80SameTextPoints;
    }

    public void setIsgreaterthen80SameTextPoints(boolean isgreaterthen80SameTextPoints) {
        this.isgreaterthen80SameTextPoints = isgreaterthen80SameTextPoints;
    }

    public boolean isFullyFoundedSameTextPoints() {
        return isFullyFoundedSameTextPoints;
    }

    public void setFullyFoundedSameTextPoints(boolean fullyFoundedSameTextPoints) {
        isFullyFoundedSameTextPoints = fullyFoundedSameTextPoints;
    }

    public boolean isTextfullyFounded() {
        return isTextfullyFounded;
    }

    public void setTextfullyFounded(boolean textfullyFounded) {
        isTextfullyFounded = textfullyFounded;
    }
}
