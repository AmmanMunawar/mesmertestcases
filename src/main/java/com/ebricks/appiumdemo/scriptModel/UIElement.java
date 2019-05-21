package com.ebricks.appiumdemo.scriptModel;

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
    private String resourceId;

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

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public boolean isEqual(UIElement uiElement) {

        if (
                this.text.equals(uiElement.text) &&
                this.type.equals(uiElement.type) &&
                this.pkg.equals(uiElement.pkg) &&
                this.contentDesc.equals(uiElement.contentDesc) &&
                this.checkable == uiElement.checkable &&
                this.checked == uiElement.checked &&
                this.clickable == uiElement.clickable &&
                this.enabled == uiElement.enabled &&
                this.focusable == uiElement.focusable &&
                this.focused == uiElement.focused &&
                this.scrollable == uiElement.scrollable &&
                this.longClickable == uiElement.longClickable &&
                this.password == uiElement.password &&
                this.selected == uiElement.selected &&
                this.bounds.equals(uiElement.bounds) &&
                this.resourceId.equals(uiElement.resourceId)
        ) {
            return true;
        }
        return false;
    }
}
