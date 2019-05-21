package com.ebricks.appiumdemo.scriptModel;

import java.util.ArrayList;
import java.util.List;

public class ScriptInputData {
    private List<UIElement> uiElementList = new ArrayList<UIElement>();

    public List<UIElement> getUiElementList() {
        return uiElementList;
    }

    public void setUiElementList(List<UIElement> uiElementList) {
        this.uiElementList = uiElementList;
    }
}
