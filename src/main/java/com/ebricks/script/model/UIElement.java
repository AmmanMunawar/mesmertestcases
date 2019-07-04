package com.ebricks.script.model;

import com.ebricks.script.FindAttributesWeightage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.lang.Math;
import java.util.logging.Logger;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UIElement {

    private String text;
    private String type;
    private String pkg;
    private String contentDesc;
    private Boolean checkable;
    private Boolean checked;
    private Boolean clickable;
    private Boolean enabled;
    private Boolean focusable;
    private Boolean focused;
    private Boolean scrollable;
    private Boolean longClickable;
    private Boolean password;
    private Boolean selected;
    private String bounds;
    private String id;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public Boolean getCheckable() {
        return checkable;
    }

    public void setCheckable(Boolean checkable) {
        this.checkable = checkable;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getClickable() {
        return clickable;
    }

    public void setClickable(Boolean clickable) {
        this.clickable = clickable;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getFocusable() {
        return focusable;
    }

    public void setFocusable(Boolean focusable) {
        this.focusable = focusable;
    }

    public Boolean getFocused() {
        return focused;
    }

    public void setFocused(Boolean focused) {
        this.focused = focused;
    }

    public Boolean getScrollable() {
        return scrollable;
    }

    public void setScrollable(Boolean scrollable) {
        this.scrollable = scrollable;
    }

    public Boolean getLongClickable() {
        return longClickable;
    }

    public void setLongClickable(Boolean longClickable) {
        this.longClickable = longClickable;
    }

    public Boolean getPassword() {
        return password;
    }

    public void setPassword(Boolean password) {
        this.password = password;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getBounds() {
        return bounds;
    }

    public void setBounds(String bounds) {
        this.bounds = bounds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double isEqual(UIElement uiElement, int pointX2, int pointY2, boolean elementForUdid) {

        double point = 0;

        if (!elementForUdid) {
            point = FindAttributesWeightage.getInstance().
                    getPositionWeightage() / shortestDistanceCord(uiElement.bounds, pointX2, pointY2);
        }
        point += FindAttributesWeightage.jaroDistance(uiElement.getText(), this.text);

        if (this.id.equals(uiElement.id)) {
            point = point + FindAttributesWeightage.getInstance().getIdWeightage();
        }
        if (this.bounds.equals(uiElement.bounds)) {
            point = point + FindAttributesWeightage.getInstance().getBoundsWeightage();
        }
        if (this.pkg.equals(uiElement.pkg)) {
            point = point + FindAttributesWeightage.getInstance().getPkgWeightage();
        }
        if (this.type.equals(uiElement.type)) {
            point = point + FindAttributesWeightage.getInstance().getClassWeightage();
        }
        if (this.contentDesc.equals(uiElement.contentDesc)) {
            point = point + FindAttributesWeightage.getInstance().getContentDescWeightage();
        }
        if (this.checkable == uiElement.checkable) {
            point = point + FindAttributesWeightage.getInstance().getCheckableWeightage();
        }
        if (this.checked == uiElement.checked) {
            point = point + FindAttributesWeightage.getInstance().getCheckedWeightage();
        }
        if (this.clickable == uiElement.clickable) {
            point = point + FindAttributesWeightage.getInstance().getLongClickableWeightage();
        }
        if (this.enabled == uiElement.enabled) {
            point = point + FindAttributesWeightage.getInstance().getEnabledWeightage();
        }
        if (this.focusable == uiElement.focusable) {
            point = point + FindAttributesWeightage.getInstance().getFocusableWeightage();
        }
        if (this.focused == uiElement.focused) {
            point = point + FindAttributesWeightage.getInstance().getFocusedWeightage();
        }
        if (this.scrollable == uiElement.scrollable) {
            point = point + FindAttributesWeightage.getInstance().getScrollableWeightage();
        }
        if (this.longClickable == uiElement.longClickable) {
            point = point + FindAttributesWeightage.getInstance().getLongClickableWeightage();
        }
        if (this.password == uiElement.password) {
            point = point + FindAttributesWeightage.getInstance().getPasswordWeightage();
        }
        if (this.selected == uiElement.selected) {
            point = point + FindAttributesWeightage.getInstance().getSelectedWeightage();
        }
        return point;
    }

    private double shortestDistanceCord(String bounds, int pointX2, int pointY2){

        bounds = bounds.replaceAll("\\D", ",");
        String[] splitted = bounds.split(",");

        double distanceBetweenCordnts;
        int pointX1 = Integer.parseInt(splitted[1]);
        int pointY1 = Integer.parseInt(splitted[2]);

        distanceBetweenCordnts = Math.pow((pointX2 - pointX1), 2) + Math.pow((pointY2 - pointY1), 2);
        distanceBetweenCordnts = Math.sqrt(distanceBetweenCordnts);
        distanceBetweenCordnts = distanceBetweenCordnts / 2937;
        distanceBetweenCordnts = Math.exp(distanceBetweenCordnts);

        return distanceBetweenCordnts + 1;
    }


}