package com.ebricks.script.stepexecutor.response;

import com.ebricks.script.model.UIElement;

public class StepResponse {

    private Screen screen;
    private StepStatus stepStatus;
    private UIElement uiElement;

    public StepResponse(){
        this.screen = new Screen();
        this.stepStatus = new StepStatus();
    }

    public Screen getScreen() {
        return screen;
    }

    public StepStatus getStepStatus() {
        return stepStatus;
    }

    public UIElement getUiElement() {
        return uiElement;
    }

    public void setUiElement(UIElement uiElement) {
        this.uiElement = uiElement;
    }

    public String response(){
        return "{ "+
                "action:"+this.uiElement.getClass().getSimpleName()+","+
                "screenshotname:"+this.screen.getScreenshotName()+","+
                "status:"+this.stepStatus.getStatus()+"}";
    }
}